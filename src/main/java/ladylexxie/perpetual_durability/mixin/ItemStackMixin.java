package ladylexxie.perpetual_durability.mixin;

import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ModdedEnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract ItemStack copy();

    @Inject(at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/ItemStack;getTag()Lnet/minecraft/nbt/CompoundTag;"), method = "isDamageableItem", cancellable = true)
    private void handlePerpetualEnchantment(CallbackInfoReturnable<Boolean> cir) {
        if(ModdedEnchantmentHelper.hasEnchant(this.copy(), LexRegistry.PERPETUAL.get())){
            cir.setReturnValue(false);
        }
    }
}
