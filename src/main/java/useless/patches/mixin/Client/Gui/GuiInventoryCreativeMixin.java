package useless.patches.mixin.Client.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiInventoryCreative;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.ContainerPlayerCreative;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GuiInventoryCreative.class, remap = false)
public class GuiInventoryCreativeMixin {
	@Shadow
	protected ContainerPlayerCreative container;
	@Unique
	private final Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
	@Redirect(method = "buttonPressed(Lnet/minecraft/client/gui/GuiButton;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/player/inventory/InventoryPlayer;setInventorySlotContents(ILnet/minecraft/core/item/ItemStack;)V"))
	private void clearInvMultiplayer(InventoryPlayer instance, int i, ItemStack itemstack){
		mc.playerController.doInventoryAction(container.windowId, InventoryAction.CREATIVE_DELETE, new int[]{i}, mc.thePlayer);
	}
	@Redirect(method = "buttonPressed(Lnet/minecraft/client/gui/GuiButton;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/player/inventory/InventoryPlayer;getSizeInventory()I"))
	private int clearInvSize(InventoryPlayer instance){
		return instance.getSizeInventory() + 5;
	}
}
