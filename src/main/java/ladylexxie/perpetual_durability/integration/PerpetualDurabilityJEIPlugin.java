package ladylexxie.perpetual_durability.integration;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.registry.LexRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

@JeiPlugin
public class PerpetualDurabilityJEIPlugin implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(PerpetualDurability.MOD_ID, "main");
	}

	@Override
	public void registerRecipes( IRecipeRegistration recipeRegistration ) {
		recipeRegistration.addRecipes(ApplyPerpetual.getRecipes(), VanillaRecipeCategoryUid.SMITHING);
	}

	@Override
	public void onRuntimeAvailable( IJeiRuntime runtime ) {
		// please tell me there's a better way than this... thing...
		ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
		book.getOrCreateTag().putByte("Unbreakable", (byte) 1);
		book.getOrCreateTag().put("StoredEnchantments", new ListNBT());

		ListNBT listnbt = book.getOrCreateTag().getList("StoredEnchantments", 10);
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putString("id", LexRegistry.PERPETUAL.getId().toString());
		compoundnbt.putShort("lvl", (short) 1);
		listnbt.add(compoundnbt);

		runtime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM, Collections.singleton(book));
	}
}
