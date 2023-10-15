package useless.patches;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.PanelCrashReport;
import net.minecraft.client.render.canvas.CanvasCrashReport;
import net.minecraft.client.render.canvas.CanvasMojangLogo;
import net.minecraft.core.UnexpectedThrowable;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class PanelModPackCrashReport extends PanelCrashReport {
	public PanelModPackCrashReport(UnexpectedThrowable unexpectedthrowable) {
		super(unexpectedthrowable);
		this.setBackground(new Color(0x6E0000));
		this.setLayout(new BorderLayout());
		StringWriter stringwriter = new StringWriter();
		unexpectedthrowable.exception.printStackTrace(new PrintWriter(stringwriter));
		String s = stringwriter.toString();
		String s1 = "";
		String s2 = "";
		try {
			s2 = s2 + "Generated " + new SimpleDateFormat().format(new Date()) + "\n";
			s2 = s2 + "\n";
			s2 = s2 + "Minecraft: Better than Adventure! " + Minecraft.VERSION + "\n";
			s2 = s2 + Patches.ModPackString + "\n";
			s2 = s2 + "OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version") + "\n";
			s2 = s2 + "Java: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor") + "\n";
			s2 = s2 + "VM: " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor") + "\n";
			s2 = s2 + "LWJGL: " + Sys.getVersion() + "\n";
			s1 = GL11.glGetString(7936);
			s2 = s2 + "OpenGL: " + GL11.glGetString(7937) + " version " + GL11.glGetString(7938) + ", " + GL11.glGetString(7936) + "\n";
		} catch (Throwable throwable) {
			s2 = s2 + "[failed to get system properties (" + throwable + ")]\n";
		}
		s2 = s2 + "\n";
		s2 = s2 + s;
		String s3 = "";
		s3 = s3 + "\n";
		s3 = s3 + "\n";
		if (s.contains("Pixel format not accelerated")) {
			s3 = s3 + "      Bad video card drivers!      \n";
			s3 = s3 + "      -----------------------      \n";
			s3 = s3 + "\n";
			s3 = s3 + "Minecraft was unable to start because it failed to find an accelerated OpenGL mode.\n";
			s3 = s3 + "This can usually be fixed by updating the video card drivers.\n";
			if (s1.toLowerCase().contains("nvidia")) {
				s3 = s3 + "\n";
				s3 = s3 + "You might be able to find drivers for your video card here:\n";
				s3 = s3 + "  http://www.nvidia.com/\n";
			} else if (s1.toLowerCase().contains("ati")) {
				s3 = s3 + "\n";
				s3 = s3 + "You might be able to find drivers for your video card here:\n";
				s3 = s3 + "  http://www.amd.com/\n";
			}
		} else {
			s3 = s3 + "      Minecraft has crashed!      \n";
			s3 = s3 + "      ----------------------      \n";
			s3 = s3 + "\n";
			s3 = s3 + "Minecraft has stopped running because it encountered a problem.\n";
			s3 = s3 + "\n";
			s3 = s3 + "If you wish to report this, please copy this entire text and post it on the forum thread at https://github.com/UselessBullets/UselessBTAModPack/issues, or join the BTA Discord server at https://discord.gg/6r3HdT53XH and post it in the community content, Server Compatible Modpack Thread.\n";
			s3 = s3 + "Please include a description of what you did when the error occured.\n";
			s3 = s3 + "\nIf you believe the issue to be from just BTA then, please copy this entire text and post it on the forum thread at https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/3106066-better-than-adventure-for-beta-1-7-3-timely, or join the Discord server at https://discord.gg/6r3HdT53XH and post it in #bug-reports.\n";
			try {
				s3 = s3 + Patches.modTable();
			} catch (Exception e){
				Patches.LOGGER.warn(String.valueOf(e));
				s3 = s3 + "Mod Table Failed to generate!";
			}

		}
		s3 = s3 + "\n";
		s3 = s3 + "--- BEGIN ERROR REPORT " + Integer.toHexString(s3.hashCode()) + " --------\n";
		s3 = s3 + s2;
		s3 = s3 + "--- END ERROR REPORT " + Integer.toHexString(s3.hashCode()) + " ----------\n";
		s3 = s3 + "\n";
		s3 = s3 + "\n";
		TextArea textarea = new TextArea(s3, 0, 0, 1);
		textarea.setFont(new Font("Monospaced", 0, 12));
		this.add((Component)new CanvasMojangLogo(), "North");
		this.add((Component)new CanvasCrashReport(80), "East");
		this.add((Component)new CanvasCrashReport(80), "West");
		this.add((Component)new CanvasCrashReport(100), "South");
		this.add((Component)textarea, "Center");
	}
}
