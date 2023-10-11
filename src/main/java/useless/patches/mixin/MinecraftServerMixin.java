package useless.patches.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import useless.patches.Patches;

@Mixin(value = MinecraftServer.class,remap = false)
public class MinecraftServerMixin {
	@Inject(method = "startServer()Z", at = @At(value = "INVOKE", target = "Lorg/apache/log4j/Logger;info(Ljava/lang/Object;)V", ordinal = 0, shift = At.Shift.AFTER))
	private void modpackInfo(CallbackInfoReturnable<Boolean> cir){
		MinecraftServer.logger.info("Running with " + Patches.ModPackString);
	}
}
