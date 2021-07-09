package io.github.kpzip.slimerevolution.common.tileentities;

import javax.annotation.Nullable;

import io.github.kpzip.slimerevolution.common.recipe.IUsesMachineRecipe;
import io.github.kpzip.slimerevolution.common.recipe.IndustrialBrewingRecipe;
import io.github.kpzip.slimerevolution.core.init.RecipeTypeInit;
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
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class TileEntityIndustrialBrewerController extends LockableTileEntity implements ISidedInventory, ITickableTileEntity, IFluidHandler, IUsesMachineRecipe<IndustrialBrewingRecipe> {
	
	public static final int NETHER_WART_SLOT = 0;
	public static final int MAIN_INGREDIENT = 1;
	public static final int INVERTER_SLOT = 2;
	public static final int SPLASH_SLOT = 3;
	public static final int MODIFIER_SLOT = 4;
	public static final int LINGERING_SLOT = 5;
	public static final int RESIDUE_SLOT = 6;
	
	
	
	private FluidTank inTank = new FluidTank(10000); //ID 0
	private FluidTank outTank = new FluidTank(10000); //ID 1
	
	private int progress;
	
	private final IIntArray fields = new IIntArray() {

		@Override
		public int get(int index) {
			switch (index) {
			case 0:
				return progress;
			default:
				return 0;
			}
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
			case 0:
				progress = value;
				break;
			default:
				break;
			}
			
		}

		@Override
		public int getCount() {
			return 1;
		}
		
	};

	public TileEntityIndustrialBrewerController() {
		super(TileEntityInit.INDUSTRIAL_BREWER_CONTROLLER_TILE_ENTITY_TYPE.get());
	}
	
	public void encodeExtraData(PacketBuffer buffer) {
		buffer.writeByte(fields.getCount());
	}
	
	@Override
	public CompoundNBT save(CompoundNBT compound) {
		//IMPORTANT: Always call super method since it saves important information about the tile entity
		super.save(compound);
		
		inTank.writeToNBT(compound);
		outTank.writeToNBT(compound);
		compound.putInt("Progress", progress);
		
		return compound;
	}
	
	@Override
	public void load(BlockState state, CompoundNBT compound) {
		//IMPORTANT: Always call super method since it loads important information about the tile entity
		super.load(state, compound);
		
		inTank.readFromNBT(compound);
		outTank.readFromNBT(compound);
		progress = compound.getInt("Progress");
		
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
		
	}
	
	//returns null if there is no valid recipe
	@Nullable
	@Override
	public IndustrialBrewingRecipe getRecipe() {
		if (this.level == null || getItem(0).isEmpty()) {
			return null;
		}
		return this.level.getRecipeManager().getRecipeFor(RecipeTypeInit.INDUSTRIAL_BREWING, this, this.level).orElse(null);
	}
	
	@Override
	public ItemStack getWorkOutput(@Nullable IndustrialBrewingRecipe recipe) {
		if (recipe != null) {
			return recipe.assemble(this);
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public void doWork(IndustrialBrewingRecipe recipe) {
		assert this.level != null;
		
		ItemStack current = getItem(RESIDUE_SLOT);
		ItemStack output = getWorkOutput(recipe);
		
		if (!current.isEmpty()) {
			int newcount = current.getCount() + output.getCount();
			if (!ItemStack.matches(current, output) || newcount > output.getMaxStackSize()) {
				stopWork();
				return;
			}
			
		}
		
		if (progress < recipe.getDuration()) {
			progress ++;
		}
		else if (progress >= recipe.getDuration() && !this.level.isClientSide) {
			finishWork(recipe, current, output);
		}
		
	}
	
	@Override
	public void stopWork() {
		progress = 0;
	}
	
	@Override
	public void finishWork(IndustrialBrewingRecipe recipe, ItemStack current, ItemStack output) {
		if (!current.isEmpty()) {
			current.grow(output.getCount());
		}
		else {
			setItem(RESIDUE_SLOT, output);
		}
		progress = 0;
		
		this.removeItem(NETHER_WART_SLOT, 1);
		this.removeItem(MAIN_INGREDIENT, 1);
		this.removeItem(INVERTER_SLOT, 1);
		this.removeItem(SPLASH_SLOT, 1);
		this.removeItem(MODIFIER_SLOT, 1);
		this.removeItem(LINGERING_SLOT, 1);
		
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
