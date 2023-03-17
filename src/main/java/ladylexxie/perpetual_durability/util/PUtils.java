package ladylexxie.perpetual_durability.util;

import ladylexxie.perpetual_durability.config.PCommonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class PUtils {
	private static final List<String> modBlacklist = (List<String>) PCommonConfig.MOD_BLACKLIST.get();
	private static final List<String> itemBlacklist = (List<String>) PCommonConfig.ITEM_BLACKLIST.get();
	private static final List<String> tagsBlacklist = (List<String>) PCommonConfig.TAGS_BLACKLIST.get();

	public static boolean hasTag( ItemStack stack, TagKey<Item> tag ) { return hasTag(stack, tag.toString()); }
	public static boolean hasTag( ItemStack stack, ResourceLocation tag ) { return hasTag(stack, tag.toString()); }
	public static boolean hasTag( ItemStack stack, String tag ) {
		for( TagKey<Item> itemTag : stack.getTags().toList() ) if( itemTag.toString().equals(tag) ) return true;
		return false;
	}

	public static ResourceLocation getID( Item item ) { return ForgeRegistries.ITEMS.getKey(item); }
	public static ResourceLocation getID( ItemStack stack ) { return getID(stack.getItem()); }

	public static boolean canPerpetuate( ItemStack stack ) { return canPerpetuate(getID(stack)); }
	public static boolean canPerpetuate( Item item ) { return canPerpetuate(getID(item)); }
	public static boolean canPerpetuate( ResourceLocation id ) {
		if( !getItemStackFromID(id).isDamageableItem() ) return false;
		if( modBlacklist.contains(id.getNamespace()) ) return false;
		if( itemBlacklist.contains(id.toString()) ) return false;
		if( tagsBlacklist.stream().anyMatch(tag -> PUtils.hasTag(getItemStackFromID(id), tag)) ) return false;
		return true;
	}

	public static ItemStack getItemStackFromID( ResourceLocation id ) {
		return ForgeRegistries.ITEMS.getValue(id).getDefaultInstance();
	}
}
