package ladylexxie.perpetual_durability.integration;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.config.PClientConfig;
import ladylexxie.perpetual_durability.registry.PRegistry;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ApplyPerpetual {
	public static List<UpgradeRecipe> getRecipes() {
		List<UpgradeRecipe> recipes = new ArrayList<>();
		if( !PClientConfig.SHOW_JEI_RECIPE.get() ) return recipes;

		List<Item> listOfDamageableItems = ForgeRegistries.ITEMS.getValues().stream().filter(item -> item.isDamageable(null)).toList();

		listOfDamageableItems.forEach(item -> {
			ResourceLocation itemID = PUtils.getID(item);
			ItemStack stack = new ItemStack(item);
			if(!PUtils.canPerpetuate(stack) ) return;

			ResourceLocation recipeID = PerpetualDurability.asResource("jei.perpetuate." + itemID.toString().replace(":", "."));
			stack.getOrCreateTag().putBoolean("Unbreakable", true);
			stack.setDamageValue(0);

			UpgradeRecipe recipe = new UpgradeRecipe(recipeID, Ingredient.of(item), Ingredient.of(PRegistry.TAG_PERPETUAL), stack);
			recipes.add(recipe);
		});

		return recipes;
	}
}
