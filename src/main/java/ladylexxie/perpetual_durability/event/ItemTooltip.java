package ladylexxie.perpetual_durability.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ladylexxie.perpetual_durability.config.PClientConfig;
import ladylexxie.perpetual_durability.registry.PRegistry;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber( value = Dist.CLIENT )
public class ItemTooltip {
	private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

	private static void showEnchantedBookWarning( ItemStack stack, List<ITextComponent> tooltip ) {
		// Show a red warning if player somehow got the old enchanted book
		if( stack.getItem() != Items.ENCHANTED_BOOK ) return;
		if( !PUtils.hasEnchant(stack, PRegistry.ENCHANTMENT_PERPETUAL.get()) ) return;
		tooltip.add(new TranslationTextComponent("enchantment.perpetual_durability.perpetual.desc").withStyle(TextFormatting.RED));
	}

	private static void showTagDescription( ItemStack stack, List<ITextComponent> tooltip ) {
		// Show if the item can be used to create unbreakable items
		if( !PClientConfig.TAG_TOOLTIP.get() ) return;
		if( !stack.getItem().is(PRegistry.TAG_PERPETUAL) ) return;
		tooltip.add(new TranslationTextComponent("tooltip.perpetual_durability.perpetual.desc").withStyle(TextFormatting.LIGHT_PURPLE));
	}

	private static void coloredName( ItemStack stack, List<ITextComponent> tooltip ) {
		if( stack.hasTag() && !stack.getOrCreateTag().getBoolean("Unbreakable") ) return;

		for( ITextComponent line : tooltip ) {
			if( !line.getString().equals(new TranslationTextComponent("item.unbreakable").getString()) ) continue;
			if( PClientConfig.COLORFUL_TOOLTIP.get() ) {
				String perpetual = new TranslationTextComponent("tooltip.perpetual_durability.perpetual.name").getString();
				tooltip.set(tooltip.indexOf(line), PUtils.animateTextColor(perpetual));
			} else {
				tooltip.set(tooltip.indexOf(line), new TranslationTextComponent("tooltip.perpetual_durability.perpetual.name").withStyle(TextFormatting.DARK_RED));
			}
			break;
		}
	}

	private static void showDebugTags( ItemStack stack, List<ITextComponent> tooltip, ItemTooltipEvent event ) {
		// Tags on tooltips
		if( !PClientConfig.ENABLE_DEBUG_TAGS.get() ) return;
		if( !event.getFlags().isAdvanced() ) return;
		if( stack.getItem().getTags().isEmpty() ) return;

		if( Screen.hasControlDown() ) {
			tooltip.add(new TranslationTextComponent("tooltip.perpetual_durability.debug_tags_item").withStyle(TextFormatting.LIGHT_PURPLE));
			tooltip.add(new StringTextComponent("------------------------------").withStyle(TextFormatting.DARK_PURPLE));
			stack.getItem().getTags().forEach(tag -> tooltip.add(new StringTextComponent(tag.toString()).withStyle(TextFormatting.DARK_GRAY)));

			tooltip.add(new TranslationTextComponent("tooltip.perpetual_durability.debug_tags_block").withStyle(TextFormatting.LIGHT_PURPLE));
			tooltip.add(new StringTextComponent("------------------------------").withStyle(TextFormatting.DARK_PURPLE));
			Block.byItem(stack.getItem()).getTags().forEach(tag -> tooltip.add(new StringTextComponent(tag.toString()).withStyle(TextFormatting.DARK_GRAY)));
		} else {
			tooltip.add(new TranslationTextComponent("tooltip.perpetual_durability.debug_tags").withStyle(TextFormatting.GOLD));
		}
	}

	private static void showDebugNBT( ItemStack stack, List<ITextComponent> tooltip, ItemTooltipEvent event ) {
		// NBT on tooltips
		if( !PClientConfig.ENABLE_DEBUG_NBT.get() ) return;
		if( !event.getFlags().isAdvanced() ) return;
		if( !stack.hasTag() ) return;

		if( Screen.hasAltDown() ) {
			tooltip.add(new StringTextComponent("NBT:").withStyle(TextFormatting.LIGHT_PURPLE));
			tooltip.add(new StringTextComponent("------------------------------").withStyle(TextFormatting.DARK_PURPLE));

			String json = GSON.toJson(stack.getTag().getAsString());
			for( String line : json.split("\n") )
				tooltip.add(new StringTextComponent(line).withStyle(TextFormatting.DARK_GRAY));
		} else {
			tooltip.add(new TranslationTextComponent("tooltip.perpetual_durability.debug_nbt").withStyle(TextFormatting.GOLD));
		}
	}

	@SubscribeEvent
	public static void onItemTooltip( ItemTooltipEvent e ) {
		List<ITextComponent> tooltip = e.getToolTip();
		ItemStack itemStack = e.getItemStack();

		showEnchantedBookWarning(itemStack, tooltip);
		showTagDescription(itemStack, tooltip);
		coloredName(itemStack, tooltip);
		showDebugTags(itemStack, tooltip, e);
		showDebugNBT(itemStack, tooltip, e);
	}
}
