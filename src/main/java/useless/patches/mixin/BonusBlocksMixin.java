package useless.patches.mixin;

import luke.bonusblocks.BonusBlocks;
import net.minecraft.core.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.patches.ItemJarPlacable;

@Mixin(value = BonusBlocks.class, remap = false)
public class BonusBlocksMixin {
	@Inject(method = "onInitialize()V", at = @At("TAIL"))
	private void init(CallbackInfo ci){
		// Fixes BTBTA and Bonus Blocks jar incompatibility
		Item.jar = (new ItemJarPlacable("jar", 16519, BonusBlocks.jar)).setIconCoord(3, 9);
	}
}
