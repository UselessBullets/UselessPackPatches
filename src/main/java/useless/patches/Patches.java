package useless.patches;

import goocraft4evr.nonamedyes.block.ModBlocks;
import goocraft4evr.nonamedyes.item.ItemModDye;
import goocraft4evr.nonamedyes.item.ModItems;
import goocraft4evr.nonamedyes.item.block.ItemModBlockPainted;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlockPainted;
import net.minecraft.core.player.inventory.ContainerPlayerCreative;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;
import turniplabs.halplibe.util.GameStartEntrypoint;
import useless.patches.compat.block.BlockBoxNoNameDyes;

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
	public static Block paintedBox = new BlockBuilder(MOD_ID)
		.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0F, 1.0F))
		.setHardness(2.5F)
		.setResistance(5.0F)
		.setTextures(9, 1)
		.setFlammability(2, 2)
		.setItemBlock((b) -> new ItemModBlockPainted(b, false))
		.setTags(new Tag[]{BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT, BlockTags.NOT_IN_CREATIVE_MENU})
		.build(new BlockBoxNoNameDyes("box", id++, Material.wood));
//	public static Block moddedPaintedCornerStairs = new BlockBuilder(MOD_ID);

	@Override
	public void afterGameStart() {

		for (int i = 0; i < ItemModDye.NUM_DYES; i++) {
			Registries.ITEM_GROUPS.getItem("bonusblocks:box").add(new ItemStack(paintedBox, 1, i));
			RecipeBuilder.Shapeless(MOD_ID)
				.addInput("bonusblocks:box")
				.addInput(new ItemStack(ModItems.dye, 1, i))
				.create("painted_box_dye", new ItemStack(paintedBox, 1, i));
			RecipeBuilder.Shaped(MOD_ID, "CC", "CC")
				.addInput('C', new ItemStack(ModBlocks.chestPlanksOakPainted, 1, i << 4))
				.create("painted_box", new ItemStack(paintedBox, 8, i));
			ContainerPlayerCreative.creativeItems.add(new ItemStack(paintedBox, 1, i));
			ContainerPlayerCreative.creativeItemsCount += 1;
		}
	}
}
