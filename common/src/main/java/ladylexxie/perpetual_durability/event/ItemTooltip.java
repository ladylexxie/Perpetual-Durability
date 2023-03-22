package ladylexxie.perpetual_durability.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import ladylexxie.perpetual_durability.PDClient;
import ladylexxie.perpetual_durability.registry.PDRegistry;
import ladylexxie.perpetual_durability.util.PDUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class ItemTooltip {
	private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

	private static void showEnchantedBookWarning( ItemStack stack, List<Component> tooltip, TooltipFlag tooltipFlag ) {
		if( stack.getItem() != Items.ENCHANTED_BOOK ) return;
		tooltip.add(Component.translatable("enchantment.perpetual_durability.perpetual.desc").withStyle(ChatFormatting.RED));
	}

	private static void showTagDescription( ItemStack stack, List<Component> tooltip, TooltipFlag tooltipFlag ) {
		if( !PDClient.CLIENT_CONFIG.tagTooltip ) return;
		if( !stack.is(PDRegistry.TAG_PERPETUAL) ) return;
		tooltip.add(Component.translatable("tooltip.perpetual_durability.perpetual.desc").withStyle(ChatFormatting.LIGHT_PURPLE));
	}

	private static void coloredName( ItemStack stack, List<Component> tooltip, TooltipFlag tooltipFlag ) {
		if( stack.hasTag() && !stack.getOrCreateTag().getBoolean("Unbreakable") ) return;

		for( Component line : tooltip ) {
			if( !line.getString().equals(Component.translatable("item.unbreakable").getString()) ) continue;
			if( PDClient.CLIENT_CONFIG.colorfulTooltip ) {
				String perpetual = Component.translatable("tooltip.perpetual_durability.perpetual.name").getString();
				tooltip.set(tooltip.indexOf(line), PDUtils.animateTextColor(perpetual));
			} else {
				tooltip.set(tooltip.indexOf(line), Component.translatable("tooltip.perpetual_durability.perpetual.name").withStyle(ChatFormatting.DARK_RED));
			}
			break;
		}
	}

	private static void showDebugTags( ItemStack stack, List<Component> tooltip, TooltipFlag tooltipFlag ) {
		if( !PDClient.CLIENT_CONFIG.debug.enableDebugTags ) return;
		if( !tooltipFlag.isAdvanced() ) return;
		if( stack.getTags().findAny().isEmpty() ) return;

		if( Screen.hasControlDown() ) {
			tooltip.add(Component.translatable("tooltip.perpetual_durability.debug_tags_item").withStyle(ChatFormatting.LIGHT_PURPLE));
			tooltip.add(Component.literal("-".repeat(30)).withStyle(ChatFormatting.DARK_PURPLE));
			stack.getTags().forEach(tag -> tooltip.add(Component.literal(tag.location().toString()).withStyle(ChatFormatting.DARK_GRAY)));

			tooltip.add(Component.translatable("tooltip.perpetual_durability.debug_tags_block").withStyle(ChatFormatting.LIGHT_PURPLE));
			tooltip.add(Component.literal("-".repeat(30)).withStyle(ChatFormatting.DARK_PURPLE));
			Block.byItem(stack.getItem()).defaultBlockState().getTags().forEach(tag -> tooltip.add(Component.literal(tag.location().toString()).withStyle(ChatFormatting.DARK_GRAY)));
		} else {
			tooltip.add(Component.translatable("tooltip.perpetual_durability.debug_tags").withStyle(ChatFormatting.GOLD));
		}
	}

	private static void showDebugNBT( ItemStack stack, List<Component> tooltip, TooltipFlag tooltipFlag ) {
		if( !PDClient.CLIENT_CONFIG.debug.enableDebugNbt ) return;
		if( !tooltipFlag.isAdvanced() ) return;
		if( !stack.hasTag() ) return;

		if( Screen.hasAltDown() ) {
			tooltip.add(Component.literal("NBT:").withStyle(ChatFormatting.LIGHT_PURPLE));
			tooltip.add(Component.literal("-".repeat(30)).withStyle(ChatFormatting.DARK_PURPLE));

			String json = GSON.toJson(JsonParser.parseString(stack.getTag().getAsString()));
			for( String line : json.split("\n") )
				tooltip.add(Component.literal(line).withStyle(ChatFormatting.DARK_GRAY));
		} else {
			tooltip.add(Component.translatable("tooltip.perpetual_durability.debug_nbt").withStyle(ChatFormatting.GOLD));
		}
	}

	public static void onItemTooltip( ItemStack stack, List<Component> tooltip, TooltipFlag tooltipFlag ) {
		showEnchantedBookWarning(stack, tooltip, tooltipFlag);
		showTagDescription(stack, tooltip, tooltipFlag);
		coloredName(stack, tooltip, tooltipFlag);
		showDebugTags(stack, tooltip, tooltipFlag);
		showDebugNBT(stack, tooltip, tooltipFlag);
	}
}
