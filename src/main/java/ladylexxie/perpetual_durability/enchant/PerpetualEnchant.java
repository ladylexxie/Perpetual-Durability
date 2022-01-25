package ladylexxie.perpetual_durability.enchant;

import ladylexxie.perpetual_durability.registry.LexRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class PerpetualEnchant extends Enchantment {
    public PerpetualEnchant() {
        super(Rarity.VERY_RARE, LexRegistry.DESTRUCTIBLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack){
        return false;
    }
}
