package matteo.weathertotems.registries;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

import matteo.weathertotems.WeatherTotem;

public class CreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WeatherTotem.MOD_ID);

    private static final RegistryObject<CreativeModeTab> WEATHER_TOTEM_THUNDER = CREATIVE_MODE_TABS.register("weather_totems_item", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> WeatherTotemItems.WEATHER_TOTEM_THUNDER.get().asItem().getDefaultInstance())
            .title(Component.translatable("Weather Totems"))
            .displayItems((ctx, entry) -> {

                entry.accept(WeatherTotemItems.WEATHER_TOTEM_THUNDER.get());
                entry.accept(WeatherTotemItems.WEATHER_TOTEM_RAIN.get());
                entry.accept(WeatherTotemItems.WEATHER_TOTEM_CLEAR.get());
            }).build());


    public static void register(IEventBus event) {
        CREATIVE_MODE_TABS.register(event);
    }
}
