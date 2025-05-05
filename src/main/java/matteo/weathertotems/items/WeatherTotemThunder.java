package matteo.weathertotems.items;

import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

public class WeatherTotemThunder extends Item {


    public WeatherTotemThunder(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ron = super.use(world, player, hand);
        CommandSourceStack pSource = player.createCommandSourceStack();
        ItemStack itemStack = player.getItemInHand(hand);

        if (!world.isClientSide()) {
            setThunder(pSource, player);
            itemStack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(player.getUsedItemHand()));
        }
        return ron;
    }

    public void setThunder(CommandSourceStack pSource, Player player) {
        ServerLevel level = pSource.getLevel();

        if (!level.isClientSide()) {
        pSource.getLevel().setWeatherParameters(0, 12000, true, true);
            pSource.sendSuccess(() -> Component.translatable(player.getName().getString() + " has summoned the thunder using a Weather Totem"), true);
        }
    }
}