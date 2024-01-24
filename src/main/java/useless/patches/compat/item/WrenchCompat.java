package useless.patches.compat.item;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.item.IndustryItems;
import deboni.potatologistics.PotatoLogisticsMod;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import turniplabs.simpletech.SimpleTech;
import useless.patches.compat.PatchTags;

public class WrenchCompat {
	public static void init(){
		Item _wrench = PotatoLogisticsMod.itemWrench;
		Item.itemsList[_wrench.id] = null;
		new ItemWrenchUniversal(_wrench.id)
			.setIconIndex(_wrench.getIconIndex(_wrench.getDefaultStack()))
			.setFull3D()
			.setKey(_wrench.getKey().replace("item.", ""));

		Item _wrenchIndustry = IndustryItems.toolWrench;
		Item.itemsList[_wrenchIndustry.id] = null;
		new ItemWrenchUniversal(_wrenchIndustry.id)
			.setIconIndex(_wrenchIndustry.getIconIndex(_wrenchIndustry.getDefaultStack()))
			.setFull3D()
			.setKey(_wrenchIndustry.getKey().replace("item.", ""));

		PotatoLogisticsMod.blockPotato.withTags(PatchTags.WRENCH_PICKS);
		PotatoLogisticsMod.blockBlockCrusher.withTags(PatchTags.WRENCH_PICKS);

		IndustryBlocks.alarm.withTags(PatchTags.WRENCH_PICKS);
		IndustryBlocks.nuclearChamber.withTags(PatchTags.WRENCH_PICKS);

		SimpleTech.jumpPad.withTags(PatchTags.WRENCH_PICKS);
		SimpleTech.lightSensor.withTags(PatchTags.WRENCH_PICKS);

		Block.mobspawner.withTags(PatchTags.WRENCH_EXCLUSION);
		Block.pistonMoving.withTags(PatchTags.WRENCH_EXCLUSION);
	}
}
