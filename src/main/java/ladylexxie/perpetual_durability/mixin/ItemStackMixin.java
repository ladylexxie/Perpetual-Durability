package ladylexxie.perpetual_durability.mixin;

import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ModdedEnchantmentHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract ItemStack copy();

    @Inject(at = @At(value = "HEAD"), method = "hurt", cancellable = true)
    private void handleIndestructibleEnchantment(int damage, Random rand, ServerPlayer player, CallbackInfoReturnable<Boolean> info){
        if(ModdedEnchantmentHelper.hasEnchant(this.copy(), LexRegistry.PERPETUAL.get())){
            info.setReturnValue(false);
        }
    }
}
