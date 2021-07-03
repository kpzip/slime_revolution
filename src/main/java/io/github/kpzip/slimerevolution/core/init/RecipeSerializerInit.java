package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.ModVars;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {
	
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ModVars.MOD_ID);

}
