package ladylexxie.perpetual_durability;

import dev.architectury.event.events.client.ClientTooltipEvent;
import ladylexxie.perpetual_durability.config.PDClientConfig;
import ladylexxie.perpetual_durability.config.PDClientConfigWrapper;
import ladylexxie.perpetual_durability.event.ItemTooltip;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

public final class PDClient {
	public static PDClientConfig CLIENT_CONFIG;

	private PDClient() { }

	public static void init() {
		AutoConfig.register(PDClientConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		CLIENT_CONFIG = AutoConfig.getConfigHolder(PDClientConfigWrapper.class).getConfig().client;
		ClientTooltipEvent.ITEM.register(ItemTooltip::onItemTooltip);
	}
}
