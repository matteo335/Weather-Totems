package matteo.weathertotems.items;

import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

public class WeatherTotemThunder extends Item {


    public WeatherTotemThunder(Properties properties/*, ItemStack itemStack, Player player*/) {
        super(properties);
        //itemStack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(player.getUsedItemHand()));
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ron = super.use(world, entity, hand);
        CommandSourceStack pSource = entity.createCommandSourceStack();
        //UseOnContext pContext = entity.getItemInHand(hand).hurtAndBreak(1, );

        if (!world.isClientSide()) { setThunder(pSource); }
        return ron;
    }

    public void setThunder(CommandSourceStack pSource) {
        ServerLevel level = pSource.getLevel();

        //UseOnContext pContext = entity.getItemInHand(hand).hurtAndBreak(1, null, LivingEntity);
        //for (GameProfile $$5 : gameProfile) {
/*
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand())
        );
 */

        if (!level.isClientSide()) {
        pSource.getLevel().setWeatherParameters(0, 12000, true, true);
            pSource.sendSuccess(() -> Component.translatable("weather_totems.setThunder"/* + $$5*/), true);
            //level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("%1$s" + "%1$s Someone changed the weather to Thunder using a Weather Totem. If nothing is happening it's in cooldown"), false);
            //}
        }
    }
}