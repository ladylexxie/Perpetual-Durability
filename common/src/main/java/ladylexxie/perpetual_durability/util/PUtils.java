package ladylexxie.perpetual_durability.util;

import ladylexxie.perpetual_durability.PerpetualDurability;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.awt.*;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PUtils {
	private static int timer = 255;
	private static MutableComponent perpetualTooltip = Component.literal("");
	private static final double interval = Math.PI / 128;
	private static final YearMonth currentYearMonth = YearMonth.now();

	private PUtils() { }

	private static final List<String> modBlacklist = PerpetualDurability.COMMON_CONFIG.blacklist.modBlacklist;
	private static final List<String> itemBlacklist = PerpetualDurability.COMMON_CONFIG.blacklist.itemBlacklist;
	private static final List<String> tagsBlacklist = PerpetualDurability.COMMON_CONFIG.blacklist.tagBlacklist;

	public static boolean canPerpetuate( ItemStack stack ) {
		if( !stack.isDamageableItem() ) return false;
		ResourceLocation id = stack.getItem().arch$registryName();
		if( modBlacklist.contains(id.getNamespace()) ) return false;
		if( itemBlacklist.contains(id.toString()) ) return false;
		Set<String> tagsBlacklistSet = new HashSet<>(tagsBlacklist);
		if( stack.getTags().anyMatch(tag -> tagsBlacklistSet.contains(tag.toString())) ) { return false; }
		return true;
	}

	public static Component animateTextColor( String text ) {
		timer = (timer - 2 < 0) ? 255 : timer - 2;
		if( timer % 5 != 0 ) return perpetualTooltip;
		perpetualTooltip = Component.literal("");

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
			perpetualTooltip.append(colorCharacter(String.valueOf(text.charAt(i)), color));
		}
		return perpetualTooltip;
	}

	private static MutableComponent colorCharacter( String character, int color ) {
		return Component.literal(character).withStyle(Style.EMPTY.withColor(color));
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
