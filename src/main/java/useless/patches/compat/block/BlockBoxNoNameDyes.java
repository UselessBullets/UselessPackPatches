package useless.patches.compat.block;

import goocraft4evr.nonamedyes.item.ItemModDye;
import luke.bonusblocks.block.BlockPaintedBox;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.TextureHelper;

import java.util.ArrayList;
import java.util.List;

public class BlockBoxNoNameDyes extends BlockPaintedBox {
	private static final List<Integer> textures = new ArrayList<>();
	public BlockBoxNoNameDyes(String key, int id, Material material) {
		super(key, id, material);
	}
	public int getBlockTextureFromSideAndMetadata(Side side, int meta) {
		return textures.get(meta & 15) == null ? textures.get(0) : textures.get(meta & 15);
	}
	static {
		for(String s : ItemModDye.dyeColors) {
			textures.add(TextureHelper.getOrCreateBlockTextureIndex("nonamedyes", "chest/" + s.replace(".", "_") + "_chest_top.png"));
		}
	}
}
