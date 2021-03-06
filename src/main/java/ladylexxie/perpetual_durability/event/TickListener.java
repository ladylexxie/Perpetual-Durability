package ladylexxie.perpetual_durability.event;

import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ModdedEnchantmentHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TickListener {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        Player player = event.player;

        for(int i = 0; i < player.getInventory().getContainerSize(); i++){
            ItemStack stack = player.getInventory().getItem(i);

            if(stack != ItemStack.EMPTY && stack.isDamageableItem() && stack.isDamaged() && ModdedEnchantmentHelper.hasEnchant(stack, LexRegistry.PERPETUAL.get())) {
                stack.setDamageValue(0);
            }
        }
    }
}
