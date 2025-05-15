package matteo.weathertotems.items;

import net.minecraft.util.RandomSource;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;

import static matteo.weathertotems.registries.ModConfig.ClearDurationMin;
import static matteo.weathertotems.registries.ModConfig.ClearDurationMax;
import static matteo.weathertotems.registries.ModConfig.ShowNotification;

import org.jetbrains.annotations.NotNull;

public class WeatherTotemClear extends Item {

    public WeatherTotemClear(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ron = super.use(level, player, hand);
        ItemStack itemStack = player.getItemInHand(hand);
        CommandSourceStack source = player.createCommandSourceStack();

        if (!level.isClientSide()) {
            setClear(player, source);
            itemStack.hurtAndBreak(1, player, (entity) -> player.broadcastBreakEvent(player.getUsedItemHand()));
        }
        return ron;
    }

    public void setClear(Player player, CommandSourceStack source) {
        ServerLevel level = source.getLevel();
        int ClearDuration = RandomSource.create().nextInt(ClearDurationMin.get(), ClearDurationMax.get());

        if (!level.isClientSide()) {
            source.getLevel().setWeatherParameters(0, ClearDuration, false, false);
            level.getServer().getPlayerList().broadcastSystemMessage(Component.translatable(player.getName().getString() + " has cleared the weather using a Weather Totem"), ShowNotification.get());
        }
    }
}
