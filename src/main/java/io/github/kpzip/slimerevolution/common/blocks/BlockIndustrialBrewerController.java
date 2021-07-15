package io.github.kpzip.slimerevolution.common.blocks;

import javax.annotation.Nullable;

import io.github.kpzip.slimerevolution.common.tileentities.TileEntityIndustrialBrewerController;
import io.github.kpzip.slimerevolution.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockIndustrialBrewerController extends BlockMultiblockComponentRotatable implements IBlockFluidContainer {

	public BlockIndustrialBrewerController(Properties property) {
		super(property);
	}

	@Override
	public boolean isMultiBlock(BlockState state, World world, BlockPos pos) {
		return world.getBlockState(pos.above()).getBlock().equals(BlockInit.INDUSTRIAL_BREWER_COLUMN.get())
				&& world.getBlockState(pos.above().above()).getBlock().equals(BlockInit.INDUSTRIAL_BREWER_CHAMBER.get()) 
				&& world.getBlockState(pos.above()).getValue(BlockHorizontal.HORIZONAL_FACING).equals(state.getValue(BlockHorizontal.HORIZONAL_FACING)) 
				&& world.getBlockState(pos.above().above()).getValue(BlockHorizontal.HORIZONAL_FACING).equals(state.getValue(BlockHorizontal.HORIZONAL_FACING));
	}

	@Override
	public void addMultiBlockProperty(BlockState state, World world, BlockPos pos) {
		world.setBlockAndUpdate(pos, state.setValue(BlockMultiblockComponentRotatable.IS_MULTIBLOCK, true));
		world.setBlockAndUpdate(pos.above(), world.getBlockState(pos.above()).setValue(BlockMultiblockComponentRotatable.IS_MULTIBLOCK, true));
		world.setBlockAndUpdate(pos.above().above(), world.getBlockState(pos.above().above()).setValue(BlockMultiblockComponentRotatable.IS_MULTIBLOCK, true));
		
	}

	@Override
	public void removeMultiBlockProperty(BlockState state, World world, BlockPos pos) {
		if (state.getBlock().equals(BlockInit.INDUSTRIAL_BREWER_COLUMN.get())) world.setBlockAndUpdate(pos, state.setValue(BlockMultiblockComponentRotatable.IS_MULTIBLOCK, false));
		if (world.getBlockState(pos.above()).getBlock().equals(BlockInit.INDUSTRIAL_BREWER_CHAMBER.get())) world.setBlockAndUpdate(pos.above(), world.getBlockState(pos.above()).setValue(BlockMultiblockComponentRotatable.IS_MULTIBLOCK, false));
		if (world.getBlockState(pos.below()).getBlock().equals(BlockInit.INDUSTRIAL_BREWER_CONTROLLER.get())) world.setBlockAndUpdate(pos.below(), world.getBlockState(pos.below()).setValue(BlockMultiblockComponentRotatable.IS_MULTIBLOCK, false));
		
	}

	@Override
	public BlockIndustrialBrewerController tab(ItemGroup category) {
		this.category = category;
		return this;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileEntityIndustrialBrewerController();
	}
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult traceResult) {
		if (!state.getValue(BlockMultiblockComponentRotatable.IS_MULTIBLOCK)) {
			return ActionResultType.FAIL;
		}
		if (world.isClientSide()) {
			return ActionResultType.SUCCESS;
		}
		Item inHand = player.getItemInHand(hand).getItem();
		if (inHand instanceof BucketItem) {
			return addFluidFromBucket(state, world, pos, player, (BucketItem) inHand, world.getBlockEntity(pos), hand);
		}
		this.interact(world, pos, player);
		return ActionResultType.CONSUME;
		
	}
	
	public ActionResultType addFluidFromBucket(BlockState state, World world, BlockPos pos, PlayerEntity player, BucketItem inHand, TileEntity te, Hand hand) {
		TileEntityIndustrialBrewerController tile = (TileEntityIndustrialBrewerController) te;
		if (tile.getFluidInTank(0).getAmount() <= tile.getTankCapacity(0) - 1000 && tile.getFluidInTank(0).getFluid() == inHand.getFluid() && inHand.getFluid() != Fluids.EMPTY) {
			tile.fill(new FluidStack(inHand.getFluid(), 1000), FluidAction.EXECUTE);
			player.setItemInHand(hand, new ItemStack(Items.BUCKET, 1));
			return ActionResultType.CONSUME;
		}
		else if(inHand.getFluid() == Fluids.EMPTY && tile.getFluidInTank(1).getAmount() >= 1000 && tile.getFluidInTank(1).getFluid() != Fluids.EMPTY) {
			Fluid toRemoveType = tile.getFluidInTank(1).getFluid();
			tile.drain(new FluidStack(toRemoveType, 1000), FluidAction.EXECUTE);
			player.setItemInHand(hand, new ItemStack(toRemoveType.getBucket()));
			return ActionResultType.CONSUME;
		}
		return ActionResultType.FAIL;
		
		
	}

	private void interact(World world, BlockPos pos, PlayerEntity player) {
		TileEntity te = world.getBlockEntity(pos);
		if (te instanceof TileEntityIndustrialBrewerController && player instanceof ServerPlayerEntity) {
			TileEntityIndustrialBrewerController te2 = (TileEntityIndustrialBrewerController) te;
			NetworkHooks.openGui((ServerPlayerEntity) player, te2, te2::encodeExtraData);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			TileEntity te = world.getBlockEntity(pos);
			if (te instanceof IInventory) {
				InventoryHelper.dropContents(world, pos, (IInventory) te);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}
	
	
	

}
