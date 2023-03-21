package ladylexxie.perpetual_durability.registry;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.enchant.PerpetualEnchant;
import ladylexxie.perpetual_durability.recipe.PerpetuateRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PRegistry {
	private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PerpetualDurability.ID);
	private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PerpetualDurability.ID);
	public static final EnchantmentCategory NOTHING = EnchantmentCategory.create("NOTHING", null);
	public static final RegistryObject<Enchantment> ENCHANTMENT_PERPETUAL = ENCHANTMENTS.register("perpetual", PerpetualEnchant::new);
	public static final RegistryObject<RecipeSerializer<?>> PERPETUATE = RECIPES.register("perpetuate", () -> new SimpleRecipeSerializer<>(PerpetuateRecipe::new));
	public static final TagKey<Item> TAG_PERPETUAL = ItemTags.create(PerpetualDurability.asResource("perpetual"));

	public static void init() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		RECIPES.register(eventBus);
		ENCHANTMENTS.register(eventBus);
	}
}
