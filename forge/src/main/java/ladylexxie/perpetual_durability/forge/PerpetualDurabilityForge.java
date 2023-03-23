package ladylexxie.perpetual_durability.forge;

import dev.architectury.platform.forge.EventBuses;
import ladylexxie.perpetual_durability.PDConstants;
import ladylexxie.perpetual_durability.PerpetualDurability;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod( PDConstants.ID )
public class PerpetualDurabilityForge {
	public PerpetualDurabilityForge() {
		EventBuses.registerModEventBus(PDConstants.ID, FMLJavaModLoadingContext.get().getModEventBus());
		PerpetualDurability.init();
	}
}
