package ladylexxie.perpetual_durability.compat;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.plugin.client.BuiltinClientPlugin;
import me.shedaniel.rei.plugin.common.displays.DefaultSmithingDisplay;

public class PDREIPlugin implements REIClientPlugin {
	@Override
	public void registerDisplays( DisplayRegistry registry ){
		CompatHelper.getPerpetuateRecipes(CompatHelper.XEI.REI).forEach(recipe -> {
			registry.get(BuiltinClientPlugin.SMITHING).add(new DefaultSmithingDisplay(recipe));
		});
	}
}
