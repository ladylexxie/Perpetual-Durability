package ladylexxie.perpetual_durability.config;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class EnchantConfig {
    public static ForgeConfigSpec.ConfigValue<String> PERPETUAL_ITEM;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> MOD_BLACKLIST;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_BLACKLIST;

    public static void init(ForgeConfigSpec.Builder builder){
        builder.push("enchantments");
        PERPETUAL_ITEM = builder.comment("ID of the item being used for getting the Perpetual Enchant\ndefault: 'minecraft:nether_star'").define("perpetual.enchantItem", "minecraft:nether_star");
        MOD_BLACKLIST = builder.comment("List of the mods you want to blacklist\nExample: 'perpetual'\ndefault: []").defineList("perpetual.modBlacklist", Lists.newArrayList(), String.class::isInstance);
        ITEM_BLACKLIST = builder.comment("List of the item IDs you want to blacklist\nExample: 'minecraft:shield'\ndefault: []").defineList("perpetual.itemBlacklist", Lists.newArrayList(), String.class::isInstance);
        builder.pop();
    }
}