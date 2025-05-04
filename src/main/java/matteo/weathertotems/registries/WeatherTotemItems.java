package matteo.weathertotems.registries;


import com.google.common.collect.Sets;
import net.minecraft.world.item.Rarity;
import net.minecraft.core.registries.Registries;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.item.Item;

import matteo.weathertotems.WeatherTotem;
import matteo.weathertotems.items.WeatherTotemThunder;
import matteo.weathertotems.items.WeatherTotemRain;
import matteo.weathertotems.items.WeatherTotemClear;

import java.util.LinkedHashSet;
import java.util.function.Supplier;


public class WeatherTotemItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, WeatherTotem.MOD_ID);
    public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();


    public static final Supplier<Item> WEATHER_TOTEM_THUNDER = registerItem("weather_totem_thunder", () -> new WeatherTotemThunder((new Item.Properties()).stacksTo(1).durability(3).rarity(Rarity.EPIC))); //Is it trash code? Maybe. Is it working? Yes.
    public static final Supplier<Item> WEATHER_TOTEM_RAIN = registerItem("weather_totem_rain", () -> new WeatherTotemRain((new Item.Properties()).stacksTo(1).durability(3).rarity(Rarity.EPIC)));
    public static final Supplier<Item> WEATHER_TOTEM_CLEAR = registerItem("weather_totem_clear", () -> new WeatherTotemClear((new Item.Properties()).stacksTo(1).durability(3).rarity(Rarity.EPIC)));


    public static Supplier<Item> registerItem(final String identifier, final Supplier<Item> supplier) {
            Supplier<Item> item = ITEMS.register(identifier, supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    public static void init(){}

    public static void register(IEventBus event) {
        ITEMS.register(event);
    }
}
