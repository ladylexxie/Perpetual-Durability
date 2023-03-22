package ladylexxie.perpetual_durability;

import ladylexxie.perpetual_durability.config.PDClientConfig;
import ladylexxie.perpetual_durability.config.PDClientConfigWrapper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

public final class PDClient {
	private static PDClient instance;
	public static PDClientConfig CLIENT_CONFIG;

	private PDClient(){
		AutoConfig.register(PDClientConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		CLIENT_CONFIG = AutoConfig.getConfigHolder(PDClientConfigWrapper.class).getConfig().client;
	}

	public static void init() {
		if(instance == null) instance = new PDClient();
	}
}
