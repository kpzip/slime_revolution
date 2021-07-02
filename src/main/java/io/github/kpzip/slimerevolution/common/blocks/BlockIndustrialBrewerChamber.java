package io.github.kpzip.slimerevolution.common.blocks;

import io.github.kpzip.slimerevolution.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIndustrialBrewerChamber extends BlockMultiblockComponent {

	public BlockIndustrialBrewerChamber(Properties property) {
		super(property);
	}

	@Override
	public boolean isMultiBlock(BlockState state, World world, BlockPos pos) {
		return world.getBlockState(pos.below()).getBlock().equals(BlockInit.INDUSTRIAL_BREWER_COLUMN.get())
				&& world.getBlockState(pos.below().below()).getBlock().equals(BlockInit.INDUSTRIAL_BREWER_CONTROLLER.get()) 
				&& world.getBlockState(pos.below()).getValue(BlockHorizontal.HORIZONAL_FACING).equals(state.getValue(BlockHorizontal.HORIZONAL_FACING)) 
				&& world.getBlockState(pos.below().below()).getValue(BlockHorizontal.HORIZONAL_FACING).equals(state.getValue(BlockHorizontal.HORIZONAL_FACING));
	}

	@Override
	public void addMultiBlockProperty(BlockState state, World world, BlockPos pos) {
		world.setBlockAndUpdate(pos, state.setValue(BlockMultiblockComponent.IS_MULTIBLOCK, true));
		world.setBlockAndUpdate(pos.below(), world.getBlockState(pos.below()).setValue(BlockMultiblockComponent.IS_MULTIBLOCK, true));
		world.setBlockAndUpdate(pos.below().below(), world.getBlockState(pos.below().below()).setValue(BlockMultiblockComponent.IS_MULTIBLOCK, true));
	}

	@Override
	public void removeMultiBlockProperty(BlockState state, World world, BlockPos pos) {
		if (state.getBlock().equals(BlockInit.INDUSTRIAL_BREWER_CHAMBER.get())) world.setBlockAndUpdate(pos, state.setValue(BlockMultiblockComponent.IS_MULTIBLOCK, false));
		if (world.getBlockState(pos.below()).getBlock().equals(BlockInit.INDUSTRIAL_BREWER_COLUMN.get())) world.setBlockAndUpdate(pos.below(), world.getBlockState(pos.below()).setValue(BlockMultiblockComponent.IS_MULTIBLOCK, false));
		if (world.getBlockState(pos.below().below()).getBlock().equals(BlockInit.INDUSTRIAL_BREWER_CONTROLLER.get())) world.setBlockAndUpdate(pos.below().below(), world.getBlockState(pos.below().below()).setValue(BlockMultiblockComponent.IS_MULTIBLOCK, false));
	}

	@Override
	public BlockIndustrialBrewerChamber tab(ItemGroup category) {
		this.category = category;
		return this;
	}
	

}
