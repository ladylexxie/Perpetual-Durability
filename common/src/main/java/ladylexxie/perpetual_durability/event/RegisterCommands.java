package ladylexxie.perpetual_durability.event;

import com.mojang.brigadier.CommandDispatcher;
import ladylexxie.perpetual_durability.command.PerpetuateCommand;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class RegisterCommands {
	public static void onRegisterCommands( CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registry, Commands.CommandSelection selection ) {
		PerpetuateCommand.register(dispatcher);
	}
}
