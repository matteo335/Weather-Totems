package matteo.weathertotems;

import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;

import matteo.weathertotems.registries.WeatherTotemItems;
import matteo.weathertotems.registries.CreativeTab;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Mod(WeatherTotem.MOD_ID)
public class WeatherTotem {

    public static final String MOD_ID = "weather_totems";
    public static final Logger LOGGER = LogManager.getLogger();


    public WeatherTotem(IEventBus eventBus) {
        WeatherTotem.init();
        WeatherTotemItems.register(eventBus);
        CreativeTab.register(eventBus);
    }

    public static void init() {
        WeatherTotemItems.init();

        LOGGER.info("Weather Totem is baking");
    }
}