package useless.patches.mixin.Client.Gui;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.text.TextFieldEditor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = GuiChat.class, remap = false)
public interface GuiChatAccessor {
	@Accessor("editor")
	TextFieldEditor getEditor();
}
