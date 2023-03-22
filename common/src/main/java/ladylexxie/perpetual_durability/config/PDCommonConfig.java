package ladylexxie.perpetual_durability.config;

import ladylexxie.perpetual_durability.PDConstants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.List;

@Config(name = PDConstants.ID )
public class PDCommonConfig implements ConfigData {
	public Blacklist blacklist = new Blacklist();

	public static class Blacklist {
		@Comment("List of the mods you want to blacklist\nExample: [\"perpetual\", \"botania\"]\ndefault: []")
		public List<String> modBlacklist = new ArrayList<>();
		@Comment("List of the item IDs you want to blacklist\nExample: [\"minecraft:shield\", \"minecraft:diamond_sword\"]\ndefault: []")
		public List<String> itemBlacklist = new ArrayList<>();
		@Comment("List of the item tags you want to blacklist\nExample: [\"forge:tools/shovel\"]\ndefault: []")
		public List<String> tagBlacklist = new ArrayList<>();
	}
}
