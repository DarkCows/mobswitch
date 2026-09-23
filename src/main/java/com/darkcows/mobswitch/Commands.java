package com.darkcows.mobswitch;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;

import static net.minecraft.commands.Commands.literal;

public class Commands {
    static void initCommands() {
        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) -> {
                dispatcher.register(
                    literal("mobswitch")
                        .requires(source ->
                            source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR)
                        )
                        .then(
                            literal("toggle")
                                .executes(context -> {
                                    MobSwitch.MOBSWITCH_ENABLED = !MobSwitch.MOBSWITCH_ENABLED;
                                    context.getSource().sendSuccess(
                                        () -> Component.literal(
                                            "Mob switch: " +
                                            (MobSwitch.MOBSWITCH_ENABLED ? "ON" : "OFF")
                                        ),
                                        false
                                    );
                                    return 1;
                                })
                        )
                        .then(
                            literal("on")
                                .executes(context -> {
                                    MobSwitch.MOBSWITCH_ENABLED = true;
                                    context.getSource().sendSuccess(
                                        () -> Component.literal("Mob switch: ON"),
                                        false
                                    );
                                    return 1;
                                })
                        )
                        .then(
                            literal("off")
                                .executes(context -> {
                                    MobSwitch.MOBSWITCH_ENABLED = false;
                                    context.getSource().sendSuccess(
                                        () -> Component.literal("Mob switch: OFF"),
                                        false
                                    );
                                    return 1;
                                })
                        )
                );
            }
        );
    }
}
