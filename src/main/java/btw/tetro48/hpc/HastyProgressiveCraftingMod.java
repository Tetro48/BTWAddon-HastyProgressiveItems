package btw.tetro48.hpc;

import net.fabricmc.api.ModInitializer;

public class HastyProgressiveCraftingMod implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("TIP: To make Hasty Progressive Crafting addon do its job, create a beacon with gold block base!");
	}
}
