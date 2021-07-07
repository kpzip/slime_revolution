package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IMultiblockComponent {
	
	public static final BooleanProperty IS_MULTIBLOCK = BooleanProperty.create("is_multiblock");
	
	public BlockState getStateForPlacement(BlockItemUseContext context);
	
	public void createBlockStateDefinition(Builder<Block, BlockState> builder);
	
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean bool);
	
	public boolean isMultiBlock(BlockState state, World world, BlockPos pos);
	
	public void addMultiBlockProperty(BlockState state, World world, BlockPos pos);
	
	public void removeMultiBlockProperty(BlockState state, World world, BlockPos pos);

}
