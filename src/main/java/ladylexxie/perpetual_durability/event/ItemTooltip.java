package ladylexxie.perpetual_durability.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import ladylexxie.perpetual_durability.config.PClientConfig;
import ladylexxie.perpetual_durability.registry.PRegistry;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber( value = Dist.CLIENT )
public class ItemTooltip {
	private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

	private static void showEnchantedBookWarning( ItemStack stack, List<Component> tooltip ) {
		// Show a red warning if player somehow got the old enchanted book
		if( stack.getItem() != Items.ENCHANTED_BOOK ) return;
		if( !PUtils.hasEnchant(stack, PRegistry.ENCHANTMENT_PERPETUAL.get()) ) return;
		tooltip.add(new TranslatableComponent("enchantment.perpetual_durability.perpetual.desc").withStyle(ChatFormatting.RED));
	}

	private static void showTagDescription( ItemStack stack, List<Component> tooltip ) {
		// Show if the item can be used to create unbreakable items
		if( !PClientConfig.TAG_TOOLTIP.get() ) return;
		if( !stack.is(PRegistry.TAG_PERPETUAL) ) return;
		tooltip.add(new TranslatableComponent("tooltip.perpetual_durability.perpetual.desc").withStyle(ChatFormatting.LIGHT_PURPLE));
	}

	private static void coloredName( ItemStack stack, List<Component> tooltip ) {
		if( stack.hasTag() && !stack.getOrCreateTag().getBoolean("Unbreakable") ) return;

		for( Component line : tooltip ) {
			if( !line.getString().equals(new TranslatableComponent("item.unbreakable").getString()) ) continue;
			if( PClientConfig.COLORFUL_TOOLTIP.get() ) {
				String perpetual = new TranslatableComponent("tooltip.perpetual_durability.perpetual.name").getString();
				tooltip.set(tooltip.indexOf(line), PUtils.animateTextColor(perpetual));
			} else {
				tooltip.set(tooltip.indexOf(line), new TranslatableComponent("tooltip.perpetual_durability.perpetual.name").withStyle(ChatFormatting.DARK_RED));
			}
			break;
		}
	}

	private static void showDebugTags( ItemStack stack, List<Component> tooltip, ItemTooltipEvent event ) {
		// Tags on tooltips
		if( !PClientConfig.ENABLE_DEBUG_TAGS.get() ) return;
		if( !event.getFlags().isAdvanced() ) return;
		if( stack.getTags().findAny().isEmpty() ) return;

		if( Screen.hasControlDown() ) {
			tooltip.add(new TranslatableComponent("tooltip.perpetual_durability.debug_tags_item").withStyle(ChatFormatting.LIGHT_PURPLE));
			tooltip.add(new TextComponent("-".repeat(30)).withStyle(ChatFormatting.DARK_PURPLE));
			stack.getTags().forEach(tag -> tooltip.add(new TextComponent(tag.location().toString()).withStyle(ChatFormatting.DARK_GRAY)));

			tooltip.add(new TranslatableComponent("tooltip.perpetual_durability.debug_tags_block").withStyle(ChatFormatting.LIGHT_PURPLE));
			tooltip.add(new TextComponent("-".repeat(30)).withStyle(ChatFormatting.DARK_PURPLE));
			Block.byItem(stack.getItem()).defaultBlockState().getTags().forEach(tag -> tooltip.add(new TextComponent(tag.location().toString()).withStyle(ChatFormatting.DARK_GRAY)));
		} else {
			tooltip.add(new TranslatableComponent("tooltip.perpetual_durability.debug_tags").withStyle(ChatFormatting.GOLD));
		}
	}

	private static void showDebugNBT( ItemStack stack, List<Component> tooltip, ItemTooltipEvent event ) {
		// NBT on tooltips
		if( !PClientConfig.ENABLE_DEBUG_NBT.get() ) return;
		if( !event.getFlags().isAdvanced() ) return;
		if( !stack.hasTag() ) return;

		if( Screen.hasAltDown() ) {
			tooltip.add(new TextComponent("NBT:").withStyle(ChatFormatting.LIGHT_PURPLE));
			tooltip.add(new TextComponent("-".repeat(30)).withStyle(ChatFormatting.DARK_PURPLE));

			String json = GSON.toJson(JsonParser.parseString(stack.getTag().getAsString()));
			for( String line : json.split("\n") )
				tooltip.add(new TextComponent(line).withStyle(ChatFormatting.DARK_GRAY));
		} else {
			tooltip.add(new TranslatableComponent("tooltip.perpetual_durability.debug_nbt").withStyle(ChatFormatting.GOLD));
		}
	}

	@SubscribeEvent
	public static void onItemTooltip( ItemTooltipEvent e ) {
		List<Component> tooltip = e.getToolTip();
		ItemStack itemStack = e.getItemStack();

		showEnchantedBookWarning(itemStack, tooltip);
		showTagDescription(itemStack, tooltip);
		coloredName(itemStack, tooltip);
		showDebugTags(itemStack, tooltip, e);
		showDebugNBT(itemStack, tooltip, e);
	}
}
