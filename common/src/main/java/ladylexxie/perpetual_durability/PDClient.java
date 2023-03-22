package ladylexxie.perpetual_durability;

import ladylexxie.perpetual_durability.config.PClientConfig;
import ladylexxie.perpetual_durability.config.PClientConfigWrapper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ClientModInitializer;

public final class PDClient implements ClientModInitializer {
	public static PClientConfig CLIENT_CONFIG;

	@Override
	public void onInitializeClient() {
		AutoConfig.register(PClientConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		CLIENT_CONFIG = AutoConfig.getConfigHolder(PClientConfigWrapper.class).getConfig().client;
	}
}
