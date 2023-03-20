package ladylexxie.perpetual_durability.integration;

import ladylexxie.perpetual_durability.PerpetualDurability;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class PerpetualDurabilityJEIPlugin implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(PerpetualDurability.ID, "main");
	}

	@Override
	public void registerRecipes( IRecipeRegistration recipeRegistration ) {
		recipeRegistration.addRecipes(ApplyPerpetual.getRecipes(), VanillaRecipeCategoryUid.SMITHING);
	}
}
