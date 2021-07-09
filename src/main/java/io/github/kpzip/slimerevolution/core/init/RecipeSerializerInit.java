package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.common.recipe.IndustrialBrewingRecipe;
import io.github.kpzip.slimerevolution.common.recipe.serializers.IndustrialBrewingRecipeSerializer;
import io.github.kpzip.slimerevolution.core.ModVars;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {
	
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ModVars.MOD_ID);
	
	public static final RegistryObject<IRecipeSerializer<IndustrialBrewingRecipe>> INDUSTRIAL_BREWING_SERIALIZER = RECIPE_SERIALIZERS.register(
			"industrial_brewing_serializer", IndustrialBrewingRecipeSerializer::new);

}
