package ladylexxie.perpetual_durability.compat;

import ladylexxie.perpetual_durability.recipe.PerpetuateRecipe;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.plugin.common.displays.DefaultSmithingDisplay;
import net.minecraft.world.item.crafting.RecipeType;

public class PDREIPlugin implements REIClientPlugin {
	@Override
	public void registerDisplays( DisplayRegistry registry ) {
//		List<UpgradeRecipe> recipes = CompatHelper.getPerpetuateRecipes(CompatHelper.XEI.REI);
//		for( UpgradeRecipe recipe : recipes ) {
			//			registry.get(BuiltinClientPlugin.SMITHING).add(new DefaultSmithingDisplay(recipe));
//			registry.registerRecipeFiller(PerpetuateRecipe.class, RecipeType.SMITHING, () -> new DefaultSmithingDisplay(recipe));
//		}
			registry.registerRecipeFiller(PerpetuateRecipe.class, RecipeType.SMITHING, DefaultSmithingDisplay::new);

	}
}
