package useless.patches.compat.item;

import goocraft4evr.nonamedyes.item.ModItems;
import lexal.btb.crafting.RecipeEntryDyedArmor;
import net.minecraft.client.util.helper.Colors;
import net.minecraft.core.util.helper.Color;

import java.util.HashMap;

public class BTBTACompat {
	public static void init(){
		HashMap<Integer, Color> noNameDyesMap = new HashMap<>();
		Color[] noNameColors = new Color[12];
		Colors.fillColorArray(null, "/assets/nonamedyes/misc/colors_signs.png", noNameColors);
		for (int color = 0; color < noNameColors.length; color++) {
			noNameDyesMap.put(color, noNameColors[color]);
		}
		RecipeEntryDyedArmor.dyeMap.put(ModItems.dye, noNameDyesMap);
	}
}
