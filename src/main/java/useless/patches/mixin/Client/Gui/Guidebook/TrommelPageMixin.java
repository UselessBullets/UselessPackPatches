package useless.patches.mixin.Client.Gui.Guidebook;

import net.minecraft.client.gui.guidebook.trommeling.TrommelPage;
import net.minecraft.core.achievement.stat.Stat;
import net.minecraft.core.achievement.stat.StatsCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import useless.patches.Patches;

@Mixin(value = TrommelPage.class, remap = false)
public class TrommelPageMixin {
	@Redirect(method = "renderForeground(Lnet/minecraft/client/render/RenderEngine;Lnet/minecraft/client/render/FontRenderer;IIIIF)V",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/core/achievement/stat/StatsCounter;readStat(Lnet/minecraft/core/achievement/stat/Stat;)I"))
	private int alwaysDiscover(StatsCounter instance, Stat statbase){
		int val = instance.readStat(statbase);
		if (Patches.forceRevealStats){
			return Math.max(val, 1);
		}
		return val;
	}
}
