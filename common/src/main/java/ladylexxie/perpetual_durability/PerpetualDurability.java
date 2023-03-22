package ladylexxie.perpetual_durability;

import ladylexxie.perpetual_durability.config.PCommonConfig;
import ladylexxie.perpetual_durability.config.PCommonConfigWrapper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PerpetualDurability implements ModInitializer {
	public static final String ID = "perpetual_durability";
	public static final String NAME = "Perpetual Durability";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
	public static PCommonConfig COMMON_CONFIG;

	@Override
	public void onInitialize() {
		AutoConfig.register(PCommonConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		COMMON_CONFIG = AutoConfig.getConfigHolder(PCommonConfigWrapper.class).getConfig().common;
	}

	public static ResourceLocation id( String path ) { return new ResourceLocation(ID, path); }
}
