package useless.patches.compat.block;

import Killi.Things.Block.BlockStairsCorner;
import Killi.Things.Block.BlockStairsCornerInner;
import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.item.ItemModDye;
import goocraft4evr.nonamedyes.item.ModItems;
import goocraft4evr.nonamedyes.item.block.ItemModBlockPainted;
import luke.bonusblocks.BonusBlocks;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.ContainerPlayerCreative;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;

import static useless.patches.Patches.MOD_ID;
import static useless.patches.Patches.patchesBlockID;

public class KThingsCompat {
	public static Block paintedBox;
	public static Block blockStairsCornerPaintedModded;
	public static Block blockStairsCornerInnerPaintedModded;
	public static Block mossyBrickInner;
	public static Block mossyBrickOuter;
	public static Block sandBrickInner;
	public static Block sandBrickOuter;
	public static Block goldBrickInner;
	public static Block goldBrickOuter;
	public static Block ironBrickInner;
	public static Block ironBrickOuter;
	public static Block olivineBrickInner;
	public static Block olivineBrickOuter;
	public static Block permaFrostBrickInner;
	public static Block permaFrostBrickOuter;
	public static Block netherBrickInner;
	public static Block netherBrickOuter;
	public static Block scorchedBrickInner;
	public static Block scorchedBrickOuter;
	public static Block bakedMudBrickInner;
	public static Block bakedMudBrickOuter;
	public static Block permaFrostCobbleInner;
	public static Block permaFrostCobbleOuter;
	public static Block steelBrickInner;
	public static Block steelBrickOuter;
	public static Block quartzBrickInner;
	public static Block quartzBrickOuter;
	public static void init(){
		paintedBox = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0F, 1.0F))
			.setHardness(2.5F)
			.setResistance(5.0F)
			.setTextures(9, 1)
			.setFlammability(2, 2)
			.setItemBlock((b) -> new ItemModBlockPainted(b, false))
			.setTags(new Tag[]{BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU})
			.build(new BlockBoxNoNameDyes("box", patchesBlockID++, Material.wood));

		BlockBuilder cornerInner = new BlockBuilder(MOD_ID)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setBlockModel(new BlockModelRenderBlocks(1300));

		BlockBuilder cornerOuter = new BlockBuilder(MOD_ID)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setBlockModel(new BlockModelRenderBlocks(1301));

		blockStairsCornerPaintedModded = cornerInner
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU)
			.setItemBlock((b) -> new ItemModBlockPainted(b, true))
			.build(new BlockModStairsOuterPainted(ModBlocks.stairsPlanksOakPainted, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();
		blockStairsCornerPaintedModded.setKey("patches.stairs.corner.outer.planks.oak.painted");

		blockStairsCornerInnerPaintedModded = cornerOuter
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU)
			.setItemBlock((b) -> new ItemModBlockPainted(b, true))
			.build(new BlockModStairsInnerPainted(ModBlocks.stairsPlanksOakPainted, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();
		blockStairsCornerInnerPaintedModded.setKey("patches.stairs.corner.inner.planks.oak.painted");

		mossyBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.brickStonePolishedMossy, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		mossyBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.brickStonePolishedMossy, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		sandBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.brickSandstone, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		sandBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.brickSandstone, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		goldBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.brickGold, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		goldBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.brickGold, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		olivineBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickOlivine, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		olivineBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickOlivine, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		permaFrostBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.brickPermafrost, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		permaFrostBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.brickPermafrost, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		ironBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.brickIron, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		ironBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.brickIron, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		netherBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickNetherrack, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		netherBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickNetherrack, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		scorchedBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickScorchedstone, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		scorchedBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickScorchedstone, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		bakedMudBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickMud, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		bakedMudBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickMud, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		permaFrostCobbleInner = cornerInner
			.build(new BlockStairsCornerInner(Block.cobblePermafrost, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		permaFrostCobbleOuter = cornerOuter
			.build(new BlockStairsCorner(Block.cobblePermafrost, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		steelBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickSteel, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		steelBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickSteel, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		quartzBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickQuartz, patchesBlockID++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		quartzBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickQuartz, patchesBlockID++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		for (int i = 0; i < ItemModDye.NUM_DYES; i++) {
			Registries.ITEM_GROUPS.getItem("bonusblocks:box").add(new ItemStack(paintedBox, 1, i));
			RecipeBuilder.Shapeless(MOD_ID)
				.addInput("bonusblocks:box")
				.addInput(new ItemStack(ModItems.dye, 1, i))
				.create("painted_box_dye", new ItemStack(paintedBox, 1, i));
			RecipeBuilder.Shaped(MOD_ID, "CC", "CC")
				.addInput('C', new ItemStack(ModBlocks.chestPlanksOakPainted, 1, i << 4))
				.create("painted_box", new ItemStack(paintedBox, 8, i));
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("X", "X")
				.addInput('X', new ItemStack(ModBlocks.stairsPlanksOakPainted, 1, i << 4))
				.create("modded_painted_stairs", new ItemStack(blockStairsCornerInnerPaintedModded, 2, i << 4));
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("X", "X")
				.addInput('X', new ItemStack(blockStairsCornerInnerPaintedModded, 1, i << 4))
				.create("modded_painted_stairs", new ItemStack(blockStairsCornerPaintedModded, 2, i << 4));
		}
	}
}
