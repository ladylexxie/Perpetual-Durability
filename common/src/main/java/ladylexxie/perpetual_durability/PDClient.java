package ladylexxie.perpetual_durability;

import ladylexxie.perpetual_durability.config.PDClientConfig;
import ladylexxie.perpetual_durability.config.PDClientConfigWrapper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ClientModInitializer;

public final class PDClient implements ClientModInitializer {
	public static PDClientConfig CLIENT_CONFIG;

	@Override
	public void onInitializeClient() {
		AutoConfig.register(PDClientConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		CLIENT_CONFIG = AutoConfig.getConfigHolder(PDClientConfigWrapper.class).getConfig().client;
	}
}
