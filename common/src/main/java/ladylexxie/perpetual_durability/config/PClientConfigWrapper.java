package ladylexxie.perpetual_durability.config;

import ladylexxie.perpetual_durability.PerpetualDurability;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = PerpetualDurability.ID )
public class PClientConfigWrapper extends PartitioningSerializer.GlobalData{
	@ConfigEntry.Category("client")
	@ConfigEntry.Gui.TransitiveObject
	public PClientConfig client = new PClientConfig();
}
