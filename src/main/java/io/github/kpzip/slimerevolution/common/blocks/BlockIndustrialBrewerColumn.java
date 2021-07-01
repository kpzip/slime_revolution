package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIndustrialBrewerColumn extends BlockMultiblockComponent {

	public BlockIndustrialBrewerColumn(Properties property) {
		super(property);
	}

	@Override
	public boolean isMultiBlock(BlockState state, World world, BlockPos pos) {
		return false;
	}

	@Override
	public void addMultiBlockProperty(BlockState state, World world, BlockPos pos) {
		
		
	}

	@Override
	public void removeMultiBlockProperty(BlockState state, World world, BlockPos pos) {
		
		
	}

	

}
