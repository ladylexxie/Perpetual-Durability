package ladylexxie.perpetual_durability.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class ModdedEnchant extends Enchantment {
    public int maxLevel = 1;
    public int minEnchantability = 0;
    public int maxEnchantability = 0;
    public boolean isTreasure = false;
    public boolean canBeVillagerTrade = true;
    public boolean canGenerateInLoot = true;
    public boolean canEnchantOnBooks = true;

    public ModdedEnchant setTreasure(boolean treasure) {
        isTreasure = treasure;
        return this;
    }

    public ModdedEnchant setCanBeVillagerTrade(boolean canBeVillagerTrade) {
        this.canBeVillagerTrade = canBeVillagerTrade;
        return this;
    }

    public ModdedEnchant setCanGenerateInLoot(boolean canGenerateInLoot) {
        this.canGenerateInLoot = canGenerateInLoot;
        return this;
    }

    public ModdedEnchant setCanEnchantOnBooks(boolean canEnchantOnBooks) {
        this.canEnchantOnBooks = canEnchantOnBooks;
        return this;
    }

    public ModdedEnchant(Rarity rarity, EnchantmentType enchantmentType, EquipmentSlotType... equipmentSlotTypes) {
        super(rarity, enchantmentType, equipmentSlotTypes);
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;
    }

    @Override
    public boolean isTreasureOnly() {
        return this.isTreasure;
    }

    @Override
    public int getMinCost(int minEnchantability) {
        return this.minEnchantability;
    }

    @Override
    public int getMaxCost(int maxEnchantability) {
        return this.maxEnchantability;
    }

    @Override
    public boolean isTradeable() {
        return canBeVillagerTrade;
    }

    @Override
    public boolean isDiscoverable() {
        return canGenerateInLoot;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return canEnchantOnBooks;
    }
}
