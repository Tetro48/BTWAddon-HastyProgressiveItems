package btw.tetro48.hpc.mixin;

import api.item.items.ProgressiveCraftingItem;
import api.world.difficulty.DifficultyParam;
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
        int iUseCount = player.getItemInUseCount();
        if (this.getMaxItemUseDuration(stack) - iUseCount <= this.getItemUseWarmupDuration()) return;
        if (iUseCount % 4 != 0) return;

		amplifier *= 1 + world.getDifficultyParameter(DifficultyParam.ProgressiveCraftingAdditionalProgressPerTick.class);
        int iDamage = stack.getItemDamage();
        iDamage -= amplifier;

        if (iDamage > 0) {
            stack.setItemDamage(iDamage);
        } else {
            player.setItemInUseCount(1);
        }
    }
}
