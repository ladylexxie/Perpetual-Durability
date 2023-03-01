package ladylexxie.perpetual_durability.integration;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.registry.LexRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

@JeiPlugin
public class PerpetualDurabilityJEIPlugin implements IModPlugin {
	@Override
	public @NotNull ResourceLocation getPluginUid() { return new ResourceLocation(PerpetualDurability.MOD_ID, "main"); }

	@Override
	public void registerRecipes( IRecipeRegistration recipeRegistration ) {
		recipeRegistration.addRecipes(RecipeTypes.SMITHING, ApplyPerpetual.getRecipes());
	}

	@Override
	public void onRuntimeAvailable( IJeiRuntime runtime ) {
		// please tell me there's a better way than this... thing...
		ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
		book.getOrCreateTag().putByte("Unbreakable", (byte) 1);
		book.getOrCreateTag().put("StoredEnchantments", new ListTag());

		ListTag listnbt = book.getOrCreateTag().getList("StoredEnchantments", 10);
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putString("id", LexRegistry.PERPETUAL.getId().toString());
		compoundnbt.putShort("lvl", (short) 1);
		listnbt.add(compoundnbt);

		runtime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, Collections.singleton(book));
	}
}
