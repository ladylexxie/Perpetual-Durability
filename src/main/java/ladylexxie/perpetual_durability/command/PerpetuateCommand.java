package ladylexxie.perpetual_durability.command;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import ladylexxie.perpetual_durability.util.PUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;

public class PerpetuateCommand {
	private static final DynamicCommandExceptionType ERROR_NOT_LIVING_ENTITY = new DynamicCommandExceptionType(e -> new TranslationTextComponent("commands.perpetual_durability.perpetuate.failed.entity", e));
	private static final DynamicCommandExceptionType ERROR_NO_ITEM = new DynamicCommandExceptionType(e -> new TranslationTextComponent("commands.perpetual_durability.perpetuate.failed.itemless", e));
	private static final DynamicCommandExceptionType ERROR_INCOMPATIBLE = new DynamicCommandExceptionType(e -> new TranslationTextComponent("commands.perpetual_durability.perpetuate.failed.incompatible", e));
	private static final SimpleCommandExceptionType ERROR_NOTHING_HAPPENED = new SimpleCommandExceptionType(new TranslationTextComponent("commands.perpetual_durability.perpetuate.failed"));

	public static void register( CommandDispatcher<CommandSource> dispatcher ) {
		dispatcher.register(Commands.literal("perpetuate")
				.requires(p -> p.hasPermission(2))
				.executes(c -> perpetuate(c.getSource(), ImmutableList.of(c.getSource().getEntityOrException()), ""))
				.then(Commands.argument("force", StringArgumentType.word())
						.executes(c -> perpetuate(c.getSource(), ImmutableList.of(c.getSource().getEntityOrException()), StringArgumentType.getString(c, "force")))
				)
				.then(Commands.argument("targets", EntityArgument.entities())
						.executes(c -> perpetuate(c.getSource(), EntityArgument.getEntities(c, "targets"), ""))
				)
		);
	}

	public static int perpetuate( CommandSource source, Collection<? extends Entity> entities, String arg ) throws CommandSyntaxException {
		boolean forced = arg.equals("force");
		int i = 0;
		for( Entity entity : entities ) {
			if( entity instanceof LivingEntity ) {
				LivingEntity livingEntity = (LivingEntity) entity;
				ItemStack stack = livingEntity.getMainHandItem();
				if( !stack.isEmpty() ) {
					if( PUtils.canPerpetuate(stack) || forced) {
						stack.getOrCreateTag().putBoolean("Unbreakable", true);
						stack.setDamageValue(0);
						i++;
					} else if( entities.size() == 1 ) {	throw ERROR_INCOMPATIBLE.create(stack.getItem().getName(stack).getString()); }
				} else if( entities.size() == 1 ) { throw ERROR_NO_ITEM.create(livingEntity.getName().getString()); }
			} else if( entities.size() == 1 ) { throw ERROR_NOT_LIVING_ENTITY.create(entity.getName().getString()); }
		}

		if( i == 0 ) {
			throw ERROR_NOTHING_HAPPENED.create();
		} else {
			source.sendSuccess(entities.size() == 1
							? new TranslationTextComponent("commands.perpetual_durability.perpetuate.success.single", entities.iterator().next().getDisplayName())
							: new TranslationTextComponent("commands.enchant.success.multiple", entities.size()),
					true
			);

			return i;
		}
	}
}
