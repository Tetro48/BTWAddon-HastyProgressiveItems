package btw.tetro48.hpc;

import api.AddonHandler;
import api.BTWAddon;

public class HastyProgressiveCraftingAddon extends BTWAddon {
    private static HastyProgressiveCraftingAddon instance;

    public HastyProgressiveCraftingAddon() {
        super();
    }

    @Override
    public void postSetup() {
        this.shouldVersionCheck = false;
        this.isRequiredClientAndServer = false;
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }
}