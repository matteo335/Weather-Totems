package matteo.weathertotems.registries;

import net.minecraft.server.level.ServerLevel;

import net.minecraftforge.common.ForgeConfigSpec;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public static final ModConfig CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;
    private static final Logger LOGGER = LogManager.getLogger("Weather Totems");

    public static ForgeConfigSpec.ConfigValue<Integer> ThunderDurationMin;
    public static ForgeConfigSpec.ConfigValue<Integer> ThunderDurationMax;

    public static ForgeConfigSpec.ConfigValue<Integer> RainDurationMin;
    public static ForgeConfigSpec.ConfigValue<Integer> RainDurationMax;

    public static ForgeConfigSpec.ConfigValue<Integer> ClearDurationMin;
    public static ForgeConfigSpec.ConfigValue<Integer> ClearDurationMax;

    public static ForgeConfigSpec.ConfigValue<Boolean> ShowNotification;

    private ModConfig(ForgeConfigSpec.Builder builder) {
        builder.comment(
                "Duration values in this configuration file is related to Minecraft's Ticks Per Second (TPS)",
                "Defaults are vanilla, except for the Clear (-1, 0) who return to Vanilla",
                "The values in this file are calculated at random between Min and Max. Both cannot be set the same number",
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
        Pair<ModConfig, ForgeConfigSpec> pair =
                new ForgeConfigSpec.Builder().configure(ModConfig::new);

        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }
}
