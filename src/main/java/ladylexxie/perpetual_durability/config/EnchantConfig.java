package ladylexxie.perpetual_durability.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EnchantConfig {
    public static ForgeConfigSpec.ConfigValue<String> PERPETUAL_ITEM;

    public static void init(ForgeConfigSpec.Builder builder){
        builder.push("enchantments");
        PERPETUAL_ITEM = builder.comment("ID of the item being used for getting the Perpetual Enchant [default: \"minecraft:nether_star\"]").define("perpetual.enchantItem", "minecraft:nether_star");
        builder.pop();
    }
}
