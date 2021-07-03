package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.ModVars;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModVars.MOD_ID);

}
