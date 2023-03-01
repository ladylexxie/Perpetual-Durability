package ladylexxie.perpetual_durability.recipe;

import ladylexxie.perpetual_durability.config.EnchantConfig;
import ladylexxie.perpetual_durability.registry.LexRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class ApplyPerpetual extends SmithingRecipe {
	@SuppressWarnings( "unchecked" )
	private final List<String> modBlacklist = (List<String>) EnchantConfig.MOD_BLACKLIST.get();
	@SuppressWarnings( "unchecked" )
	private final List<String> itemBlacklist = (List<String>) EnchantConfig.ITEM_BLACKLIST.get();

	public ApplyPerpetual( ResourceLocation recipeID ) {
		super(recipeID, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
	}

	@Override
	public boolean matches( IInventory inventory, World world ) {
		ItemStack input = inventory.getItem(0);
		ItemStack addition = inventory.getItem(1);
		String id = EnchantConfig.PERPETUAL_ITEM.get();

		if( input.isDamageableItem() ) {
			if( modBlacklist.contains(input.getItem().getRegistryName().getNamespace()) ) return false;
			if( itemBlacklist.contains(input.getItem().getRegistryName().toString()) ) return false;

			return Objects.equals(Objects.requireNonNull(addition.getItem().getRegistryName()).toString(), id);
		}
		return false;
	}

	@Override
	public ItemStack assemble( IInventory inventory ) {
		ItemStack stack = inventory.getItem(0).copy();
		CompoundNBT nbt = stack.getOrCreateTag();
		nbt.putBoolean("Unbreakable", true);
		stack.setTag(nbt);
		stack.setDamageValue(0);

		return stack;
	}

	@Override
	public ItemStack getResultItem() { return ItemStack.EMPTY; }

	@Override
	public boolean isAdditionIngredient( ItemStack addition ) {
		String id = EnchantConfig.PERPETUAL_ITEM.get();
		return Objects.equals(Objects.requireNonNull(addition.getItem().getRegistryName()).toString(), id);
	}

	@Override
	public IRecipeSerializer<?> getSerializer() { return LexRegistry.APPLY_PERPETUAL.get(); }
}
