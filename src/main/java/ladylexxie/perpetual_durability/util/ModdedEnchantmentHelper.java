package ladylexxie.perpetual_durability.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class ModdedEnchantmentHelper {
	public static boolean hasEnchant( ItemStack item, Enchantment enchantment ) {
		for( Enchantment itemEnchantment : EnchantmentHelper.getEnchantments(item).keySet() ) {
			if( itemEnchantment == enchantment ) { return true; }
		}
		return false;
	}
}
