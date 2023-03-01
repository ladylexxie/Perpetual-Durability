package ladylexxie.perpetual_durability.event;

import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ModdedEnchantmentHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Tooltip {
	@SubscribeEvent
	public static void tooltips( ItemTooltipEvent e ) {
		ItemStack i = e.getItemStack();
		if( i.getItem() == Items.ENCHANTED_BOOK && ModdedEnchantmentHelper.hasEnchant(i, LexRegistry.PERPETUAL.get()) )
			e.getToolTip().add(Component.translatable("enchantment.perpetual_durability.perpetual.desc").withStyle(ChatFormatting.RED));
	}
}
