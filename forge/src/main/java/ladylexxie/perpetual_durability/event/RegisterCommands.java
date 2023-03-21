package ladylexxie.perpetual_durability.event;

import ladylexxie.perpetual_durability.command.PerpetuateCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RegisterCommands {
	@SubscribeEvent
	public static void onRegisterCommands( RegisterCommandsEvent event ) {
		PerpetuateCommand.register(event.getDispatcher());
	}
}
