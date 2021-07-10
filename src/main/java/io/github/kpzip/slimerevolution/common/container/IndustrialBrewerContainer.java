package io.github.kpzip.slimerevolution.common.container;

import io.github.kpzip.slimerevolution.common.container.slot.SlotMachineInput;
import io.github.kpzip.slimerevolution.common.container.slot.SlotResult;
import io.github.kpzip.slimerevolution.common.container.slot.SlotType;
import io.github.kpzip.slimerevolution.common.tileentities.TileEntityIndustrialBrewerController;
import io.github.kpzip.slimerevolution.core.init.ContainerInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class IndustrialBrewerContainer extends Container {
	
	private final IInventory inventory;
	private IIntArray fields;
	
	
	
	
	//client side constructor
	public IndustrialBrewerContainer(int id, PlayerInventory playerInv, PacketBuffer buffer) {
		this(id, playerInv, new TileEntityIndustrialBrewerController(), new IntArray(buffer.readByte()));
	}
	
	
	
	
	//server side constructor
	public IndustrialBrewerContainer(int id, PlayerInventory playerInv, IInventory inventory, IIntArray fields) {
		
		super(ContainerInit.INDUSTRIAL_BREWER_CONTAINER.get(), id);
		
		//Basic constructor stuff
		this.inventory = inventory;
		this.fields = fields;
		
		//Machine Slots
		this.addSlot(new SlotMachineInput(this.inventory, 0, 44, 22, SlotType.NETHER_WART_SLOT));
		this.addSlot(new SlotMachineInput(this.inventory, 1, 74, 35, SlotType.MAIN_INGREDIENT_SLOT));
		this.addSlot(new SlotMachineInput(this.inventory, 2, 92, 35, SlotType.INVERTER_SLOT));
		this.addSlot(new SlotMachineInput(this.inventory, 3, 110, 35, SlotType.SPLASH_SLOT));
		this.addSlot(new SlotMachineInput(this.inventory, 4, 128, 35, SlotType.MODIFIER_SLOT));
		this.addSlot(new SlotMachineInput(this.inventory, 5, 146, 35, SlotType.LINGERING_SLOT));
		this.addSlot(new SlotResult(this.inventory, 6, 146, 71));
		
		
		//Player Slots
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				int index = 9 + x + y * 9;
				int posX = 8 + x * 18;
				int posY = 84 + y * 18;
				this.addSlot(new Slot(playerInv, index, posX, posY));
			}
		}
		
		//Player Hotbar
		for (int x = 0; x < 9; x++) {
			int index = x;
			int posX = 8 + x * 18;
			int posY = 142;
			this.addSlot(new Slot(playerInv, index, posX, posY));
		}
		
	}
	
	@Override
	public boolean stillValid(PlayerEntity player) {
		return this.inventory.stillValid(player);
	}
	
	public int getProgressScale() {
		int progress = fields.get(0);
		if (progress > 0) {
			return progress * 24 / 40;
		}
		return 0;
	}
	
	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            final int inventorySize = 7;
            final int playerInventoryEnd = inventorySize + 27;
            final int playerHotbarEnd = playerInventoryEnd + 9;

            if (index == 1) {
                if (!this.moveItemStackTo(itemstack1, inventorySize, playerHotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 0) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, inventorySize, playerHotbarEnd, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
	}

}