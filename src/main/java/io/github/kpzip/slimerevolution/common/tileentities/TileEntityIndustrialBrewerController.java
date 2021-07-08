package io.github.kpzip.slimerevolution.common.tileentities;

import io.github.kpzip.slimerevolution.core.init.TileEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class TileEntityIndustrialBrewerController extends LockableTileEntity implements ISidedInventory, ITickableTileEntity, IFluidHandler {
	
	public FluidTank inTank = new FluidTank(10000); //ID 0
	public FluidTank outTank = new FluidTank(10000); //ID 1
	
	
	

	public TileEntityIndustrialBrewerController() {
		super(TileEntityInit.INDUSTRIAL_BREWER_CONTROLLER_TILE_ENTITY_TYPE.get());
	}
	
	public void encodeExtraData(PacketBuffer buffer) {
		//TODO
	}
	
	@Override
	public CompoundNBT save(CompoundNBT compound) {
		//IMPORTANT: Always call super method since it saves important information about the tile entity
		super.save(compound);
		
		inTank.writeToNBT(compound);
		outTank.writeToNBT(compound);
		
		return compound;
	}
	
	@Override
	public void load(BlockState state, CompoundNBT compound) {
		//IMPORTANT: Always call super method since it loads important information about the tile entity
		super.load(state, compound);
		
		inTank.readFromNBT(compound);
		outTank.readFromNBT(compound);
		
	}

	@Override
	public int getContainerSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getItem(int p_70301_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItem(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean stillValid(PlayerEntity p_70300_1_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clearContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getSlotsForFace(Direction p_180463_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canPlaceItemThroughFace(int p_180462_1_, ItemStack p_180462_2_, Direction p_180462_3_) {
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
		return true;
	}

	@Override
	protected ITextComponent getDefaultName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTanks() {
		return 2;
	}

	@Override
	public FluidStack getFluidInTank(int tank) {
		switch (tank) {
		case 0:
			return inTank.getFluidInTank(0).copy();
		case 1:
			return outTank.getFluidInTank(0).copy();
		default:
			return inTank.getFluidInTank(0).copy();
		}
	}

	@Override
	public int getTankCapacity(int tank) {
		return 10000;
	}

	@Override
	public boolean isFluidValid(int tank, FluidStack stack) {
		return inTank.isFluidValid(tank, stack) || outTank.isFluidValid(tank, stack);
	}

	@Override
	public int fill(FluidStack resource, FluidAction action) {
		return inTank.fill(resource, action);
	}

	@Override
	public FluidStack drain(FluidStack resource, FluidAction action) {
		return outTank.drain(resource, action);
	}

	@Override
	public FluidStack drain(int maxDrain, FluidAction action) {
		return outTank.drain(maxDrain, action);
	}

}
