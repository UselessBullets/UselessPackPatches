package useless.patches;

import Killi.Things.Block.BlockStairsCorner;
import Killi.Things.Block.BlockStairsCornerInner;
import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.item.ItemModDye;
import goocraft4evr.nonamedyes.item.ModItems;
import goocraft4evr.nonamedyes.item.block.ItemModBlockPainted;
import luke.bonusblocks.BonusBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.ContainerPlayerCreative;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.GameStartEntrypoint;
import useless.patches.compat.block.BlockBoxNoNameDyes;
import useless.patches.compat.block.BlockModStairsInnerPainted;
import useless.patches.compat.block.BlockModStairsOuterPainted;

import java.util.*;


public class Patches implements ModInitializer, GameStartEntrypoint {
    public static final String MOD_ID = "patches";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static String ModPackVersion;
	static {
		ModPackVersion = FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata().getVersion().getFriendlyString();
		if (ModPackVersion.equals("${version}")){
			ModPackVersion = "INDEV";
		}
	}
	public static String ModPackString = "Useless' BTA Modpack! " + ModPackVersion;
    @Override
    public void onInitialize() {
        LOGGER.info("Modpack Patches initialized.");
    }
	public static String modTable(){
		Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();
		List<String> modNames = new ArrayList<>();
		List<String> modVersions = new ArrayList<>();
		List<String> modAuthors = new ArrayList<>();
		for (ModContainer mod: mods) {
			ModMetadata metadata = mod.getMetadata();
			if (metadata.getName().equals("Minecraft")){
				modNames.add("Minecraft BTA");
			}
			else {
				modNames.add(metadata.getName());
			}
			modVersions.add(metadata.getVersion().getFriendlyString());
			StringBuilder authors = new StringBuilder();
			Collection<Person> authorsList = metadata.getAuthors();
			for (Person author:authorsList) {
				authors.append(author.getName()).append(", ");
			}
			if (authorsList.size() == 0){
				if (metadata.getName().equals("Minecraft")){
					authors.append("Majonk, ");
				}
				else {
					authors.append("N/A, ");
				}
			}
			String authorsString = authors.toString();
			modAuthors.add(authorsString.substring(0,authorsString.length()-2));
		}
		int longestName = longestString(modNames);
		int longestVersion = longestString(modVersions);
		int longestAuthor = longestString(modAuthors);
		StringBuilder stringBuilder = new StringBuilder("\nMod List\n").append(StringUtils.repeat(" ", 4)).append(StringUtils.rightPad("Name", longestName+4)).append(StringUtils.rightPad("Version", longestVersion+4)).append("Authors\n");
		stringBuilder.append(StringUtils.repeat("=", longestName + longestAuthor + longestVersion + 16)).append("\n");
		int modSize = mods.size();
		for (int i = 0; i < modSize; i++) {
			stringBuilder.append("||  ");
			stringBuilder.append(StringUtils.rightPad(modNames.get(i), longestName)).append(" |  ");
			stringBuilder.append(StringUtils.rightPad(modVersions.get(i), longestVersion)).append(" |  ");
			stringBuilder.append(StringUtils.rightPad(modAuthors.get(i), longestAuthor)).append("  ||\n");
		}
		stringBuilder.append(StringUtils.repeat("=", longestName + longestAuthor + longestVersion + 16)).append("\n");
		return stringBuilder.toString();
	}
	public static int longestString(List<String> stringList){
		String longest = stringList.stream().max(Comparator.comparingInt(String::length)).get();
		return longest.length();
	}
	@Override
	public void beforeGameStart() {

	}
	private static int id = 10000;
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
	@Override
	public void afterGameStart() {
		paintedBox = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0F, 1.0F))
			.setHardness(2.5F)
			.setResistance(5.0F)
			.setTextures(9, 1)
			.setFlammability(2, 2)
			.setItemBlock((b) -> new ItemModBlockPainted(b, false))
			.setTags(new Tag[]{BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU})
			.build(new BlockBoxNoNameDyes("box", id++, Material.wood));

		BlockBuilder cornerInner = new BlockBuilder(MOD_ID)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setBlockModel(new BlockModelRenderBlocks(1300));

		BlockBuilder cornerOuter = new BlockBuilder(MOD_ID)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setBlockModel(new BlockModelRenderBlocks(1301));

		blockStairsCornerPaintedModded = cornerInner
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU)
			.setItemBlock((b) -> new ItemModBlockPainted(b, true))
			.build(new BlockModStairsOuterPainted(ModBlocks.stairsPlanksOakPainted, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();
		blockStairsCornerPaintedModded.setKey("patches.stairs.corner.outer.planks.oak.painted");

		blockStairsCornerInnerPaintedModded = cornerOuter
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.NOT_IN_CREATIVE_MENU)
			.setItemBlock((b) -> new ItemModBlockPainted(b, true))
			.build(new BlockModStairsInnerPainted(ModBlocks.stairsPlanksOakPainted, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();
		blockStairsCornerInnerPaintedModded.setKey("patches.stairs.corner.inner.planks.oak.painted");

		mossyBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.brickStonePolishedMossy, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		mossyBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.brickStonePolishedMossy, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		sandBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.brickSandstone, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		sandBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.brickSandstone, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		goldBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.brickGold, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		goldBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.brickGold, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		olivineBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickOlivine, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		olivineBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickOlivine, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		permaFrostBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.permafrost, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		permaFrostBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.permafrost, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		ironBrickInner = cornerInner
			.build(new BlockStairsCornerInner(Block.brickIron, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		ironBrickOuter = cornerOuter
			.build(new BlockStairsCorner(Block.brickIron, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		netherBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickNetherrack, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		netherBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickNetherrack, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		scorchedBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickScorchedstone, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		scorchedBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickScorchedstone, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		bakedMudBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickMud, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		bakedMudBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickMud, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		permaFrostCobbleInner = cornerInner
			.build(new BlockStairsCornerInner(Block.cobblePermafrost, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		permaFrostCobbleOuter = cornerOuter
			.build(new BlockStairsCorner(Block.cobblePermafrost, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		steelBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickSteel, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		steelBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickSteel, id++, ""))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		quartzBrickInner = cornerInner
			.build(new BlockStairsCornerInner(BonusBlocks.brickQuartz, id++))
			.withLitInteriorSurface(true)
			.withDisabledNeighborNotifyOnMetadataChange();

		quartzBrickOuter = cornerOuter
			.build(new BlockStairsCorner(BonusBlocks.brickQuartz, id++, ""))
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
//			RecipeBuilder.Shaped(MOD_ID, "X", "X")
//				.addInput('X', new ItemStack(ModBlocks.chestPlanksOakPainted, 1, i << 4))
//				.create("painted_corner_stairs", new ItemStack(paintedBox, 8, i));
			ContainerPlayerCreative.creativeItems.add(new ItemStack(paintedBox, 1, i));
//			ContainerPlayerCreative.creativeItems.add(new ItemStack(blockStairsCornerPaintedModded, 1, i));
			ContainerPlayerCreative.creativeItemsCount += 1;
		}
		for (int i = 0; i < ItemModDye.NUM_DYES; i++) {
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("X", "X")
				.addInput('X', new ItemStack(ModBlocks.stairsPlanksOakPainted, 1, i << 4))
				.create("modded_painted_stairs", new ItemStack(blockStairsCornerInnerPaintedModded, 2, i << 4));
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("X", "X")
				.addInput('X', new ItemStack(blockStairsCornerInnerPaintedModded, 1, i << 4))
				.create("modded_painted_stairs", new ItemStack(blockStairsCornerPaintedModded, 2, i << 4));

			ContainerPlayerCreative.creativeItems.add(new ItemStack(blockStairsCornerPaintedModded, 1, i << 4));
			ContainerPlayerCreative.creativeItems.add(new ItemStack(blockStairsCornerInnerPaintedModded, 1, i << 4));
			ContainerPlayerCreative.creativeItemsCount += 2;
		}
	}
}
