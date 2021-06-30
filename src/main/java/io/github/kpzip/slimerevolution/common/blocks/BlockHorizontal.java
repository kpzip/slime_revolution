package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

public class BlockHorizontal extends Block {
	
	public static final DirectionProperty HORIZONAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

	public BlockHorizontal(Properties property) {
		super(property);
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONAL_FACING, Direction.NORTH));
	}

}
