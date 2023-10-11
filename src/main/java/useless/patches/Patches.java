package useless.patches;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Patches implements ModInitializer {
    public static final String MOD_ID = "patches";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static String ModPackVersion = "Useless' BTA Modpack! 1.2.0";

    @Override
    public void onInitialize() {
        LOGGER.info("ExampleMod initialized.");
    }
}
