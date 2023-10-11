package useless.patches.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.ServerGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import useless.patches.Patches;

import javax.swing.*;

@Mixin(value = ServerGui.class,remap = false)
public class ServerGuiMixin {
	@Inject(method = "initGui(Lnet/minecraft/server/MinecraftServer;)V", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
	private static void changeTitle(MinecraftServer minecraftserver, CallbackInfo ci, ServerGui servergui, JFrame jframe){
		jframe.setTitle("Useless' BTA Modpack Server: " + Patches.ModPackVersion);
	}
}
