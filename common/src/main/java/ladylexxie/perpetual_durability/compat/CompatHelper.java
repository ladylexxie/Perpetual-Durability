package ladylexxie.perpetual_durability.compat;

import ladylexxie.perpetual_durability.PDClient;
import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.registry.PDRegistry;
import ladylexxie.perpetual_durability.util.PDUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.UpgradeRecipe;

import java.util.ArrayList;
import java.util.List;

public class CompatHelper {
	public static List<UpgradeRecipe> getPerpetuateRecipes( XEI mod ) {
		List<UpgradeRecipe> recipes = new ArrayList<>();
		if( !PDClient.CLIENT_CONFIG.showRecipes ) return recipes;

		List<Item> listOfDamageableItems = Registry.ITEM.stream().filter(Item::canBeDepleted).toList();

		listOfDamageableItems.forEach(item -> {
			ResourceLocation itemID = item.arch$registryName();
			ItemStack stack = new ItemStack(item);
			if( !PDUtils.canPerpetuate(stack) ) return;

			ResourceLocation recipeID = PerpetualDurability.id(mod + "/perpetuate/" + itemID.toString().replace(":", "."));
			stack.getOrCreateTag().putBoolean("Unbreakable", true);
			stack.setDamageValue(0);

			UpgradeRecipe recipe = new UpgradeRecipe(recipeID, Ingredient.of(item), Ingredient.of(PDRegistry.TAG_PERPETUAL), stack);
			recipes.add(recipe);
		});

		return recipes;
	}

	public enum XEI {
		REI("rei"),
		JEI("jei");

		private final String mod;

		XEI( String id ) { this.mod = id; }

		@Override
		public String toString() { return mod; }
	}
}
