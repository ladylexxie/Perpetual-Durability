package ladylexxie.perpetual_durability.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import ladylexxie.perpetual_durability.PDClient;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class PerpetualCommand {
	public static void register( CommandDispatcher<CommandSourceStack> dispatcher ) {
		dispatcher.register(Commands.literal("perpetual").then(Commands.literal("config")
				.then(Commands.literal("client")
						.then(Commands.literal("tagTooltip")
								.then(Commands.argument("tagTooltip", BoolArgumentType.bool())
										.executes(context -> tagTooltip(context.getSource(), BoolArgumentType.getBool(context, "tagTooltip")))))
//						.then(Commands.literal("colorfulTooltip")
//								.then(Commands.argument("colorfulTooltip", BoolArgumentType.bool())
//										.executes(context -> colorfulTooltip(context.getSource(), BoolArgumentType.getBool(context, "colorfulTooltip")))))
//						.then(Commands.literal("enableDebugTags")
//								.then(Commands.argument("enableDebugTags", BoolArgumentType.bool())
//										.executes(context -> enableDebugTags(context.getSource(), BoolArgumentType.getBool(context, "enableDebugTags")))))
//						.then(Commands.literal("enableDebugNbt")
//								.then(Commands.argument("enableDebugNbt", BoolArgumentType.bool())
//										.executes(context -> enableDebugNbt(context.getSource(), BoolArgumentType.getBool(context, "enableDebugNbt")))))
				)
//				.then(Commands.literal("common")
//						.requires(source -> source.getServer().isSingleplayer())
//				)
		));
	}

	public static int tagTooltip( CommandSourceStack stack, boolean bool ) {
		PDClient.CLIENT_CONFIG.tagTooltip = bool;
		return 1;
	}

//	public static int colorfulTooltip( CommandSourceStack stack, boolean bool ) {
//
//	}
//
//	public static int enableDebugTags( CommandSourceStack stack, boolean bool ) {
//
//	}
//
//	public static int enableDebugNbt( CommandSourceStack stack, boolean bool ) {
//
//	}
}
