package useless.patches;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Patches implements ModInitializer {
    public static final String MOD_ID = "patches";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static String ModPackVersion = FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata().getVersion().getFriendlyString();
	public static String ModPackString = "Useless' BTA Modpack! " + ModPackVersion;
    @Override
    public void onInitialize() {
        LOGGER.info("Modpack Patches initialized.");
    }
}
