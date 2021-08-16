package ladylexxie.perpetual_durability.integration;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.config.EnchantConfig;
import ladylexxie.perpetual_durability.registry.LexRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ApplyPerpetual {
    public static List<SmithingRecipe> getRecipes(){
        List<SmithingRecipe> recipes = new ArrayList<>();
        List<Item> list = new ArrayList<>();

        ForgeRegistries.ITEMS.getValues().forEach(item -> {
            if(item.isDamageable(null)){
                list.add(item);
            }
        });

        String configItem = EnchantConfig.PERPETUAL_ITEM.get();
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(configItem));

        Ingredient ingredient = Ingredient.of(item);

        list.forEach(itemList -> {
            ResourceLocation id = new ResourceLocation(PerpetualDurability.MOD_ID, "jei.apply_perpetual." + itemList.getRegistryName());
            ItemStack output = new ItemStack(itemList);
            output.enchant(LexRegistry.PERPETUAL.get(), 1);
            SmithingRecipe recipe = new SmithingRecipe(id, Ingredient.of(itemList), ingredient, output);

            recipes.add(recipe);
        });

        return recipes;
    }
}
