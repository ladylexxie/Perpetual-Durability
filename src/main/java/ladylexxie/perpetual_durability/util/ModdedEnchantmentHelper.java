package ladylexxie.perpetual_durability.util;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class ModdedEnchantmentHelper {
    public static boolean hasEnchant(ItemStack item, Enchantment enchantment){
        for(Enchantment itemEnchantment : EnchantmentHelper.getEnchantments(item).keySet()){
            if(itemEnchantment == enchantment) return true;
        }
        return false;
    }
}
