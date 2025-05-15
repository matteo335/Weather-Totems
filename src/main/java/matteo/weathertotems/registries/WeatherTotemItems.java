package matteo.weathertotems.registries;

import net.minecraft.advancements.critereon.ItemDurabilityTrigger;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraft.world.item.Item;

import matteo.weathertotems.WeatherTotem;
import matteo.weathertotems.items.WeatherTotemThunder;
import matteo.weathertotems.items.WeatherTotemRain;
import matteo.weathertotems.items.WeatherTotemClear;

import java.util.function.Supplier;


public class WeatherTotemItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeatherTotem.MOD_ID);

    public static final RegistryObject<Item> WEATHER_TOTEM_THUNDER = registerItem("weather_totem_thunder", () -> new WeatherTotemThunder((new Item.Properties()).stacksTo(1).durability(3).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> WEATHER_TOTEM_RAIN = registerItem("weather_totem_rain", () -> new WeatherTotemRain((new Item.Properties()).stacksTo(1).durability(3).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> WEATHER_TOTEM_CLEAR = registerItem("weather_totem_clear", () -> new WeatherTotemClear((new Item.Properties()).stacksTo(1).durability(3).rarity(Rarity.EPIC)));


    public static <T extends Item> RegistryObject<T> registerItem(String identifier, Supplier<T> item) {
        return WeatherTotemItems.ITEMS.register(identifier, item);
    }

    public static void init(){}

    public static void register(IEventBus event) {
        ITEMS.register(event);
    }
}
