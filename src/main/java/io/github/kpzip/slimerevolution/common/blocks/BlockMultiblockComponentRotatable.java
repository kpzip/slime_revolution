package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public abstract class BlockMultiblockComponentRotatable extends BlockHorizontal implements IMultiblockComponent {

	public BlockMultiblockComponentRotatable(Properties property) {
		super(property);
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONAL_FACING, Direction.NORTH).setValue(IS_MULTIBLOCK, false));
	}
	
	public BlockMultiblockComponentRotatable(Properties property, VoxelShape shape) {
		super(property, shape);
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONAL_FACING, Direction.NORTH).setValue(IS_MULTIBLOCK, false));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).setValue(IS_MULTIBLOCK, false);
	}
	
	@Override
	public void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(IS_MULTIBLOCK);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block p_220069_4_, BlockPos p_220069_5_, boolean p_220069_6_) {
		super.neighborChanged(state, world, pos, p_220069_4_, p_220069_5_, p_220069_6_);
		if (!world.isClientSide) {
			if (isMultiBlock(state, world, pos) && !(state.getValue(IS_MULTIBLOCK))) addMultiBlockProperty(state, world, pos);
			if (!(isMultiBlock(state, world, pos)) && state.getValue(IS_MULTIBLOCK)) removeMultiBlockProperty(state, world, pos);
		}
	}
	
	public abstract boolean isMultiBlock(BlockState state, World world, BlockPos pos);
	
	public abstract void addMultiBlockProperty(BlockState state, World world, BlockPos pos);
	
	public abstract void removeMultiBlockProperty(BlockState state, World world, BlockPos pos);

}
