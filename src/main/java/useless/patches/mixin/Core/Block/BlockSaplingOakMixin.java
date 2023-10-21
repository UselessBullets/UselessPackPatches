package useless.patches.mixin.Core.Block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockSaplingOak;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancyRainforest;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeShapeSwamp;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(value = BlockSaplingOak.class, remap = false)
public abstract class BlockSaplingOakMixin extends Block{
	public BlockSaplingOakMixin(String key, int id, Material material) {
		super(key, id, material);
	}

	@Inject(method = "growTree(Lnet/minecraft/core/world/World;IIILjava/util/Random;)V", at = @At("HEAD"), cancellable = true)
	private void addMossyTreeTypes(World world, int x, int y, int z, Random random, CallbackInfo ci){
		if (checkForMud(world, x, y, z)){
			WorldFeature swampTree = new WorldFeatureTreeShapeSwamp(Block.leavesOak.id, Block.logOakMossy.id, 6);
			world.setBlock(x, y, z, 0);
			if (!(swampTree).generate(world, random, x, y, z)) {
				world.setBlock(x, y, z, id);
			}
			ci.cancel();
		} else if (random.nextInt(8) == 0) {
			WorldFeature rainforestTree;
			if (random.nextInt(2) == 0) {
				rainforestTree = new WorldFeatureTreeFancyRainforest(Block.leavesOak.id, Block.logOakMossy.id, 0);
			} else {
				rainforestTree = new WorldFeatureTreeFancyRainforest(Block.leavesOak.id, Block.logOak.id, 0);
			}
			world.setBlock(x, y, z, 0);
			if (!(rainforestTree).generate(world, random, x, y, z)) {
				world.setBlock(x, y, z, id);
			}
			ci.cancel();
		}
	}
	@Unique
	private boolean checkForMud(World world, int x, int y, int z){
		for (int xCheck = x-1; xCheck < x+2; xCheck++) {
			for (int zCheck = z-1; zCheck < z+2; zCheck++) {
				if (world.getBlockId(xCheck, y-1, zCheck) == Block.mud.id){
					return true;
				}
			}
		}
		return false;
	}
}
