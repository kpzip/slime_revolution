package io.github.kpzip.slimerevolution.core.util;

import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.registries.ForgeRegistries;

public final class NBTHelper {
	
	public static CompoundNBT writeFluidTankToNBTWithPrefix(CompoundNBT nbt, FluidTank tank, String prefix) {
		FluidStack stack = tank.getFluid();
		nbt.putString(prefix + "FluidName", stack.getFluid().getRegistryName().toString());
        nbt.putInt(prefix + "Amount", stack.getAmount());

        if (stack.getTag() != null)
        {
            nbt.put(prefix + "Tag", stack.getTag());
        }
        return nbt;
	}

	public static FluidTank loadFluidTankFromNBTWithPrefix(CompoundNBT nbt, FluidTank tank, String prefix) {
		if (nbt == null)
        {
			tank.setFluid(FluidStack.EMPTY);
			return tank;
        }
        if (!nbt.contains(prefix + "FluidName", Constants.NBT.TAG_STRING))
        {
        	tank.setFluid(FluidStack.EMPTY);
			return tank;
        }

        ResourceLocation fluidName = new ResourceLocation(nbt.getString(prefix + "FluidName"));
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidName);
        if (fluid == null)
        {
        	tank.setFluid(FluidStack.EMPTY);
			return tank;
        }
        FluidStack stack = new FluidStack(fluid, nbt.getInt(prefix + "Amount"));

        if (nbt.contains(prefix + "Tag", Constants.NBT.TAG_COMPOUND))
        {
            stack.setTag(nbt.getCompound(prefix + "Tag"));
        }
        tank.setFluid(stack);
        return tank;
	}
}
