package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.ModVars;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerInit {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ModVars.MOD_ID);
	
	

}
