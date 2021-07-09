package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.common.recipe.IndustrialBrewingRecipe;
import io.github.kpzip.slimerevolution.core.ModVars;
import net.minecraft.item.crafting.IRecipeType;

public class RecipeTypeInit {
	
	public static final IRecipeType<IndustrialBrewingRecipe> INDUSTRIAL_BREWING = IRecipeType.register(ModVars.MOD_ID + "industrial_brewing");

}
