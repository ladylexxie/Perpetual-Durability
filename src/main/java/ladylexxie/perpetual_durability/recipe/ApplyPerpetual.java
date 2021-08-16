package ladylexxie.perpetual_durability.recipe;

import ladylexxie.perpetual_durability.config.EnchantConfig;
import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ItemStackUtils;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Objects;

public class ApplyPerpetual extends SmithingRecipe {
    public ApplyPerpetual(ResourceLocation recipeID){
        super(recipeID, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
    }

    @Override
    public boolean matches(IInventory inventory, World world){
        ItemStack input = inventory.getItem(0);
        ItemStack addition = inventory.getItem(1);
        String id = EnchantConfig.PERPETUAL_ITEM.get();

        if(input.isDamageableItem() && !ItemStackUtils.hasEnchant(input, LexRegistry.PERPETUAL.get())){
            return Objects.equals(Objects.requireNonNull(addition.getItem().getRegistryName()).toString(), id);
        }
        return false;
    }

    @Override
    public ItemStack assemble(IInventory inventory){
        ItemStack stack = inventory.getItem(0).copy();
        stack.enchant(LexRegistry.PERPETUAL.get(), 1);

        return stack;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isAdditionIngredient(ItemStack addition) {
        String id = EnchantConfig.PERPETUAL_ITEM.get();
        return Objects.equals(Objects.requireNonNull(addition.getItem().getRegistryName()).toString(), id);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return LexRegistry.APPLY_PERPETUAL.get();
    }
}
