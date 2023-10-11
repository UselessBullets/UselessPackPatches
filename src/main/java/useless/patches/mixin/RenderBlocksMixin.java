package useless.patches.mixin;

import net.minecraft.client.render.RenderBlocks;
import net.minecraft.core.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.patches.Patches;

@Mixin(value = RenderBlocks.class,remap = false)
public class RenderBlocksMixin {
	@Inject(method = "renderBlockOnInventory(Lnet/minecraft/core/block/Block;IF)V", at = @At("HEAD"), cancellable = true)
	private void debug(Block block, int metadata, float brightness, CallbackInfo ci){
		String key = "Null";
		String id = "IDNULL";
		try {
			key = block.getKey();
		} catch (NullPointerException e){
		}
		try {
			id = String.valueOf(block.id);
		} catch (Exception e){

		}
		Patches.LOGGER.info( key + ", " + id + ", " + metadata);
		if (block == null){
			ci.cancel();
		}
	}
}
