package io.github.kpzip.slimerevolution.common.tileentities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;

public class TileEntityIndustrialBrewerController extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {

	public TileEntityIndustrialBrewerController() {
		super(type);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
		// TODO Auto-generated method stub
		return false;
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

}
