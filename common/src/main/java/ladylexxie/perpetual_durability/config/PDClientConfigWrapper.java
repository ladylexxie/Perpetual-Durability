package ladylexxie.perpetual_durability.config;

import ladylexxie.perpetual_durability.PDConstants;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = PDConstants.ID )
public class PDClientConfigWrapper extends PartitioningSerializer.GlobalData{
	@ConfigEntry.Category("client")
	@ConfigEntry.Gui.TransitiveObject
	public PDClientConfig client = new PDClientConfig();
}
