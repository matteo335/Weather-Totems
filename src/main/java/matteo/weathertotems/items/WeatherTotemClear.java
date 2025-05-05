package matteo.weathertotems.items;

import net.minecraft.advancements.critereon.ItemDurabilityTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

public class WeatherTotemClear extends Item {

    public WeatherTotemClear(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ron = super.use(level, player, hand);
        CommandSourceStack source = player.createCommandSourceStack();

        if (!level.isClientSide()) { setClear(player, level, source); }
        return ron;
    }

    public void setClear(Player player, Level level, CommandSourceStack source) {
        InteractionHand hand = InteractionHand.MAIN_HAND;
        ItemStack itemStack = player.getItemInHand(hand);

        itemStack.hurtAndBreak(1, player, (entity) ->
                entity.broadcastBreakEvent(player.getUsedItemHand()));


        if (!level.isClientSide()) {
            source.getLevel().setWeatherParameters(0, 12000, false, false); //The value in pWeatherTime is to prevent Vanilla from setting the weather back to rain immediately after, which can happens
            source.sendSuccess(() -> Component.translatable(player.getName().getString() + " has cleared the weather using a Weather Totem"), true);
        }
    }
}
