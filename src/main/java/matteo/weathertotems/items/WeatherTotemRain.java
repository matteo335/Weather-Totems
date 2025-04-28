package matteo.weathertotems.items;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;

import net.neoforged.neoforge.common.UsernameCache;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class WeatherTotemRain extends Item {

    public WeatherTotemRain(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ron = super.use(world, entity, hand);
        CommandSourceStack pSource = entity.createCommandSourceStack();


        //if (!world.isClientSide() && world.getServer() != null) { world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("§r§5 Someone changed the weather to Raining using a Weather Totem. If nothing is happening it's in cooldown"), false); }
        if (!world.isClientSide()) { setRain(pSource); }

        return ron;
    }

    public void setRain(CommandSourceStack pSource) {
        ServerLevel level = pSource.getLevel();

        if (!level.isClientSide()) {
            pSource.getLevel().setWeatherParameters(0, 12000, true, false);
            pSource.sendSuccess(() -> Component.translatable("weather_totems.setRain" + UsernameCache.getLastKnownUsername(UUID.randomUUID())), true);
        }
    }
}
