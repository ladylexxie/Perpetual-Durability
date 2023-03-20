package ladylexxie.perpetual_durability.config;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

import java.util.List;

public class PCommonConfig {
	public static ConfigValue<List<? extends String>> MOD_BLACKLIST;
	public static ConfigValue<List<? extends String>> ITEM_BLACKLIST;
	public static ConfigValue<List<? extends String>> TAGS_BLACKLIST;

	public PCommonConfig( Builder builder ) {
		builder.push("blacklist");
		MOD_BLACKLIST = builder.comment("List of the mods you want to blacklist\nExample: [\"perpetual\", \"botania\"]\ndefault: []").defineList("mods", Lists.newArrayList(), String.class::isInstance);
		ITEM_BLACKLIST = builder.comment("List of the item IDs you want to blacklist\nExample: [\"minecraft:shield\", \"minecraft:diamond_sword\"]\ndefault: []").defineList("items", Lists.newArrayList(), String.class::isInstance);
		TAGS_BLACKLIST = builder.comment("List of the item tags you want to blacklist\nExample: [\"forge:tools/shovel\"]\ndefault: []").defineList("tags", Lists.newArrayList(), String.class::isInstance);
		builder.pop();
	}
}