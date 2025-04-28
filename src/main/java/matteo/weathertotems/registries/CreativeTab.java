package matteo.weathertotems.registries;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;

import matteo.weathertotems.WeatherTotem;

import java.util.function.Supplier;

public class CreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WeatherTotem.MOD_ID);

    public static final Supplier<CreativeModeTab> WEATHER_TOTEMS_TAB = CREATIVE_MODE_TABS.register(WeatherTotem.MOD_ID, () -> CreativeModeTab.builder()
            .icon(() -> WeatherTotemItems.WEATHER_TOTEM_CLEAR.get().asItem().getDefaultInstance())
            .title(Component.translatable("Weather Totems"))
            .displayItems((parameters, output) -> WeatherTotemItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
            .build());

    public static void register(IEventBus event) {
        CREATIVE_MODE_TABS.register(event);
    }
}
