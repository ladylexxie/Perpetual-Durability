package ladylexxie.perpetual_durability;

import dev.architectury.event.events.common.CommandRegistrationEvent;
import ladylexxie.perpetual_durability.config.PDCommonConfig;
import ladylexxie.perpetual_durability.config.PDCommonConfigWrapper;
import ladylexxie.perpetual_durability.event.RegisterCommands;
import ladylexxie.perpetual_durability.registry.PDRegistry;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.minecraft.resources.ResourceLocation;

public final class PerpetualDurability {
	private static PerpetualDurability instance;
	public static PDCommonConfig COMMON_CONFIG;

	private PerpetualDurability() { }

	public static void init() {
		AutoConfig.register(PDCommonConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		COMMON_CONFIG = AutoConfig.getConfigHolder(PDCommonConfigWrapper.class).getConfig().common;

		CommandRegistrationEvent.EVENT.register(RegisterCommands::onRegisterCommands);
		PDRegistry.init();
	}

	public static ResourceLocation id( String path ) { return new ResourceLocation(PDConstants.ID, path); }
}
