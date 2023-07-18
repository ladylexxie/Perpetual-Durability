package ladylexxie.perpetual_durability.recipe;

import ladylexxie.perpetual_durability.registry.PDRegistry;
import ladylexxie.perpetual_durability.util.PDUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PerpetuateRecipe implements SmithingRecipe {
	public PerpetuateRecipe( ResourceLocation recipeID ) {}

	@Override
	public boolean matches( Container inventory, Level world ) {
		ItemStack input = inventory.getItem(0);
		ItemStack addition = inventory.getItem(1);

		if( !addition.is(PDRegistry.TAG_PERPETUAL) ) return false;
		return PDUtils.canPerpetuate(input);
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return ItemStack.EMPTY;
	}

	@Override
	public ResourceLocation getId() {
		return null;
	}

	public ItemStack assemble(Container container, RegistryAccess registryAccess) {
		ItemStack stack = container.getItem(0).copy();
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
		stack.setDamageValue(0);
		return stack;
	}

	@Override public boolean isTemplateIngredient(ItemStack itemStack) { return false; }
	@Override public boolean isBaseIngredient(ItemStack itemStack) { return PDUtils.canPerpetuate(itemStack); }
	@Override public boolean isAdditionIngredient(ItemStack addition ) { return addition.is(PDRegistry.TAG_PERPETUAL); }
	@Override public @NotNull RecipeSerializer<?> getSerializer() { return PDRegistry.PERPETUATE.get(); }
}
