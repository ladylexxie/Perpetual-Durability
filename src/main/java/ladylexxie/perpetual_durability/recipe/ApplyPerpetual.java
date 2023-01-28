package ladylexxie.perpetual_durability.recipe;

import com.google.gson.JsonObject;
import ladylexxie.perpetual_durability.config.EnchantConfig;
import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ModdedEnchantmentHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ApplyPerpetual extends UpgradeRecipe {
	final Ingredient base;
	final Ingredient addition;
	final ItemStack result;
	private final List<String> modBlacklist = (List<String>) EnchantConfig.MOD_BLACKLIST.get();
	private final List<String> itemBlacklist = (List<String>) EnchantConfig.ITEM_BLACKLIST.get();

	public ApplyPerpetual( ResourceLocation recipeID ) {
		super(recipeID, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
		this.base = Ingredient.EMPTY;
		this.addition = Ingredient.EMPTY;
		this.result = ItemStack.EMPTY;
	}

	@Override
	public boolean matches( Container inventory, Level world ) {
		ItemStack input = inventory.getItem(0);
		ItemStack addition = inventory.getItem(1);
		String id = EnchantConfig.PERPETUAL_ITEM.get();

		if( input.isDamageableItem() && !ModdedEnchantmentHelper.hasEnchant(input, LexRegistry.PERPETUAL.get()) ) {
			if( modBlacklist.contains(ForgeRegistries.ITEMS.getKey(input.getItem()).getNamespace()) ) return false;
			if( itemBlacklist.contains(ForgeRegistries.ITEMS.getKey(input.getItem()).toString()) ) return false;
			return Objects.equals(ForgeRegistries.ITEMS.getKey(addition.getItem()).toString(), id);
		}
		return false;
	}

	@Override
	public @NotNull ItemStack assemble( Container inventory ) {
		ItemStack stack = inventory.getItem(0).copy();
		stack.enchant(LexRegistry.PERPETUAL.get(), 1);

		return stack;
	}

	@Override
	public @NotNull ItemStack getResultItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isAdditionIngredient( ItemStack addition ) {
		String id = EnchantConfig.PERPETUAL_ITEM.get();
		return Objects.equals(ForgeRegistries.ITEMS.getKey(addition.getItem()), id);
	}

	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return LexRegistry.APPLY_PERPETUAL.get();
	}

	public static class Serializer implements RecipeSerializer<ApplyPerpetual> {
		public ApplyPerpetual fromJson(ResourceLocation p_44562_, JsonObject p_44563_) {
			return new ApplyPerpetual(p_44562_);
		}

		public ApplyPerpetual fromNetwork(ResourceLocation p_44565_, FriendlyByteBuf p_44566_) {
			return new ApplyPerpetual(p_44565_);
		}

		public void toNetwork(FriendlyByteBuf p_44553_, ApplyPerpetual p_44554_) {
			p_44554_.base.toNetwork(p_44553_);
			p_44554_.addition.toNetwork(p_44553_);
			p_44553_.writeItem(p_44554_.result);
		}
	}
}
