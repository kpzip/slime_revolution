package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.common.tileentities.TileEntityIndustrialBrewerController;
import io.github.kpzip.slimerevolution.core.ModVars;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModVars.MOD_ID);
	
	public static final RegistryObject<TileEntityType<TileEntityIndustrialBrewerController>> INDUSTRIAL_BREWER_CONTROLLER_TILE_ENTITY_TYPE = TILE_ENTITIES.register(
			"industrial_brewer_controller_tile_entity_type", () -> 
			{ return TileEntityType.Builder.of(
					TileEntityIndustrialBrewerController::new, 
					BlockInit.INDUSTRIAL_BREWER_CONTROLLER.get()).build(null); });
	

}
