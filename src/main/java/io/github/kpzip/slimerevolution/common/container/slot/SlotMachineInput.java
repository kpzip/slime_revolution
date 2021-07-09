package io.github.kpzip.slimerevolution.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SlotMachineInput extends Slot {
	
	public SlotMachineInput(IInventory inv, int index, int x, int y) {
		super(inv, index, x, y);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem().equals(Items.NETHER_WART);
	}

}
