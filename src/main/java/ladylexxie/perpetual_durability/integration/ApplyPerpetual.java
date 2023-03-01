package ladylexxie.perpetual_durability.integration;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.config.EnchantConfig;
import ladylexxie.perpetual_durability.registry.LexRegistry;
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
		List<Item> list = new ArrayList<>();

		ForgeRegistries.ITEMS.getValues().forEach(item -> { if( item.isDamageable(null) ) { list.add(item); } });

		String configItem = EnchantConfig.PERPETUAL_ITEM.get();
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(configItem));

		Ingredient ingredient = Ingredient.of(item.getDefaultInstance());

		list.forEach(itemList -> {
			ResourceLocation itemID = ForgeRegistries.ITEMS.getKey(itemList);
			ResourceLocation id = new ResourceLocation(PerpetualDurability.MOD_ID, "jei.apply_perpetual." + itemID.getNamespace() + "." + itemID.getPath());
			ItemStack output = new ItemStack(itemList);
			output.enchant(LexRegistry.PERPETUAL.get(), 1);
			UpgradeRecipe recipe = new UpgradeRecipe(id, Ingredient.of(itemList), ingredient, output);

			recipes.add(recipe);
		});

		return recipes;
	}
}
