package matteo.weathertotems.items;

import net.minecraft.util.RandomSource;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import static matteo.weathertotems.registries.ModConfig.ClearDurationMin;
import static matteo.weathertotems.registries.ModConfig.ClearDurationMax;
import static matteo.weathertotems.registries.ModConfig.ShowNotification;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class WeatherTotemClear extends Item {

    public WeatherTotemClear(Properties properties) {
        super(properties);
    }

    private static final Logger LOGGER = LogManager.getLogger("Weather Totems");

    public @NotNull InteractionResult use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResult ron = super.use(world, entity, hand);
        ItemStack itemStack = entity.getItemInHand(hand);

        if (!world.isClientSide()) {
            CommandSourceStack pSource = entity.createCommandSourceStackForNameResolution((ServerLevel) world);

            setClear(pSource, entity);
            itemStack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
        }

        return ron;
    }

    public void setClear(CommandSourceStack pSource, Player player) {
        ServerLevel level = pSource.getLevel();
        int ClearDuration = RandomSource.create().nextInt(ClearDurationMin.get(), ClearDurationMax.get());

        if (!level.isClientSide()) {
                pSource.getLevel().setWeatherParameters(0, ClearDuration, false, false);
                level.getServer().getPlayerList().broadcastSystemMessage(Component.translatable(player.getName().getString() + " has cleared the weather using a Weather Totem"), ShowNotification.get());
                LOGGER.atDebug().log(ClearDuration);
        }
    }
}
