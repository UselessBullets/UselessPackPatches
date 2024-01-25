package useless.patches;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.popup.GuiPopup;
import net.minecraft.client.gui.popup.PopupBuilder;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;

import static useless.patches.Patches.MOD_ID;

public class PatchesClient implements ClientStartEntrypoint {
	@Override
	public void beforeClientStart() {
		SoundHelper.Client.addSound(MOD_ID, "ratchet.wav");
	}

	@Override
	public void afterClientStart() {
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
		GuiPopup popup = new PopupBuilder(mc.currentScreen, (int) (mc.currentScreen.width * 0.75))
			.closeOnEsc(0)
			.closeOnClickOut(0)
			.withMessageBox("", (int)(mc.currentScreen.height * 0.5f),"This is a prerelease version of the modpack, some features have yet to be implemented like world conversions from the previous 1.6.0 version of the modpack. There are still likely many bugs that I have yet to catch caution advised and make sure to make frequent backups if you decide to continue.", (int) (((mc.currentScreen.width * 0.75)/3) / mc.resolution.scale))
			.withButtonGroup("gui.exception.choice", new String[]{"gui.exception.button.back"}, new int[]{0})
			.build();
		mc.displayGuiScreen(popup);
	}
}
