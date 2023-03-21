package ladylexxie.perpetual_durability.config;

import ladylexxie.perpetual_durability.PerpetualDurability;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config( name = PerpetualDurability.ID )
public class PClientConfig implements ConfigData {
	@Comment( "Enable the recipe to show up in JEI\ndefault: true" )
	boolean showJeiRecipe = true;
	@Comment( "Enable tagged items to have a tooltip\ndefault: true" )
	boolean tagTooltip = true;
	@Comment( "Enable animated tooltip\ndefault: true" )
	boolean colorfulTooltip = true;

	@ConfigEntry.Gui.CollapsibleObject
	Debug debug = new Debug();

	static class Debug {
		@Comment( "Enable items showing their tags on the tooltip when holding CTRL\ndefault: true" )
		boolean enableDebugTags = true;
		@Comment( "Enable items showing their NBT on the tooltip when holding ALT\ndefault: true" )
		boolean enableDebugNbt = true;
	}
}
