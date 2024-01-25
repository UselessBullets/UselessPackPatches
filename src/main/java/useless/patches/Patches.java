package useless.patches;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;
import net.minecraft.core.item.Item;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;
import useless.patches.compat.block.KThingsCompat;
import useless.patches.compat.item.BTBTACompat;
import useless.patches.compat.item.WrenchCompat;

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
	public static int patchesBlockID = 10000;
	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {
		KThingsCompat.init();
		WrenchCompat.init();
		BTBTACompat.init();
	}
}
