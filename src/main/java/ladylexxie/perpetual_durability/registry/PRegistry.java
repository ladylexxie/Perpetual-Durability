package ladylexxie.perpetual_durability.registry;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.enchant.PerpetualEnchant;
import ladylexxie.perpetual_durability.recipe.PerpetuateRecipe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PRegistry {
    private static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PerpetualDurability.ID);
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PerpetualDurability.ID);
    public static final EnchantmentType NOTHING = EnchantmentType.create("NOTHING", null);
    public static final RegistryObject<Enchantment> ENCHANTMENT_PERPETUAL = ENCHANTMENTS.register("perpetual", PerpetualEnchant::new);
    public static final RegistryObject<IRecipeSerializer<?>> PERPETUATE = RECIPES.register("perpetuate", () -> new SpecialRecipeSerializer<>(PerpetuateRecipe::new));
    public static final ITag.INamedTag<Item> TAG_PERPETUAL = ItemTags.bind(PerpetualDurability.asResource("perpetual").toString());

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RECIPES.register(eventBus);
        ENCHANTMENTS.register(eventBus);
    }
}
