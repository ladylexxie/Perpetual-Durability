package ladylexxie.perpetual_durability.compat;

import ladylexxie.perpetual_durability.PerpetualDurability;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class PDJEIPlugin implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() { return PerpetualDurability.id("main"); }

	@Override
	public void registerRecipes( IRecipeRegistration recipeRegistration ) {
		recipeRegistration.addRecipes(RecipeTypes.SMITHING, CompatHelper.getPerpetuateRecipes(CompatHelper.JEI));
	}
}