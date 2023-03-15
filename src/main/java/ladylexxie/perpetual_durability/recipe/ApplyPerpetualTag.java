package ladylexxie.perpetual_durability.recipe;

import com.google.gson.JsonObject;
import ladylexxie.perpetual_durability.config.PCommonConfig;
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

import java.util.List;

public class ApplyPerpetualTag extends LegacyUpgradeRecipe {
	private static final List<String> modBlacklist = (List<String>) PCommonConfig.MOD_BLACKLIST.get();
	private static final List<String> itemBlacklist = (List<String>) PCommonConfig.ITEM_BLACKLIST.get();
	private static final List<String> tagsBlacklist = (List<String>) PCommonConfig.TAGS_BLACKLIST.get();

	public ApplyPerpetualTag( ResourceLocation recipeID ) {
		super(recipeID, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
	}

	@Override
	public boolean matches( Container inventory, Level world ) {
		ItemStack input = inventory.getItem(0);
		ItemStack addition = inventory.getItem(1);

		if( !input.isDamageableItem() ) return false;
		if( !addition.is(PRegistry.TAG_PERPETUAL) ) return false;
		if( !canGetTag(input) ) return false;
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
	@Override public RecipeSerializer<?> getSerializer() { return PRegistry.APPLY_PERPETUAL_TAG.get(); }

	private boolean canGetTag( ItemStack stack ) {
		ResourceLocation stackID = PUtils.getID(stack);
		if( modBlacklist.contains(stackID.getNamespace()) ) return false;
		if( itemBlacklist.contains(stackID.toString()) ) return false;
		if( tagsBlacklist.stream().noneMatch(tag -> PUtils.hasTag(stack, tag)) ) return false;
		return true;
	}

	public static class Serializer implements RecipeSerializer<ApplyPerpetualTag> {
		public ApplyPerpetualTag fromJson( ResourceLocation id, JsonObject _unused ) { return new ApplyPerpetualTag(id); }
		public ApplyPerpetualTag fromNetwork( ResourceLocation id, FriendlyByteBuf _unused ) { return new ApplyPerpetualTag(id); }
		public void toNetwork( FriendlyByteBuf _unused1, ApplyPerpetualTag _unused2 ) { }
	}
}
