package ladylexxie.perpetual_durability.util;

import ladylexxie.perpetual_durability.config.PCommonConfig;
import ladylexxie.perpetual_durability.registry.PRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PUtils {
	private static int timer = 255;
	private static IFormattableTextComponent perpetualTooltip = new StringTextComponent("");
	private static final double interval = Math.PI / 128;
	private static final YearMonth currentYearMonth = YearMonth.now();

	private PUtils() { }

	private static final List<String> modBlacklist = new ArrayList<>(PCommonConfig.MOD_BLACKLIST.get());
	private static final List<String> itemBlacklist = new ArrayList<>(PCommonConfig.ITEM_BLACKLIST.get());
	private static final List<String> tagsBlacklist = new ArrayList<>(PCommonConfig.TAGS_BLACKLIST.get());

	public static ItemStack getItemStackFromID( ResourceLocation id ) { return getItemFromID(id).getDefaultInstance(); }
	public static Item getItemFromID( ResourceLocation id ) { return ForgeRegistries.ITEMS.getValue(id); }

	public static ResourceLocation getID( Item item ) { return ForgeRegistries.ITEMS.getKey(item); }
	public static ResourceLocation getID( ItemStack stack ) { return getID(stack.getItem()); }

	public static boolean hasEnchant( ItemStack stack, Enchantment enchantment ) { return EnchantmentHelper.getEnchantments(stack).containsKey(enchantment); }
	public static void removeEnchant( ItemStack stack, Enchantment enchantment ){ stack.getEnchantmentTags().removeIf(filter -> filter.toString().contains(enchantment.getDescriptionId())); }

	public static boolean canPerpetuate( ItemStack stack ) {
		if( !stack.isDamageableItem() ) return false;
		ResourceLocation id = getID(stack);
		if( hasEnchant(stack, PRegistry.ENCHANTMENT_PERPETUAL.get()) ) return false;
		if( modBlacklist.contains(id.getNamespace()) ) return false;
		if( itemBlacklist.contains(id.toString()) ) return false;
		Set<String> tagsBlacklistSet = new HashSet<>(tagsBlacklist);
		if( stack.getItem().getTags().stream().anyMatch(tag -> tagsBlacklistSet.contains(tag.toString())) ) { return false; }
		return true;
	}

	public static IFormattableTextComponent animateTextColor( String text ) {
		timer = (timer - 2 < 0) ? 255 : timer - 2;
		if( timer % 5 != 0 ) return perpetualTooltip;
		perpetualTooltip = new StringTextComponent("");

		for( int i = 0; i < text.length(); i++ ) {
			int temp = timer + i * 10;
			int color;
			if( currentYearMonth.getMonth() == Month.JUNE ) {
				int rainbow = getCosBetween(temp, 0, 255);
				color = Color.getHSBColor(rainbow / 255f, 1, 1).getRGB();
			} else {
				int red = getCosBetween(temp, 100, 255);
				int green = getCustomBetween(temp, 0, 148);
				int blue = getCustomBetween(temp, 0, 209);
				color = new Color(red, green, blue).getRGB();
			}
			perpetualTooltip.append(colorCharacter(String.valueOf(text.charAt(i)), net.minecraft.util.text.Color.fromRgb(color)));
		}
		return perpetualTooltip;
	}

	private static IFormattableTextComponent colorCharacter( String character, net.minecraft.util.text.Color color ) {
		return new StringTextComponent(character).withStyle(Style.EMPTY.withColor(color));
	}

	private static int getCosBetween( int seed, int min, int max ) {
		int range = max - min;
		double sine = Math.cos(seed * interval);
		return (int) ((sine + 1) * range / 2 + min);
	}

	private static int getCustomBetween( int seed, int min, int max ) {
		double temp = seed * interval;
		double range = -Math.abs(2 * Math.sin(temp / 2)) + 1;
		return (range > 0) ? ((int) (range * (max - min) + min)) : 0;
	}
}
