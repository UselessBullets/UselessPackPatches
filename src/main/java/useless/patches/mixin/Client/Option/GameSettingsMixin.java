package useless.patches.mixin.Client.Option;

import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import useless.patches.PatchSettings;

@Mixin(GameSettings.class)
public class GameSettingsMixin implements PatchSettings {
	@Unique
	public KeyBinding commandKey = new KeyBinding("patches.key.command", Keyboard.KEY_SLASH);
	@Unique
	public KeyBinding getCommandKey() {
		return commandKey;
	}
}
