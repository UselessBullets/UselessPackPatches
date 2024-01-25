package useless.patches.compat.item;

import baboon.industry.IndustryTags;
import baboon.industry.item.ItemWrench;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import useless.patches.compat.PatchTags;

public class ItemWrenchUniversal extends ItemWrench {
	public ItemWrenchUniversal(int id) {
		super(id);
	}
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		Block block = world.getBlock(blockX, blockY, blockZ);
		if (entityplayer.isSneaking() && (block.hasTag(IndustryTags.REQUIRES_WRENCH) || block.hasTag(PatchTags.WRENCH_PICKS) || block instanceof BlockTileEntity) && !block.hasTag(PatchTags.WRENCH_EXCLUSION)){
			int meta = world.getBlockMetadata(blockX, blockY, blockZ);
			block.dropBlockWithCause(world, EnumDropCause.PROPER_TOOL, blockX, blockY, blockZ,
				meta,
				world.getBlockTileEntity(blockX, blockY, blockZ));
			world.setBlockWithNotify(blockX, blockY, blockZ, 0);
			if (!Global.isServer){
				Minecraft.getMinecraft(Minecraft.class).sndManager.playSound("patches.ratchet", SoundType.ENTITY_SOUNDS, (float) entityplayer.x, (float) entityplayer.y, (float) entityplayer.z, 1f, 1f);
			}
			return true;
		}
		return false;
	}
}
