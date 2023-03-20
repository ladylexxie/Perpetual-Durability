package ladylexxie.perpetual_durability.integration;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.config.PClientConfig;
import ladylexxie.perpetual_durability.registry.PRegistry;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplyPerpetual {
	public static List<SmithingRecipe> getRecipes() {
		List<SmithingRecipe> recipes = new ArrayList<>();
		if( !PClientConfig.SHOW_JEI_RECIPE.get() ) return recipes;

		List<Item> listOfDamageableItems = ForgeRegistries.ITEMS.getValues().stream().filter(item -> item.isDamageable(null)).collect(Collectors.toList());

		listOfDamageableItems.forEach(item -> {
			ResourceLocation itemID = PUtils.getID(item);
			ItemStack stack = new ItemStack(item);
			if(!PUtils.canPerpetuate(stack) ) return;

			ResourceLocation recipeID = PerpetualDurability.asResource("jei.perpetuate." + itemID.toString().replace(":", "."));
			stack.getOrCreateTag().putBoolean("Unbreakable", true);
			stack.setDamageValue(0);

			SmithingRecipe recipe = new SmithingRecipe(recipeID, Ingredient.of(item), Ingredient.of(PRegistry.TAG_PERPETUAL), stack);
			recipes.add(recipe);
		});

		return recipes;
	}
}
