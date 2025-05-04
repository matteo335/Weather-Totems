package matteo.weathertotems.items;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import org.jetbrains.annotations.NotNull;


public class WeatherTotemRain extends Item {

    public WeatherTotemRain(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResult use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResult ron = super.use(world, entity, hand);
        ItemStack itemStack = entity.getItemInHand(hand);

        if (!world.isClientSide()) {
            CommandSourceStack pSource = entity.createCommandSourceStackForNameResolution((ServerLevel) world);

            setRain(pSource, entity);
            itemStack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
        }

        //if (!world.isClientSide() && world.getServer() != null) { world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("§r§5 Someone changed the weather to Raining using a Weather Totem. If nothing is happening it's in cooldown"), false); }
        return ron;
    }

    public void setRain(CommandSourceStack pSource, Player player) {
        ServerLevel level = pSource.getLevel();

        if (!level.isClientSide()) {
            pSource.getLevel().setWeatherParameters(0, 12000, true, false);
            level.getServer().getPlayerList().broadcastSystemMessage(Component.translatable(player.getName().getString() + " has summoned the rain for 10 minutes using a Weather Totem"), false);
        }
    }
}
