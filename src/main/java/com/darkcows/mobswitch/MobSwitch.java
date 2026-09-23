package com.darkcows.mobswitch;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobSwitch implements ModInitializer {
	public static final String MOD_ID = "darkcows-mobswitch";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static boolean MOBSWITCH_ENABLED = false;

	@Override
	public void onInitialize() {
		Commands.initCommands();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
