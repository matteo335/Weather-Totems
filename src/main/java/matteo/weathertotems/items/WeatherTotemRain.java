package matteo.weathertotems.items;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;

import static matteo.weathertotems.registries.ModConfig.RainDurationMin;
import static matteo.weathertotems.registries.ModConfig.RainDurationMax;
import static matteo.weathertotems.registries.ModConfig.ShowNotification;

import org.jetbrains.annotations.NotNull;

public class WeatherTotemRain extends Item {

    public WeatherTotemRain(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ron = super.use(world, entity, hand);
        CommandSourceStack pSource = entity.createCommandSourceStack();
        ItemStack itemStack = entity.getItemInHand(hand);

        if (!world.isClientSide()) {
            setRain(pSource, entity);
            itemStack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
        }
        return ron;
    }

    public void setRain(CommandSourceStack pSource, Player player) {
        ServerLevel level = pSource.getLevel();
        int RainDuration = RandomSource.create().nextInt(RainDurationMin.get(), RainDurationMax.get());

        if (!level.isClientSide()) {
            pSource.getLevel().setWeatherParameters(0, RainDuration, true, false);
            level.getServer().getPlayerList().broadcastSystemMessage(Component.translatable(player.getName().getString() + " summoned the rain for " + RainDuration / 1200 + "m using a Weather Totem"), ShowNotification.get());
        }
    }
}
