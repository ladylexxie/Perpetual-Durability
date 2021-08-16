package ladylexxie.perpetual_durability.registry;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.enchant.ModdedEnchant;
import ladylexxie.perpetual_durability.recipe.ApplyPerpetual;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LexRegistry {
    private static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PerpetualDurability.MOD_ID);
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PerpetualDurability.MOD_ID);

    private static final EnchantmentType DESTRUCTIBLE = EnchantmentType.create("DESTRUCTIBLE", item -> item.isDamageable(null));

    public static final RegistryObject<Enchantment> PERPETUAL = ENCHANTMENTS.register("perpetual",() -> new ModdedEnchant(Enchantment.Rarity.COMMON, DESTRUCTIBLE, EquipmentSlotType.MAINHAND));

    public static void init(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RECIPES.register(eventBus);
        ENCHANTMENTS.register(eventBus);
    }

    public static final RegistryObject<IRecipeSerializer<?>> APPLY_PERPETUAL = RECIPES.register("apply_perpetual", () -> new SpecialRecipeSerializer<>(ApplyPerpetual::new));
}
