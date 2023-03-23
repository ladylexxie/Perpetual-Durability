package ladylexxie.perpetual_durability.forge;

import ladylexxie.perpetual_durability.PDConstants;
import ladylexxie.perpetual_durability.PerpetualDurability;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod( PDConstants.ID )
public class PerpetualDurabilityForge {
	public PerpetualDurabilityForge() {
		MinecraftForge.EVENT_BUS.register(this);
		PerpetualDurability.init();
	}
}
