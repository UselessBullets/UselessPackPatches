package useless.patches.mixin.Client.Gui;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.patches.Patches;

@Mixin(value = GuiMainMenu.class, remap = false)
public class GuiMainMenuMixin extends GuiScreen {
	@Inject(method = "drawScreen(IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;drawString(Lnet/minecraft/client/render/FontRenderer;Ljava/lang/String;III)V", shift = At.Shift.AFTER))
	private void waterMark(int x, int y, float renderPartialTicks, CallbackInfo ci){
		int versionTextColor = (Boolean) this.mc.gameSettings.alphaMenu.value ? 0x505050 : 0xFFFFFF;
		this.drawString(fontRenderer, Patches.ModPackString, 2, 12, versionTextColor);
	}
}
