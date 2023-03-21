package ladylexxie.perpetual_durability;

import ladylexxie.perpetual_durability.registry.PRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod( PerpetualDurability.ID )
public class PerpetualDurability {
	public static final String ID = "perpetual_durability";
	public static final Logger LOGGER = LogManager.getLogger();

	public PerpetualDurability() {
		PRegistry.init();
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static ResourceLocation asResource( String path ) { return new ResourceLocation(ID, path); }
}
