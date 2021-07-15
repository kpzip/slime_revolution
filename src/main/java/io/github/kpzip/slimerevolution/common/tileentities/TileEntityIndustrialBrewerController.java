package io.github.kpzip.slimerevolution.common.tileentities;

import javax.annotation.Nullable;

import io.github.kpzip.slimerevolution.common.container.IndustrialBrewerContainer;
import io.github.kpzip.slimerevolution.common.recipe.IUsesMachineRecipe;
import io.github.kpzip.slimerevolution.common.recipe.IndustrialBrewingRecipe;
import io.github.kpzip.slimerevolution.core.ModVars;
import io.github.kpzip.slimerevolution.core.init.RecipeTypeInit;
import io.github.kpzip.slimerevolution.core.init.TileEntityInit;
import io.github.kpzip.slimerevolution.core.util.NBTHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileEntityIndustrialBrewerController extends LockableTileEntity implements ISidedInventory, ITickableTileEntity, IFluidHandler, IUsesMachineRecipe<IndustrialBrewingRecipe> {
	
	public static final int NETHER_WART_SLOT = 0;
	public static final int MAIN_INGREDIENT = 1;
	public static final int INVERTER_SLOT = 2;
	public static final int SPLASH_SLOT = 3;
	public static final int MODIFIER_SLOT = 4;
	public static final int LINGERING_SLOT = 5;
	public static final int RESIDUE_SLOT = 6;
	
	private NonNullList<ItemStack> items;
	private final LazyOptional<? extends IItemHandler> [] handlers;
	
	private FluidTank inTank = new FluidTank(10000); //ID 0
	private FluidTank outTank = new FluidTank(10000); //ID 1
	
	private int progress;
	
	private final IIntArray fields = new IIntArray() {

		@SuppressWarnings("deprecation")
		@Override
		public int get(int index) {
			switch (index) {
			case 0:
				return progress;
			case 1:
				return inTank.getFluidAmount();
			case 2:
				return Registry.FLUID.getId(inTank.getFluid().getFluid());
			case 3:
				return outTank.getFluidAmount();
			case 4:
				return Registry.FLUID.getId(outTank.getFluid().getFluid());
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
			case 1:
				inTank.getFluid().setAmount(value);
				break;
			case 2:
				//unable to set fluid types with this method
				break;
			case 3:
				outTank.getFluid().setAmount(value);
				break;
			case 4:
				//unable to set fluid types with this method
				break;
			default:
				break;
			}
			
		}

		@Override
		public int getCount() {
			return 4;
		}
		
	};

	public TileEntityIndustrialBrewerController() {
		super(TileEntityInit.INDUSTRIAL_BREWER_CONTROLLER_TILE_ENTITY_TYPE.get());
		this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
		this.items = NonNullList.withSize(7, ItemStack.EMPTY);
	}
	
	public void encodeExtraData(PacketBuffer buffer) {
		buffer.writeByte(fields.getCount());
	}
	
	@Override
	public CompoundNBT save(CompoundNBT compound) {
		//IMPORTANT: Always call super method since it saves important information about the tile entity
		super.save(compound);
		
		ItemStackHelper.saveAllItems(compound, items);
		NBTHelper.writeFluidTankToNBTWithPrefix(compound, inTank, "InTank");
		NBTHelper.writeFluidTankToNBTWithPrefix(compound, outTank, "OutTank");
		compound.putInt("Progress", progress);
		
		return compound;
	}
	
	@Override
	public void load(BlockState state, CompoundNBT compound) {
		//IMPORTANT: Always call super method since it loads important information about the tile entity
		super.load(state, compound);
		
		this.items = NonNullList.withSize(7, ItemStack.EMPTY);
		
		
		ItemStackHelper.loadAllItems(compound, this.items);
		NBTHelper.loadFluidTankFromNBTWithPrefix(compound, inTank, "InTank");
		NBTHelper.loadFluidTankFromNBTWithPrefix(compound, outTank, "OutTank");
		progress = compound.getInt("Progress");
		
	}
	
	@Override
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT compound = this.getUpdateTag();
		
		ItemStackHelper.saveAllItems(compound, this.items);
		NBTHelper.writeFluidTankToNBTWithPrefix(compound, inTank, "InTank");
		NBTHelper.writeFluidTankToNBTWithPrefix(compound, outTank, "OutTank");
		compound.putInt("Progress", progress);
		
		return new SUpdateTileEntityPacket(this.worldPosition, 1, compound);
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT tag = super.getUpdateTag();
		tag.putInt("Progress", this.progress);
		return tag;
	}
	
	@Nullable
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (!this.remove && side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (side == Direction.UP) {
				return this.handlers[0].cast();
			}
			else if (side == Direction.DOWN) {
				return this.handlers[1].cast();
			}
			else {
				return this.handlers[2].cast();
			}
		}
		else {
			return super.getCapability(cap, side);
		}
	}
	
	@Override
	public void setRemoved() {
		super.setRemoved();
		
		for (LazyOptional<? extends IItemHandler> handler : this.handlers) {
			handler.invalidate();
		}
		
	}

	@Override
	public int getContainerSize() {
		return 7;
	}

	@Override
	public boolean isEmpty() {
		return getItem(NETHER_WART_SLOT).isEmpty() && 
				getItem(MAIN_INGREDIENT).isEmpty() &&
				getItem(INVERTER_SLOT).isEmpty() &&
				getItem(SPLASH_SLOT).isEmpty() &&
				getItem(MODIFIER_SLOT).isEmpty() &&
				getItem(LINGERING_SLOT).isEmpty() &&
				getItem(RESIDUE_SLOT).isEmpty();
	}

	@Override
	public ItemStack getItem(int index) {
		return items.get(index);
	}

	@Override
	public ItemStack removeItem(int index, int ammount) {
		return ItemStackHelper.removeItem(items, index, ammount);
	}

	@Override
	public ItemStack removeItemNoUpdate(int index) {
		return ItemStackHelper.takeItem(items, index);
	}

	@Override
	public void setItem(int index, ItemStack item) {
		items.set(index, item);
		
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return this.level != null && this.level.getBlockEntity(this.worldPosition) == this && 
				player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ()) <= 64;
	}

	@Override
	public void clearContent() {
		items.clear();
		
	}

	@Override
	public void tick() {
		if (this.level == null || this.level.isClientSide) {
			return;
		}
		this.setChanged();
		IndustrialBrewingRecipe recipe = getRecipe();
		if (recipe != null) {
			doWork(recipe);
		}
		else {
			stopWork();
		}
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
		else if (progress >= recipe.getDuration()) {
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
	public int[] getSlotsForFace(Direction facing) {
		if (facing.equals(Direction.DOWN)) {
			return new int [] {RESIDUE_SLOT};
		}
		else {
			return new int [] {NETHER_WART_SLOT, MAIN_INGREDIENT, INVERTER_SLOT, SPLASH_SLOT, MODIFIER_SLOT, LINGERING_SLOT};
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack item, Direction facing) {
		return index != RESIDUE_SLOT;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack item, Direction facing) {
		return index == RESIDUE_SLOT;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + ModVars.MOD_ID + ".industrial_brewer");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory playerInv) {
		return new IndustrialBrewerContainer(id, playerInv, this, this.fields);
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
	
	public int getProgress() {
		return this.progress;
	}

}
