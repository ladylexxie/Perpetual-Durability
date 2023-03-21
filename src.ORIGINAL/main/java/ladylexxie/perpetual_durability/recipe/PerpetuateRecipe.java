package ladylexxie.perpetual_durability.recipe;

import ladylexxie.perpetual_durability.registry.PRegistry;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PerpetuateRecipe extends UpgradeRecipe {
	public PerpetuateRecipe( ResourceLocation recipeID ) {
		super(recipeID, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
	}

	@Override
	public boolean matches( Container inventory, Level world ) {
		ItemStack input = inventory.getItem(0);
		ItemStack addition = inventory.getItem(1);

		if( !addition.is(PRegistry.TAG_PERPETUAL) ) return false;
		if( !PUtils.canPerpetuate(input) ) return false;
		return true;
	}

	@Override
	public @NotNull ItemStack assemble( Container inventory ) {
		ItemStack stack = inventory.getItem(0).copy();
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
		stack.setDamageValue(0);
		return stack;
	}

	@Override public @NotNull ItemStack getResultItem() { return ItemStack.EMPTY; }
	@Override public boolean isAdditionIngredient( ItemStack addition ) {	return addition.is(PRegistry.TAG_PERPETUAL); }
	@Override public @NotNull RecipeSerializer<?> getSerializer() { return PRegistry.PERPETUATE.get(); }
}
