package ladylexxie.perpetual_durability.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class PUtils {
	public static boolean hasTag( ItemStack stack, TagKey<Item> tag ) { return hasTag(stack, tag.toString()); }
	public static boolean hasTag( ItemStack stack, ResourceLocation tag ) { return hasTag(stack, tag.toString()); }
	public static boolean hasTag( ItemStack stack, String tag ) {
		for( TagKey<Item> itemTag : stack.getTags().toList() ) if( itemTag.toString().equals(tag) ) return true;
		return false;
	}

	public static ResourceLocation getID( Item item ) { return ForgeRegistries.ITEMS.getKey(item); }
	public static ResourceLocation getID( ItemStack stack ) { return getID(stack.getItem()); }
}
