package io.github.kpzip.slimerevolution.common.recipe.serializers;

import com.google.gson.JsonObject;

import io.github.kpzip.slimerevolution.common.recipe.IndustrialBrewingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class IndustrialBrewingRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<IndustrialBrewingRecipe> {

	@Override
	public IndustrialBrewingRecipe fromJson(ResourceLocation id, JsonObject json) {
		
		ResourceLocation fluidIdIn = new ResourceLocation(JSONUtils.getAsString(json, "influid"));
		int fluidCountIn = JSONUtils.getAsInt(json, "influidcount");
		ResourceLocation fluidIdOut = new ResourceLocation(JSONUtils.getAsString(json, "outfluid"));
		int fluidCountOut = JSONUtils.getAsInt(json, "outfluidcount");
		
		FluidStack inFluid = new FluidStack(ForgeRegistries.FLUIDS.getValue(fluidIdIn), fluidCountIn);
		FluidStack outFluid = new FluidStack(ForgeRegistries.FLUIDS.getValue(fluidIdOut), fluidCountOut);
		Ingredient starterItem = Ingredient.fromJson(json.get("starter"));
		
		
		
		
		return null;
	}

	@Override
	public IndustrialBrewingRecipe fromNetwork(ResourceLocation p_199426_1_, PacketBuffer p_199426_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void toNetwork(PacketBuffer p_199427_1_, IndustrialBrewingRecipe p_199427_2_) {
		// TODO Auto-generated method stub
		
	}

}
