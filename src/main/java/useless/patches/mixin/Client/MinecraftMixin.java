package useless.patches.mixin.Client;

import net.minecraft.client.GameResolution;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.core.UnexpectedThrowable;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.patches.PanelModPackCrashReport;
import useless.patches.PatchSettings;
import useless.patches.mixin.Client.Gui.GuiChatAccessor;

import javax.swing.*;
import java.awt.*;

@Mixin(value = Minecraft.class, remap = false)
public class MinecraftMixin {
	@Unique
	private final Minecraft thisAs = (Minecraft)(Object)this;
	@Final
	@Shadow
	public final GameResolution resolution = new GameResolution(thisAs);
	@Shadow
	public GameSettings gameSettings;
	@Unique
	private UnexpectedThrowable unexpectedthrowable;
	@Inject(method = "displayUnexpectedThrowable(Lnet/minecraft/core/UnexpectedThrowable;)V", at = @At(value = "HEAD"))
	private void display(UnexpectedThrowable unexpectedthrowable, CallbackInfo ci){
		this.unexpectedthrowable = unexpectedthrowable; // gets exception
	}
	@Redirect(method = "displayUnexpectedThrowable(Lnet/minecraft/core/UnexpectedThrowable;)V", at = @At(value = "INVOKE", target = "Ljavax/swing/JFrame;add(Ljava/awt/Component;)Ljava/awt/Component;"))
	private Component customCrashScreen(JFrame instance, Component component){ // Returns modded crash screen
		PanelModPackCrashReport panel = new PanelModPackCrashReport(unexpectedthrowable);
		panel.setPreferredSize(new Dimension(this.resolution.width, this.resolution.height));
		return instance.add(panel);
	}
	@Unique
	private boolean addSlash = false;
	@Redirect(method = "runTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;isEventKey()Z", ordinal = 9))
	private boolean chatOrCommand(KeyBinding instance){ // Open chat with chat key or slash
		if (Keyboard.getEventKey() == ((PatchSettings)gameSettings).getCommandKey().keyCode()){
			addSlash = true;
			return true;
		}
		return instance.isEventKey();
	}
	@Redirect(method = "runTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", ordinal = 6))
	private void addSlashToChat(Minecraft instance, GuiScreen guiscreen){
		instance.displayGuiScreen(guiscreen);
		if (addSlash){
			addSlash = false;
			((GuiChat)guiscreen).setText("/");
			((GuiChatAccessor)guiscreen).getEditor().setCursor(10000);
		}
	}
}
