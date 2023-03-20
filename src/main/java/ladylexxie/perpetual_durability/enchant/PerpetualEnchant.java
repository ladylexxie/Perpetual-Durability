package ladylexxie.perpetual_durability.enchant;

import ladylexxie.perpetual_durability.registry.PRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class PerpetualEnchant extends Enchantment {
	public PerpetualEnchant() {
		super(Rarity.VERY_RARE, PRegistry.NOTHING, new EquipmentSlotType[]{ EquipmentSlotType.MAINHAND });
	}

	@Override public boolean isTreasureOnly() { return false; }
	@Override public boolean isTradeable() { return false; }
	@Override public boolean isDiscoverable() { return false; }
	@Override public boolean isAllowedOnBooks() { return false; }
	@Override public boolean canApplyAtEnchantingTable( ItemStack stack ) { return false; }
}
