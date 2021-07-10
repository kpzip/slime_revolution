package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.common.container.IndustrialBrewerContainer;
import io.github.kpzip.slimerevolution.core.ModVars;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerInit {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ModVars.MOD_ID);
	
	
	
	public static final RegistryObject<ContainerType<IndustrialBrewerContainer>> INDUSTRIAL_BREWER_CONTAINER = CONTAINERS.register("industrial_brewer_container", () -> IForgeContainerType.create(IndustrialBrewerContainer::new));
	
	

}
