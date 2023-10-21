package useless.patches.mixin.Client.Render.Window;

import net.minecraft.client.render.window.GameWindowLWJGL2;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import useless.patches.Patches;

@Mixin(value = GameWindowLWJGL2.class, remap = false)
public class GameWindowLWJGL2Mixin {
	@Redirect(method = "init(Lnet/minecraft/client/Minecraft;)V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
	private void changeTitle(String newTitle){
		Display.setTitle(Patches.ModPackString);
	}
}
