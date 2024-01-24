package useless.patches.mixin.Core;

import goocraft4evr.nonamedyes.item.ItemModDye;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.ContainerPlayerCreative;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.patches.compat.block.KThingsCompat;

@Mixin(value = ContainerPlayerCreative.class, remap = false)
public class ContainerCreative {
	@Inject(method = "<clinit>", at = @At("TAIL"))
	private static void addStuffs(CallbackInfo ci){
		for (int i = 0; i < ItemModDye.NUM_DYES; i++) {
			ContainerPlayerCreative.creativeItems.add(new ItemStack(KThingsCompat.paintedBox, 1, i));
			ContainerPlayerCreative.creativeItemsCount += 1;
		}
		for (int i = 0; i < ItemModDye.NUM_DYES; i++) {
			ContainerPlayerCreative.creativeItems.add(new ItemStack(KThingsCompat.blockStairsCornerPaintedModded, 1, i << 4));
			ContainerPlayerCreative.creativeItems.add(new ItemStack(KThingsCompat.blockStairsCornerInnerPaintedModded, 1, i << 4));
			ContainerPlayerCreative.creativeItemsCount += 2;
		}
	}
}
