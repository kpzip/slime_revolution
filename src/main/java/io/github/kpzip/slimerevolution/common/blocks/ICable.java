package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;

public interface ICable {
	
	public static final BooleanProperty HAS_NORTH = BooleanProperty.create("has_north");
	public static final BooleanProperty HAS_EAST = BooleanProperty.create("has_east");
	public static final BooleanProperty HAS_SOUTH = BooleanProperty.create("has_south");
	public static final BooleanProperty HAS_WEST = BooleanProperty.create("has_west");
	public static final BooleanProperty HAS_UP = BooleanProperty.create("has_up");
	public static final BooleanProperty HAS_DOWN = BooleanProperty.create("has_down");
	
	public void createBlockStateDefinition(Builder<Block, BlockState> builder);
	
	public BlockState getStateForPlacement(BlockItemUseContext context);

}
