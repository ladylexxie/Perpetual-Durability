package ladylexxie.perpetual_durability.event;

import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ItemStackUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TickListener {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;

        for(int i = 0; i < player.inventory.getContainerSize(); i++){
            ItemStack stack = player.inventory.getItem(i);

            if(stack != ItemStack.EMPTY && stack.isDamageableItem() && stack.isDamaged() && ItemStackUtils.hasEnchant(stack, LexRegistry.PERPETUAL.get())) {
                stack.setDamageValue(0);
            }
        }
    }
}
