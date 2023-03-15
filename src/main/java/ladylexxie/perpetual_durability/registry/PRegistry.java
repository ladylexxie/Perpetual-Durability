package ladylexxie.perpetual_durability.registry;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.recipe.ApplyPerpetualTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PRegistry {
	private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PerpetualDurability.ID);

	public static final RegistryObject<RecipeSerializer<?>> APPLY_PERPETUAL_TAG = RECIPES.register("apply_perpetual_tag", ApplyPerpetualTag.Serializer::new);
	public static final TagKey<Item> TAG_PERPETUAL = ItemTags.create(PerpetualDurability.asResource("perpetual"));

	public static void init() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		RECIPES.register(eventBus);
	}
}
