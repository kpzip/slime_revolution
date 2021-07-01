package io.github.kpzip.slimerevolution.common.blocks;

import org.apache.logging.log4j.Level;

import io.github.kpzip.slimerevolution.SlimeRevolution;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public abstract class BlockMultiblockComponent extends BlockHorizontal {
	
	public static final BooleanProperty IS_MULTIBLOCK = BooleanProperty.create("is_multiblock");

	public BlockMultiblockComponent(Properties property) {
		super(property);
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONAL_FACING, Direction.NORTH).setValue(IS_MULTIBLOCK, false));
	}
	
	public BlockMultiblockComponent(Properties property, VoxelShape shape) {
		super(property, shape);
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONAL_FACING, Direction.NORTH).setValue(IS_MULTIBLOCK, false));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).setValue(IS_MULTIBLOCK, false);
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(IS_MULTIBLOCK);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(BlockState observerState, World world, BlockPos observerPos, Block p_220069_4_, BlockPos p_220069_5_, boolean p_220069_6_) {
		super.neighborChanged(observerState, world, observerPos, p_220069_4_, p_220069_5_, p_220069_6_);
		if (!world.isClientSide) {
			SlimeRevolution.LOGGER.log(Level.DEBUG, "Observed Change at " + observerPos.toString());
			if (isMultiBlock(observerState, world, observerPos) && !(observerState.getValue(IS_MULTIBLOCK))) { addMultiBlockProperty(observerState, world, observerPos); SlimeRevolution.LOGGER.log(Level.WARN, "adding multiblock property");}
			if (!(isMultiBlock(observerState, world, observerPos)) && observerState.getValue(IS_MULTIBLOCK)) removeMultiBlockProperty(observerState, world, observerPos);
		}
	}
	
	public abstract boolean isMultiBlock(BlockState state, World world, BlockPos pos);
	
	public abstract void addMultiBlockProperty(BlockState state, World world, BlockPos pos);
	
	public abstract void removeMultiBlockProperty(BlockState state, World world, BlockPos pos);

}
