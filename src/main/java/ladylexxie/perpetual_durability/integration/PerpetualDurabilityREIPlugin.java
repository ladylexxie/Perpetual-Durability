package ladylexxie.perpetual_durability.integration;


import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.forge.REIPluginCommon;
import me.shedaniel.rei.plugin.common.DefaultPlugin;

@REIPluginCommon
public class PerpetualDurabilityREIPlugin implements REIServerPlugin {
	@Override
	public void registerDisplaySerializer( DisplaySerializerRegistry registry ){
		registry.register(DefaultPlugin.SMITHING, );
	}
}
