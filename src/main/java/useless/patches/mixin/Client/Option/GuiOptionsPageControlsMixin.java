package useless.patches.mixin.Client.Option;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.options.GuiOptionsPageControls;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.patches.PatchSettings;

@Mixin(value = GuiOptionsPageControls.class, remap = false)
public abstract class GuiOptionsPageControlsMixin {
	@Shadow
	protected abstract void addKeyBindingsCategory(String languageKey, KeyBinding... bindings);

	@Inject(method = "<init>(Lnet/minecraft/client/gui/GuiScreen;Lnet/minecraft/client/option/GameSettings;)V", at = @At("TAIL"))
	private void addPatchesKeybinds(GuiScreen parent, GameSettings settings, CallbackInfo ci){
		PatchSettings patchSettings = (PatchSettings)settings;
		addKeyBindingsCategory("patches.options.category.keybinds", patchSettings.getCommandKey());
	}
}
