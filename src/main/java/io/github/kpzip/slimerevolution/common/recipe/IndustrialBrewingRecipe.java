package io.github.kpzip.slimerevolution.common.recipe;

import io.github.kpzip.slimerevolution.common.tileentities.TileEntityIndustrialBrewerController;
import io.github.kpzip.slimerevolution.core.init.RecipeSerializerInit;
import io.github.kpzip.slimerevolution.core.init.RecipeTypeInit;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class IndustrialBrewingRecipe implements IRecipe<TileEntityIndustrialBrewerController> {
	
	
	
	private FluidStack input; //required fluid ammount to start crafting this recipe
	private FluidStack output; //fluid ammount produced by this recipe
	
	private Ingredient starterItem; //nether wart or nothing
	private Ingredient mainIngredient; //the main ingredient for a potion
	private Ingredient inverter; //fermented spider eye or nothing
	private Ingredient splashModifier; //gunpowder or nothing
	private Ingredient extensionModifier; //glowstone or redstone or nothing
	private Ingredient lingeringModifier; //dragons breath or nothing
	
	private ItemStack residue; //Residual item to be created for brewing this potion
	
	private int duration = 40; //in ticks. 20 ticks = 1 second
	
	private int powerUsage = 1000; //in RF
	private int rfpt; //RF used per tick
	
	
	
	private ResourceLocation id;
	
	public IndustrialBrewingRecipe(FluidStack input, FluidStack output,
			Ingredient starterItem, Ingredient mainIngredient, Ingredient inverter, Ingredient splashModifier,
			Ingredient extensionModifier, Ingredient lingeringModifier, ItemStack residue, int duration, int powerUsage, ResourceLocation id) {
		
		this.input = input;
		this.output = output;
		
		this.starterItem = starterItem;
		this.mainIngredient = mainIngredient;
		this.inverter = inverter;
		this.splashModifier = splashModifier;
		this.extensionModifier = extensionModifier;
		this.lingeringModifier = lingeringModifier;
		
		this.residue = residue;
		
		this.duration = duration;
		this.powerUsage = powerUsage;
		recalcRfpt();
		
		this.id = id;
	}


	public FluidStack getInput() {
		return this.input;
	}
	
	public FluidStack getOutput() {
		return this.output;
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

	public Ingredient getExtensionModifier() {
		return extensionModifier;
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

	public ItemStack getResidue() {
		return residue;
	}

	private void recalcRfpt() {
		this.rfpt = powerUsage/duration;
	}

	@Override
	public boolean matches(TileEntityIndustrialBrewerController te, World world) {
		return starterItem.test(te.getItem(TileEntityIndustrialBrewerController.NETHER_WART_SLOT)) &&
				mainIngredient.test(te.getItem(TileEntityIndustrialBrewerController.MAIN_INGREDIENT)) &&
				inverter.test(te.getItem(TileEntityIndustrialBrewerController.INVERTER_SLOT)) &&
				splashModifier.test(te.getItem(TileEntityIndustrialBrewerController.SPLASH_SLOT)) &&
				extensionModifier.test(te.getItem(TileEntityIndustrialBrewerController.MODIFIER_SLOT)) &&
				lingeringModifier.test(te.getItem(TileEntityIndustrialBrewerController.LINGERING_SLOT)) &&
				te.getFluidInTank(0).isFluidEqual(input) && te.getFluidInTank(0).getAmount() >= input.getAmount() &&
				te.getFluidInTank(1).isFluidEqual(output) && te.getFluidInTank(1).getAmount() >= output.getAmount();
	}

	@Override
	public ItemStack assemble(TileEntityIndustrialBrewerController te) {
		return this.residue.copy();
	}

	@Override
	public boolean canCraftInDimensions(int num1, int num2) {
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
		return RecipeSerializerInit.INDUSTRIAL_BREWING_SERIALIZER.get();
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeTypeInit.INDUSTRIAL_BREWING;
	}

}
