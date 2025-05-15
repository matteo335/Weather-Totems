package matteo.weathertotems.registries;

import net.minecraft.server.level.ServerLevel;

import net.neoforged.neoforge.common.ModConfigSpec;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.commons.lang3.tuple.Pair;


public class ModConfig {
    public static final ModConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;
    private static final Logger LOGGER = LogManager.getLogger("Weather Totems");
    public static ModConfigSpec.ConfigValue<Integer> ThunderDurationMin;
    public static ModConfigSpec.ConfigValue<Integer> ThunderDurationMax;
    public static ModConfigSpec.ConfigValue<Integer> RainDurationMin;
    public static ModConfigSpec.ConfigValue<Integer> RainDurationMax;
    public static ModConfigSpec.ConfigValue<Integer> ClearDurationMin;
    public static ModConfigSpec.ConfigValue<Integer> ClearDurationMax;

    public static ModConfigSpec.ConfigValue<Boolean> ShowNotification;

    private ModConfig(ModConfigSpec.Builder builder) {
        builder.comment("Duration values in this configuration file is related to Minecraft's Ticks per second (TPS)",
                "Please set the following values as tick number. Default values are vanilla beside Clear (-1, 0) who return to vanilla",
                "These values are calculated at random between the Min and the Max, both cannot be set at the same number",
                "ShowNotification: true to send the notification in the player screen, false to send in the chat");
        ThunderDurationMin = builder.define("ThunderDurationMin", ServerLevel.THUNDER_DURATION.getMinValue());
        ThunderDurationMax = builder.define("ThunderDurationMax", ServerLevel.THUNDER_DURATION.getMaxValue());

        RainDurationMin = builder.define("RainDurationMin", ServerLevel.RAIN_DURATION.getMinValue());
        RainDurationMax = builder.define("RainDurationMax", ServerLevel.RAIN_DURATION.getMaxValue());

        ClearDurationMin = builder.define("ClearDurationMin", -1);
        ClearDurationMax = builder.define("ClearDurationMax", 0);

        ShowNotification = builder.define("ShowNotification", false);

        LOGGER.atInfo().log("Config initialized");
    }

    static {
        Pair<ModConfig, ModConfigSpec> pair =
            new ModConfigSpec.Builder().configure(ModConfig::new);

            CONFIG = pair.getLeft();
            CONFIG_SPEC = pair.getRight();
        }
}
