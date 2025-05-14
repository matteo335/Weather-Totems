package matteo.weathertotems;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;

import matteo.weathertotems.registries.WeatherTotemItems;
import matteo.weathertotems.registries.CreativeTab;
import matteo.weathertotems.registries.ModConfig;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Mod(WeatherTotem.MOD_ID)
public class WeatherTotem {

    public static final String MOD_ID = "weather_totems";
    public static final Logger LOGGER = LogManager.getLogger("Weather Totems");


    public WeatherTotem(IEventBus eventBus, ModContainer modContainer) {
        WeatherTotem.init();
        WeatherTotemItems.register(eventBus);
        CreativeTab.register(eventBus);

        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.COMMON, ModConfig.CONFIG_SPEC);
    }

    public static void init() {
        WeatherTotemItems.init();

        LOGGER.info("Baking");
    }
}