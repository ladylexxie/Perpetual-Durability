package ladylexxie.perpetual_durability.config;

import ladylexxie.perpetual_durability.PDConstants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config( name = PDConstants.ID )
public class PDClientConfig implements ConfigData {
	@Comment( "Enable the recipe to show up in JEI/REI\ndefault: true" )
	public boolean showRecipes = true;
	@Comment( "Enable tagged items to have a tooltip\ndefault: true" )
	public boolean tagTooltip = true;
	@Comment( "Enable animated tooltip\ndefault: true" )
	public boolean colorfulTooltip = true;

	@ConfigEntry.Gui.CollapsibleObject
	public Debug debug = new Debug();

	public static class Debug {
		@Comment( "Enable items showing their tags on the tooltip when holding CTRL\ndefault: true" )
		public boolean enableDebugTags = true;
		@Comment( "Enable items showing their NBT on the tooltip when holding ALT\ndefault: true" )
		public boolean enableDebugNbt = true;
	}
}
