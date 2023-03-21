package ladylexxie.perpetual_durability;

import com.mojang.logging.LogUtils;
import ladylexxie.perpetual_durability.registry.PRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod( PerpetualDurability.ID )
public class PerpetualDurability {
	public static final String ID = "perpetual_durability";
	public static final Logger LOGGER = LogUtils.getLogger();

	public PerpetualDurability() {
		PRegistry.init();
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static ResourceLocation asResource( String path ) { return new ResourceLocation(ID, path); }
}
