package ladylexxie.perpetual_durability.event;

import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ModdedEnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TickListener {
	@SubscribeEvent
	public static void onPlayerTick( TickEvent.PlayerTickEvent event ) {
		PlayerEntity player = event.player;

		for( int i = 0; i < player.inventory.getContainerSize(); i++ ) {
			ItemStack stack = player.inventory.getItem(i);

			if( stack != ItemStack.EMPTY && ModdedEnchantmentHelper.hasEnchant(stack, LexRegistry.PERPETUAL.get()) ) {
				CompoundNBT nbt = stack.getOrCreateTag();
				nbt.putBoolean("Unbreakable", true);
				stack.setTag(nbt);
				stack.setDamageValue(0);
				stack.getEnchantmentTags().removeIf(filter -> filter.toString().equals("{lvl:1s,id:\"perpetual_durability:perpetual\"}"));
				if(stack.getEnchantmentTags().isEmpty()) stack.removeTagKey("Enchantments");
			}
		}
	}
}
