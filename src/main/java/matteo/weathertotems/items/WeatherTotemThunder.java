package matteo.weathertotems.items;

import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import org.jetbrains.annotations.NotNull;

public class WeatherTotemThunder extends Item {


    public WeatherTotemThunder(Properties properties) {
        super(properties);
    }


    public @NotNull InteractionResult use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResult ron = super.use(world, entity, hand);
        ItemStack itemStack = entity.getItemInHand(hand);

        if (!world.isClientSide()) {
            CommandSourceStack pSource = entity.createCommandSourceStackForNameResolution((ServerLevel) world);

            setThunder(pSource, entity);
            itemStack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
        }
        return ron;
    }


    public void setThunder(CommandSourceStack pSource, Player player) {
        ServerLevel level = pSource.getLevel();

        if (!level.isClientSide()) {
            pSource.getLevel().setWeatherParameters(0, 12000, true, true);
            level.getServer().getPlayerList().broadcastSystemMessage(Component.translatable(player.getName().getString() + " has summoned the thunder for 10 minutes using a Weather Totem "), false);
        }
    }
}