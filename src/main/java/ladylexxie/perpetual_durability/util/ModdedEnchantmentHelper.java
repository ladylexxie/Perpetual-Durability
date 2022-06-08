package ladylexxie.perpetual_durability.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;

public class ModdedEnchantmentHelper {
    public static boolean hasEnchant(ItemStack item, Enchantment enchantment) {
        for (Enchantment itemEnchantment : EnchantmentHelper.getEnchantments(item).keySet()) {
            if (itemEnchantment == enchantment) {
                LogManager.getLogger().info("true");
                return true;
            }
        }
        LogManager.getLogger().info("false");
        return false;
    }
}
