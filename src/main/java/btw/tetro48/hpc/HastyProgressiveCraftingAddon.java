package btw.tetro48.hpc;

import btw.AddonHandler;
import btw.BTWAddon;

public class HastyProgressiveCraftingAddon extends BTWAddon {
    private static HastyProgressiveCraftingAddon instance;

    public HastyProgressiveCraftingAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }
}