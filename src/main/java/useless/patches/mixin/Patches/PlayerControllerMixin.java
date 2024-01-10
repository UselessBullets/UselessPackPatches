package useless.patches.mixin.Patches;

import net.minecraft.client.player.controller.PlayerController;
import net.minecraft.core.util.helper.Side;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerController.class, remap = false)
public class PlayerControllerMixin {
	@Shadow
	protected int blockHitDelay;
	@Inject(method = "continueDestroyBlock(IIILnet/minecraft/core/util/helper/Side;)V", at = @At("HEAD"))
	private void removedBreakCooldown(int x, int y, int z, Side side, CallbackInfo ci){
		blockHitDelay = -1;
	}
}
