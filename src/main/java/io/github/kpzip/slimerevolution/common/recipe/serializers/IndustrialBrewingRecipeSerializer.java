package io.github.kpzip.slimerevolution.common.recipe.serializers;

import com.google.gson.JsonObject;

import io.github.kpzip.slimerevolution.common.recipe.IndustrialBrewingRecipe;
import net.minecraft.item.ItemStack;
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
	public IndustrialBrewingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
		
		ResourceLocation fluidIdIn = new ResourceLocation(JSONUtils.getAsString(json, "in_fluid"));
		int fluidCountIn = JSONUtils.getAsInt(json, "in_fluid_count", 1000);
		
		ResourceLocation fluidIdOut = new ResourceLocation(JSONUtils.getAsString(json, "out_fluid"));
		int fluidCountOut = JSONUtils.getAsInt(json, "out_fluid_count", 1000);
		
		ResourceLocation residueId = new ResourceLocation(JSONUtils.getAsString(json, "residue"));
		int residueCount = JSONUtils.getAsInt(json, "residue_count", 1);
		
		
		
		FluidStack inFluid = new FluidStack(ForgeRegistries.FLUIDS.getValue(fluidIdIn), fluidCountIn);
		FluidStack outFluid = new FluidStack(ForgeRegistries.FLUIDS.getValue(fluidIdOut), fluidCountOut);
		
		Ingredient starterItem = Ingredient.fromJson(json.get("starter"));
		Ingredient mainIngredient = Ingredient.fromJson(json.get("main"));
		Ingredient inverter = Ingredient.fromJson(json.get("inverter"));
		Ingredient splashModifier = Ingredient.fromJson(json.get("splash"));
		Ingredient extensionModifier = Ingredient.fromJson(json.get("extension"));
		Ingredient lingeringModifier = Ingredient.fromJson(json.get("lingering"));
		
		ItemStack residue = new ItemStack(ForgeRegistries.ITEMS.getValue(residueId), residueCount);
		
		int duration = JSONUtils.getAsInt(json, "duration");
		int powerUsage = JSONUtils.getAsInt(json, "power_usage");
		
		return new IndustrialBrewingRecipe(inFluid, outFluid, starterItem, mainIngredient, inverter, splashModifier, extensionModifier, lingeringModifier, residue, duration, powerUsage, recipeId);
	}

	@Override
	public IndustrialBrewingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
		
		FluidStack inFluid = buffer.readFluidStack();
		FluidStack outFluid = buffer.readFluidStack();
		
		Ingredient starterItem = Ingredient.fromNetwork(buffer);
		Ingredient mainIngredient = Ingredient.fromNetwork(buffer);
		Ingredient inverter = Ingredient.fromNetwork(buffer);
		Ingredient splashModifier = Ingredient.fromNetwork(buffer);
		Ingredient extensionModifier = Ingredient.fromNetwork(buffer);
		Ingredient lingeringModifier = Ingredient.fromNetwork(buffer);
		
		ItemStack residue = buffer.readItem();
		
		int duration = buffer.readInt();
		int powerUsage = buffer.readInt();
		
		return new IndustrialBrewingRecipe(inFluid, outFluid, starterItem, mainIngredient, inverter, splashModifier, extensionModifier, lingeringModifier, residue, duration, powerUsage, recipeId);
	}

	@Override
	public void toNetwork(PacketBuffer buffer, IndustrialBrewingRecipe recipe) {
		buffer.writeFluidStack(recipe.getInput());
		buffer.writeFluidStack(recipe.getOutput());
		
		recipe.getStarterItem().toNetwork(buffer);
		recipe.getMainIngredient().toNetwork(buffer);
		recipe.getInverter().toNetwork(buffer);
		recipe.getSplashModifier().toNetwork(buffer);
		recipe.getExtentionModifier().toNetwork(buffer);
		recipe.getLingeringModifier().toNetwork(buffer);
		
		buffer.writeItem(recipe.getResidue());
		
		buffer.writeVarInt(recipe.getDuration());
		buffer.writeVarInt(recipe.getPowerUsage());
		
	}

}
