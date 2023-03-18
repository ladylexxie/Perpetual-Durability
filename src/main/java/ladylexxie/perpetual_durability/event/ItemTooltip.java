package ladylexxie.perpetual_durability.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import ladylexxie.perpetual_durability.config.PClientConfig;
import ladylexxie.perpetual_durability.registry.PRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber( value = Dist.CLIENT )
public class ItemTooltip {
	private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

	private static void showTagDescription( ItemStack stack, List<Component> tooltip ) {
		// Show if the item can be used to create unbreakable items
		if( !PClientConfig.TAG_TOOLTIP.get() ) return;
		if( !stack.is(PRegistry.TAG_PERPETUAL) ) return;
		tooltip.add(Component.translatable("tooltip.perpetual_durability.perpetual.desc").withStyle(ChatFormatting.LIGHT_PURPLE));
	}

	private static void renameUnbreakable( ItemStack stack, List<Component> tooltip ) {
		// Changing the color of the Unbreakable tooltip text
		if( !stack.getOrCreateTag().getBoolean("Unbreakable") ) return;
		tooltip.forEach(line -> {
			if( !line.getString().equals("Unbreakable") ) return;
			tooltip.set(tooltip.indexOf(line), Component.translatable("tooltip.perpetual_durability.perpetual.name").withStyle(ChatFormatting.DARK_PURPLE));
		});
	}

	private static void showDebugTags( ItemStack stack, List<Component> tooltip, ItemTooltipEvent event ) {
		// Tags on tooltips
		if( !PClientConfig.ENABLE_DEBUG_TAGS.get() ) return;
		if( !event.getFlags().isAdvanced() ) return;
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

	private static void showDebugNBT( ItemStack stack, List<Component> tooltip, ItemTooltipEvent event ) {
		// NBT on tooltips
		if( !PClientConfig.ENABLE_DEBUG_NBT.get() ) return;
		if( !event.getFlags().isAdvanced() ) return;
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

	@SubscribeEvent
	public static void onItemTooltip( ItemTooltipEvent e ) {
		List<Component> tooltip = e.getToolTip();
		ItemStack itemStack = e.getItemStack();

		showTagDescription(itemStack, tooltip);
		renameUnbreakable(itemStack, tooltip);
		showDebugTags(itemStack, tooltip, e);
		showDebugNBT(itemStack, tooltip, e);
	}
}
