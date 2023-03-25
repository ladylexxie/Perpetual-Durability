package ladylexxie.perpetual_durability.compat;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;

public class PDREIPlugin implements REIClientPlugin {
	@Override
	public void registerDisplays( DisplayRegistry registry ) {
		CompatHelper.getPerpetuateRecipes(CompatHelper.REI).forEach(registry::add);
	}
}
