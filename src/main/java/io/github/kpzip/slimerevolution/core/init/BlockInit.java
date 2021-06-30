package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.ModVars;
import io.github.kpzip.slimerevolution.common.blocks.BlockHorizontal;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModVars.MOD_ID);
	
	public static final RegistryObject<BlockHorizontal> SLIME_BASED_RUBBER_BLOCK = BLOCKS.register("slime_based_rubber_block",
			() -> new BlockHorizontal(AbstractBlock.Properties
					.of(Material.CAKE, MaterialColor.COLOR_BROWN)
					.harvestLevel(-1)
					.instabreak()
					.sound(SoundType.SLIME_BLOCK)
					.isRedstoneConductor((state, reader, pos) -> false)
					));
}