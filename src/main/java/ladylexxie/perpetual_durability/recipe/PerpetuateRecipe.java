package ladylexxie.perpetual_durability.recipe;

import ladylexxie.perpetual_durability.registry.PRegistry;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PerpetuateRecipe extends SmithingRecipe {
	public PerpetuateRecipe( ResourceLocation recipeID ) {
		super(recipeID, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
	}

	@Override
	public boolean matches( IInventory inventory, World world ) {
		ItemStack input = inventory.getItem(0);
		ItemStack addition = inventory.getItem(1);

		if( !addition.getItem().is(PRegistry.TAG_PERPETUAL) ) return false;
		if( !PUtils.canPerpetuate(input) ) return false;
		return true;
	}

	@Override
	public ItemStack assemble( IInventory inventory ) {
		ItemStack stack = inventory.getItem(0).copy();
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
		stack.setDamageValue(0);
		return stack;
	}

	@Override public ItemStack getResultItem() { return ItemStack.EMPTY; }
	@Override public boolean isAdditionIngredient( ItemStack addition ) {	return addition.getItem().is(PRegistry.TAG_PERPETUAL); }
	@Override public IRecipeSerializer<?> getSerializer() { return PRegistry.PERPETUATE.get(); }
}
