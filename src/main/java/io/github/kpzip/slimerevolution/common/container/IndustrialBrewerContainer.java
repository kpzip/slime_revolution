package io.github.kpzip.slimerevolution.common.container;

import io.github.kpzip.slimerevolution.core.init.ContainerInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IIntArray;

public class IndustrialBrewerContainer extends Container {
	
	private final IInventory inventory;
	private IIntArray fields;
	
	public IndustrialBrewerContainer(int id, PlayerInventory playerInv, IInventory inventory, IIntArray fields) {
		super(ContainerInit.INDUSTRIAL_BREWER_CONTAINER, id);
		this.inventory = inventory;
		this.fields = fields;
	}
	
	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		// TODO Auto-generated method stub
		return false;
	}

}
