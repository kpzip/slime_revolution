package io.github.kpzip.slimerevolution.common.recipe;

import io.github.kpzip.slimerevolution.common.tileentities.TileEntityIndustrialBrewerController;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class IndustrialBrewingRecipe implements IRecipe<TileEntityIndustrialBrewerController> {
	
	private Fluid inputType;
	private Fluid outputType;
	private float inputAmmount = 1.0f; //ammount in buckets, 1.0f = 1 bucket, 1 bucket = 3 bottles\
	private float outputAmmount = 1.0f;
	
	private Ingredient starterItem; //nether wart or nothing
	private Ingredient mainIngredient; //the main ingredient for a potion
	private Ingredient inverter; //fermented spider eye or nothing
	private Ingredient splashModifier; //gunpowder or nothing
	private Ingredient extenionModifier; //glowstone or redstone or nothing
	private Ingredient lingeringModifier; //dragons breath or nothing
	
	private ItemStack residue; //Residual item to be created for brewing this potion 
	
	private int duration = 40; //in ticks. 20 ticks = 1 second
	
	private int powerUsage = 1000; //in RF
	private int rfpt; //RF used per tick
	
	
	
	private ResourceLocation id;
	
	public IndustrialBrewingRecipe(Fluid inputType, Fluid outputType, float inputAmmount, float outputAmmount,
			Ingredient starterItem, Ingredient mainIngredient, Ingredient inverter, Ingredient splashModifier,
			Ingredient extenionModifier, Ingredient lingeringModifier, ItemStack residue, int duration, int powerUsage, ResourceLocation id) {
		
		this.inputType = inputType;
		this.outputType = outputType;
		this.inputAmmount = inputAmmount;
		this.outputAmmount = outputAmmount;
		
		this.starterItem = starterItem;
		this.mainIngredient = mainIngredient;
		this.inverter = inverter;
		this.splashModifier = splashModifier;
		this.extenionModifier = extenionModifier;
		this.lingeringModifier = lingeringModifier;
		
		this.residue = residue;
		
		this.duration = duration;
		this.powerUsage = powerUsage;
		recalcRfpt();
		
		this.id = id;
	}

	public Fluid getInputType() {
		return inputType;
	}

	public Fluid getOutputType() {
		return outputType;
	}

	public float getInputAmmount() {
		return inputAmmount;
	}

	public float getOutputAmmount() {
		return outputAmmount;
	}

	public Ingredient getStarterItem() {
		return starterItem;
	}

	public Ingredient getMainIngredient() {
		return mainIngredient;
	}

	public Ingredient getInverter() {
		return inverter;
	}

	public Ingredient getSplashModifier() {
		return splashModifier;
	}

	public Ingredient getExtenionModifier() {
		return extenionModifier;
	}

	public Ingredient getLingeringModifier() {
		return lingeringModifier;
	}

	public int getDuration() {
		return duration;
	}

	public int getPowerUsage() {
		return powerUsage;
	}

	public int getRfpt() {
		return rfpt;
	}
	
	private void recalcRfpt() {
		this.rfpt = powerUsage/duration;
	}

	@Override
	public boolean matches(TileEntityIndustrialBrewerController p_77569_1_, World p_77569_2_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack assemble(TileEntityIndustrialBrewerController p_77572_1_) {
		return this.residue.copy();
	}

	@Override
	public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return this.getResidue();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRecipeType<?> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemStack getResidue() {
		return residue;
	}

}
