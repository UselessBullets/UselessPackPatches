package useless.patches.compat;

import net.minecraft.core.block.Block;
import net.minecraft.core.data.tag.Tag;

public class PatchTags {
	public static Tag<Block> WRENCH_PICKS = Tag.of("wrench_picks_up");
	public static Tag<Block> WRENCH_EXCLUSION = Tag.of("wrench_immune");
}
