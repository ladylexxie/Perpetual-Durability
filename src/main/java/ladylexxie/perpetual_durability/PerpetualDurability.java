package ladylexxie.perpetual_durability;

import ladylexxie.perpetual_durability.config.Config;
import ladylexxie.perpetual_durability.registry.LexRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod( PerpetualDurability.MOD_ID )
public class PerpetualDurability {
	public static final String MOD_ID = "perpetual_durability";

	public PerpetualDurability() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
		Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(PerpetualDurability.MOD_ID + "-common.toml").toString());

		LexRegistry.init();
		MinecraftForge.EVENT_BUS.register(this);
	}
}
