package ladylexxie.perpetual_durability.event;

import ladylexxie.perpetual_durability.PerpetualDurability;
import ladylexxie.perpetual_durability.registry.PRegistry;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TickListener {
	@SubscribeEvent
	public static void onPlayerTick( TickEvent.PlayerTickEvent event ) {
		if( event.side.isClient() ) return;
		Player player = event.player;

		for( int i = 0; i < player.getInventory().getContainerSize(); i++ ) {
			ItemStack stack = player.getInventory().getItem(i);
			if( stack == ItemStack.EMPTY ) continue;
			if( !PUtils.hasEnchant(stack, PRegistry.ENCHANTMENT_PERPETUAL.get()) ) continue;

			stack.getOrCreateTag().putBoolean("Unbreakable", true);
			stack.setDamageValue(0);
			PUtils.removeEnchant(stack, PRegistry.ENCHANTMENT_PERPETUAL.get());
			if( stack.getEnchantmentTags().isEmpty() ) stack.removeTagKey("Enchantments");
			PerpetualDurability.LOGGER.info(player.getDisplayName().getString() + "'s " + stack.getItem().getName(null).getString() + " had an enchant that has been swapped with a tag.");
		}
	}
}
