package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.StateContainer.Builder;

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
