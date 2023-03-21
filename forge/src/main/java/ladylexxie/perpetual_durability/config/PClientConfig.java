package ladylexxie.perpetual_durability.config;

import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class PClientConfig {
	public static ConfigValue<Boolean> SHOW_JEI_RECIPE;
	public static ConfigValue<Boolean> TAG_TOOLTIP;
	public static ConfigValue<Boolean> COLORFUL_TOOLTIP;
	public static ConfigValue<Boolean> ENABLE_DEBUG_TAGS;
	public static ConfigValue<Boolean> ENABLE_DEBUG_NBT;

	public PClientConfig( Builder builder ) {
		SHOW_JEI_RECIPE = builder.comment("Enable the recipe to show up in JEI\ndefault: true").define("enableJEIRecipe", true);
		TAG_TOOLTIP = builder.comment("Enable tagged items to have a tooltip\ndefault: true").define("enableTagTooltip", true);
		COLORFUL_TOOLTIP = builder.comment("Enable animated tooltip\ndefault: true").define("enableColorfulTooltip", true);

		builder.push("debug");
		ENABLE_DEBUG_TAGS = builder.comment("Enable items showing their tags on the tooltip when holding CTRL\ndefault: true").define("enableDebugTags", true);
		ENABLE_DEBUG_NBT = builder.comment("Enable items showing their NBT on the tooltip when holding ALT\ndefault: true").define("enableDebugNBT", true);
		builder.pop();
	}
}
