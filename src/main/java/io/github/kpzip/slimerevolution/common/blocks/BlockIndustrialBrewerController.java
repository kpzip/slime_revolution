package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIndustrialBrewerController extends BlockMultiblockComponent {

	public BlockIndustrialBrewerController(Properties property) {
		super(property);
	}

	@Override
	public boolean isMultiBlock(BlockState state, World world, BlockPos pos) {
		return false;
	}

	@Override
	public void updateMultiBlockProperty(BlockState state, World world, BlockPos pos) {
		
	}

}
