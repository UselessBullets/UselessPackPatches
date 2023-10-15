package useless.patches.mixin;

import net.minecraft.client.GameResolution;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.PanelCrashReport;
import net.minecraft.core.UnexpectedThrowable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.patches.PanelModPackCrashReport;

import javax.swing.*;
import java.awt.*;

@Mixin(value = Minecraft.class, remap = false)
public class MinecraftMixin {
	@Unique
	private Minecraft thisAs = (Minecraft)(Object)this;
	@Final
	@Shadow
	public final GameResolution resolution = new GameResolution(thisAs);
	@Unique
	private UnexpectedThrowable unexpectedthrowable;
	@Inject(method = "displayUnexpectedThrowable(Lnet/minecraft/core/UnexpectedThrowable;)V", at = @At(value = "HEAD"))
	private void display(UnexpectedThrowable unexpectedthrowable, CallbackInfo ci){
		this.unexpectedthrowable = unexpectedthrowable;
	}
	@Redirect(method = "displayUnexpectedThrowable(Lnet/minecraft/core/UnexpectedThrowable;)V", at = @At(value = "INVOKE", target = "Ljavax/swing/JFrame;add(Ljava/awt/Component;)Ljava/awt/Component;"))
	private Component customCrashScreen(JFrame instance, Component component){
		PanelModPackCrashReport panel = new PanelModPackCrashReport(unexpectedthrowable);
		panel.setPreferredSize(new Dimension(this.resolution.width, this.resolution.height));
		return instance.add(panel);
	}
}
