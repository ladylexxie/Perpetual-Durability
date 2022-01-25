package ladylexxie.perpetual_durability.recipe;

import ladylexxie.perpetual_durability.config.EnchantConfig;
import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ModdedEnchantmentHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ApplyPerpetual extends UpgradeRecipe {
    public ApplyPerpetual(ResourceLocation recipeID){
        super(recipeID, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
    }

    @Override
    public boolean matches(Container inventory, @NotNull Level world){
        ItemStack input = inventory.getItem(0);
        ItemStack addition = inventory.getItem(1);
        String id = EnchantConfig.PERPETUAL_ITEM.get();

        if(input.isDamageableItem() && !ModdedEnchantmentHelper.hasEnchant(input, LexRegistry.PERPETUAL.get())){
            return Objects.equals(Objects.requireNonNull(addition.getItem().getRegistryName()).toString(), id);
        }
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(Container inventory){
        ItemStack stack = inventory.getItem(0).copy();
        stack.enchant(LexRegistry.PERPETUAL.get(), 1);

        return stack;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isAdditionIngredient(ItemStack addition) {
        String id = EnchantConfig.PERPETUAL_ITEM.get();
        return Objects.equals(Objects.requireNonNull(addition.getItem().getRegistryName()).toString(), id);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return LexRegistry.APPLY_PERPETUAL.get();
    }
}
