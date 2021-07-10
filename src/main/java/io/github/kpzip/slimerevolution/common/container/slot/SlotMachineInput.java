package io.github.kpzip.slimerevolution.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotMachineInput extends Slot {
	
	private SlotType type;
	
	public SlotMachineInput(IInventory inv, int index, int x, int y, SlotType type) {
		super(inv, index, x, y);
		this.type = type;
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return type.isItemValid(stack);
	}

}
