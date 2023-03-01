package ladylexxie.perpetual_durability.event;

import ladylexxie.perpetual_durability.registry.LexRegistry;
import ladylexxie.perpetual_durability.util.ModdedEnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Tooltip {
	@SubscribeEvent
	public static void tooltips( ItemTooltipEvent e ) {
		ItemStack i = e.getItemStack();
		if( i.getItem() == Items.ENCHANTED_BOOK && ModdedEnchantmentHelper.hasEnchant(i, LexRegistry.PERPETUAL.get()) )
			e.getToolTip().add(new TranslationTextComponent("enchantment.perpetual_durability.perpetual.desc").withStyle(TextFormatting.RED));
	}
}
