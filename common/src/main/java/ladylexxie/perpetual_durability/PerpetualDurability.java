package ladylexxie.perpetual_durability;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PerpetualDurability {
	public static final String ID = "perpetual_durability";
	public static final String NAME = "Perpetual Durability";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static ResourceLocation id( String path ) { return new ResourceLocation(ID, path); }
}
