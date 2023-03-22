package ladylexxie.perpetual_durability.forge;

import ladylexxie.perpetual_durability.PDClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class PerpetualDurabilityClientForge {
	@SubscribeEvent
	public static void onClientSetup( FMLClientSetupEvent event ){
		PDClient.init();
	}
}
