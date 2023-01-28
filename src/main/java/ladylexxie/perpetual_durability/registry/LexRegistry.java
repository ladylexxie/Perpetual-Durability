package ladylexxie.perpetual_durability.registry;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.enchant.PerpetualEnchant;
import ladylexxie.perpetual_durability.recipe.ApplyPerpetual;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LexRegistry {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PerpetualDurability.MOD_ID);
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PerpetualDurability.MOD_ID);
    public static final EnchantmentCategory DESTRUCTIBLE = EnchantmentCategory.create("DESTRUCTIBLE", item -> item.isDamageable(null));
    public static final RegistryObject<Enchantment> PERPETUAL = ENCHANTMENTS.register("perpetual", PerpetualEnchant::new);
    public static final RegistryObject<RecipeSerializer<?>> APPLY_PERPETUAL = RECIPES.register("apply_perpetual", ApplyPerpetual.Serializer::new);

    public static void init(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RECIPES.register(eventBus);
        ENCHANTMENTS.register(eventBus);
    }
}
