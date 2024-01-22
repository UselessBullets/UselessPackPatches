package useless.patches.compat.block;

import Killi.Things.Block.BlockStairsCornerInner;
import goocraft4evr.nonamedyes.block.wood.BlockModPlanksPainted;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModStairsInnerPainted extends BlockStairsCornerInner {
	public BlockModStairsInnerPainted(Block modelBlock, int id) {
		super(modelBlock, id);
	}

	public int getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
		return this.getBlockTextureFromSideAndMetadata(side, blockAccess.getBlockMetadata(x, y, z));
	}

	public int getBlockTextureFromSideAndMetadata(Side side, int meta) {
		return BlockModPlanksPainted.getIndexFromMeta(meta, true);
	}

	public static int getMetaForDyeColor(int i) {
		return i << 4;
	}
}
