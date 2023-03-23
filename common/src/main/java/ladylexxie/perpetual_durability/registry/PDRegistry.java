package ladylexxie.perpetual_durability.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import ladylexxie.perpetual_durability.PDConstants;
import ladylexxie.perpetual_durability.PerpetualDurability;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.UpgradeRecipe;

public class PDRegistry {
	private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(PDConstants.ID, Registry.RECIPE_SERIALIZER_REGISTRY);
	public static final RegistrySupplier<RecipeSerializer<?>> PERPETUATE = RECIPES.register("perpetuate", UpgradeRecipe.Serializer::new);
	public static final TagKey<Item> TAG_PERPETUAL = TagKey.create(Registry.ITEM_REGISTRY, PerpetualDurability.id("perpetual"));

	public static void init() {
		RECIPES.register();
	}
}
