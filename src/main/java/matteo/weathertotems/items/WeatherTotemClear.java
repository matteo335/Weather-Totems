package matteo.weathertotems.items;

import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.server.level.ServerLevel;

import org.jetbrains.annotations.NotNull;

public class WeatherTotemClear extends Item {

    public WeatherTotemClear(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ron = super.use(world, entity, hand);
        CommandSourceStack pSource = entity.createCommandSourceStack();
        ItemStack itemStack = entity.getItemInHand(hand);

        if (!world.isClientSide()) {
            setClear(pSource, entity);
            itemStack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
        }
        return ron;
    }

    public void setClear(CommandSourceStack pSource, Player player) {
        ServerLevel level = pSource.getLevel();

        if (!level.isClientSide()) {
            pSource.getLevel().setWeatherParameters(0, 12000, false, false); //The value in pWeatherTime is to prevent Vanilla from setting the weather back to rain immediately after, which can happens
            pSource.sendSuccess(() -> Component.translatable(player.getName().getString() + " Has cleared the weather using a Weather Totem"), true);
        }
    }
}
