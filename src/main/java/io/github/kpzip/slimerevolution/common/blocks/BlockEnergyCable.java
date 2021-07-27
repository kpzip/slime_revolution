package io.github.kpzip.slimerevolution.common.blocks;

import io.github.kpzip.slimerevolution.common.Shapes;
import io.github.kpzip.slimerevolution.common.energy.ISlimeEnergyHandler;
import io.github.kpzip.slimerevolution.common.tileentities.TileEntityEnergyCable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockEnergyCable extends BlockBase implements ICable {
	
	
	
	public BlockEnergyCable(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HAS_NORTH, false)
															.setValue(HAS_EAST, false)
															.setValue(HAS_SOUTH, false)
															.setValue(HAS_WEST, false)
															.setValue(HAS_UP, false)
															.setValue(HAS_DOWN, false));
	}
	
	@Override
	public void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HAS_NORTH);
		builder.add(HAS_EAST);
		builder.add(HAS_SOUTH);
		builder.add(HAS_WEST);
		builder.add(HAS_UP);
		builder.add(HAS_DOWN);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context);
	}
	
	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean isMoving) {
		refreshConnections(state, world, pos);
	}
	
	@Override
	public void refreshConnections(BlockState state, World world, BlockPos pos) {
		if (world.getBlockEntity(pos.north()) instanceof ISlimeEnergyHandler) {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_NORTH, true));
		}
		else {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_NORTH, false));
		}
		if (world.getBlockEntity(pos.east()) instanceof ISlimeEnergyHandler) {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_EAST, true));
		}
		else {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_EAST, false));
		}
		if (world.getBlockEntity(pos.south()) instanceof ISlimeEnergyHandler) {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_SOUTH, true));
		}
		else {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_SOUTH, false));
		}
		if (world.getBlockEntity(pos.west()) instanceof ISlimeEnergyHandler) {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_WEST, true));
		}
		else {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_WEST, false));
		}
		if (world.getBlockEntity(pos.above()) instanceof ISlimeEnergyHandler) {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_UP, true));
		}
		else {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_UP, false));
		}
		if (world.getBlockEntity(pos.below()) instanceof ISlimeEnergyHandler) {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_DOWN, true));
		}
		else {
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(HAS_DOWN, false));
		}
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileEntityEnergyCable();
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return Shapes.SHAPE_CABLE_CENTER;
	}

	
	//Overriding these methods so the deferred register doesn't have a tantrum
	@Override
	public BlockEnergyCable tab(ItemGroup category) {
		this.category = category;
		return this;
	}
	
	@Override
	public BlockEnergyCable setHasItem(boolean b) {
		this.hasItem = b;
		return this;
	}


}
