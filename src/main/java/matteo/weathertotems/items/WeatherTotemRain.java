package matteo.weathertotems.items;

import net.minecraft.util.RandomSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;

import static matteo.weathertotems.registries.ModConfig.RainDurationMin;
import static matteo.weathertotems.registries.ModConfig.RainDurationMax;
import static matteo.weathertotems.registries.ModConfig.ShowNotification;

import org.jetbrains.annotations.NotNull;

public class WeatherTotemRain extends Item {

    public WeatherTotemRain(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ron = super.use(world, player, hand);
        ItemStack itemStack = player.getItemInHand(hand);

        if (!world.isClientSide()) {
            CommandSourceStack pSource = player.createCommandSourceStack();

            setRain(pSource, player);
            itemStack.hurtAndBreak(1, player, (entity) -> player.broadcastBreakEvent(player.getUsedItemHand()));
        }
        return ron;
    }

    public void setRain(CommandSourceStack pSource, Player player) {
        ServerLevel level = pSource.getLevel();
        int RainDuration = RandomSource.create().nextInt(RainDurationMin.get(), RainDurationMax.get());

        if (!level.isClientSide()) {
            pSource.getLevel().setWeatherParameters(0, RainDuration, true, false);
            level.getServer().getPlayerList().broadcastSystemMessage(Component.translatable(player.getName().getString() + " has summoned the rain for " + RainDuration / 1200 + "m using a Weather Totem"), ShowNotification.get());
        }
    }
}
