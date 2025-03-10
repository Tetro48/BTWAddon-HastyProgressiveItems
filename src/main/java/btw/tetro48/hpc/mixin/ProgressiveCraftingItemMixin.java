package btw.tetro48.hpc.mixin;

import btw.item.items.ProgressiveCraftingItem;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProgressiveCraftingItem.class)
public abstract class ProgressiveCraftingItemMixin extends Item {
	public ProgressiveCraftingItemMixin(int par1) {
		super(par1);
	}

	@Shadow public abstract int getMaxItemUseDuration(ItemStack stack);

	@Inject(method = "updateUsingItem", at = @At(value = "TAIL"))
	public void speedUp(ItemStack stack, World world, EntityPlayer player, CallbackInfo ci) {
		if (world.isRemote) return;
		PotionEffect hasteEffect = player.getActivePotionEffect(Potion.digSpeed);
        if (hasteEffect == null) return;
		if (hasteEffect.getDuration() <= 0) return;
        int amplifier = hasteEffect.getAmplifier() + 1;
        System.out.println("amplifier = " + amplifier);
        int iUseCount = player.getItemInUseCount();
        if (this.getMaxItemUseDuration(stack) - iUseCount <= this.getItemUseWarmupDuration()) return;
        if (iUseCount % 4 != 0) return;

        int iDamage = stack.getItemDamage();
        iDamage -= amplifier;
        if (world.getDifficulty().shouldIncreaseProgressiveCraftingSpeed()) {
            iDamage -= amplifier;
        }

        if (iDamage > 0) {
            stack.setItemDamage(iDamage);
        } else {
            player.setItemInUseCount(1);
        }
    }
}
