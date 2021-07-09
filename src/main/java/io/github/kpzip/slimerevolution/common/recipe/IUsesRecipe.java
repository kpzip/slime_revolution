package io.github.kpzip.slimerevolution.common.recipe;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public interface IUsesRecipe<T extends IRecipe<?>> {
	
	@Nullable
	public T getRecipe();
	
	public ItemStack getWorkOutput(T recipe);
	
	public void stopWork();
	
	public void finishWork(T recipe, ItemStack current, ItemStack output);

}
