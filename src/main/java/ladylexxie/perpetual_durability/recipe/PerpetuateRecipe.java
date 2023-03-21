package ladylexxie.perpetual_durability.recipe;

import com.google.gson.JsonObject;
import ladylexxie.perpetual_durability.registry.PRegistry;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.LegacyUpgradeRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

@SuppressWarnings("removal")
public class PerpetuateRecipe extends LegacyUpgradeRecipe {
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
	public ItemStack assemble( Container inventory, RegistryAccess registryAccess ) {
		ItemStack stack = inventory.getItem(0).copy();
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
		stack.setDamageValue(0);
		return stack;
	}

	@Override public ItemStack getResultItem( RegistryAccess ra ) { return ItemStack.EMPTY; }
	@Override public boolean isAdditionIngredient( ItemStack addition ) { return addition.is(PRegistry.TAG_PERPETUAL); }
	@Override public RecipeSerializer<?> getSerializer() { return PRegistry.PERPETUATE.get(); }

	public static class Serializer implements RecipeSerializer<PerpetuateRecipe> {
		public PerpetuateRecipe fromJson( ResourceLocation id, JsonObject _unused ) { return new PerpetuateRecipe(id); }
		public PerpetuateRecipe fromNetwork( ResourceLocation id, FriendlyByteBuf _unused ) { return new PerpetuateRecipe(id); }
		public void toNetwork( FriendlyByteBuf _unused1, PerpetuateRecipe _unused2 ) { }
	}
}
