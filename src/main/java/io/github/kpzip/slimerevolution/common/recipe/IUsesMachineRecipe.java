package io.github.kpzip.slimerevolution.common.recipe;

import net.minecraft.item.crafting.IRecipe;

public interface IUsesMachineRecipe<C extends IRecipe<?>> extends IUsesRecipe<C> {
	
	public void doWork(C recipe);

}
