package ladylexxie.perpetual_durability.recipe;

import ladylexxie.perpetual_durability.registry.PRegistry;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PerpetuateRecipe extends SmithingTransformRecipe {
    public PerpetuateRecipe(ResourceLocation id) {
        super(id, Ingredient.EMPTY, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
    }

    @Override
    public boolean matches(Container inventory, Level level) {
        ItemStack input = inventory.getItem(0);
        ItemStack addition = inventory.getItem(1);

        if (!addition.is(PRegistry.TAG_PERPETUAL)) return false;
        if (!PUtils.canPerpetuate(input)) return false;
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(Container inventory, RegistryAccess registryAccess) {
        ItemStack stack = inventory.getItem(0).copy();
        stack.getOrCreateTag().putBoolean("Unbreakable", true);
        stack.setDamageValue(0);
        return stack;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack template) {
        return Ingredient.EMPTY.test(template);
    }

    @Override
    public boolean isBaseIngredient(ItemStack base) {
        return PUtils.canPerpetuate(base);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack addition) {
        return addition.is(PRegistry.TAG_PERPETUAL);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return PRegistry.PERPETUATE.get();
    }
}
