package ladylexxie.perpetual_durability.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber
public class Config {
	public static final PCommonConfig COMMON_CONFIG;
	public static final PClientConfig CLIENT_CONFIG;

	static {
		Pair<PCommonConfig, ForgeConfigSpec> commonConfigPair = new ForgeConfigSpec.Builder().configure(PCommonConfig::new);
		Pair<PClientConfig, ForgeConfigSpec> clientConfigPair = new ForgeConfigSpec.Builder().configure(PClientConfig::new);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonConfigPair.getValue());
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientConfigPair.getValue());
		COMMON_CONFIG = commonConfigPair.getKey();
		CLIENT_CONFIG = clientConfigPair.getKey();
	}
}
