package matteo.weathertotems;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;

import matteo.weathertotems.registries.WeatherTotemItems;
import matteo.weathertotems.registries.CreativeTab;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Mod(WeatherTotem.MOD_ID)
public class WeatherTotem {

    public static final String MOD_ID = "weather_totems";
    public static final Logger LOGGER = LogManager.getLogger();


    public WeatherTotem() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "", (c, b) -> true));

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        WeatherTotem.init();
        WeatherTotemItems.register(eventBus);
        CreativeTab.register(eventBus);
    }

    public static void init() {
        WeatherTotemItems.init();


        LOGGER.info("Weather Totem is baking");
    }
}