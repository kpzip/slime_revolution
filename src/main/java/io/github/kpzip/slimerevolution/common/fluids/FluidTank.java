package io.github.kpzip.slimerevolution.common.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;

public class FluidTank {
	
	private Fluid fluidType = Fluids.EMPTY;
	private int ammount = 0;
	
	
	
	
	public void update() {
		if (ammount == 0) {
			fluidType = Fluids.EMPTY;
		}
	}
	
	public int getAmmount() {
		return ammount;
	}
	
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
	
	public Fluid getFluidType() {
		return fluidType;
	}
	
	public void setFluidType(Fluid fluidType) {
		this.fluidType = fluidType;
	}
	

}
