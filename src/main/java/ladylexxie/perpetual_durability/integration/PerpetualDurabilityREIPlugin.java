package ladylexxie.perpetual_durability.integration;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.forge.REIPluginClient;

@REIPluginClient
public class PerpetualDurabilityREIPlugin implements REIClientPlugin {
	@Override
	public void registerDisplays( DisplayRegistry registry) {
		ApplyPerpetual.getRecipes().forEach(registry::add);
	}
}
