package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.ModVars;
import io.github.kpzip.slimerevolution.common.blocks.BlockHorizontal;
import io.github.kpzip.slimerevolution.common.blocks.BlockIndustrialBrewerController;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
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
	public static final RegistryObject<BlockIndustrialBrewerController> INDUSTRIAL_BREWER_CONTROLLER = BLOCKS.register("industrial_brewer_controller",
			() -> new BlockIndustrialBrewerController(AbstractBlock.Properties
						.of(Material.METAL, MaterialColor.COLOR_GRAY)
						.harvestLevel(2)
						.harvestTool(ToolType.PICKAXE)
						.sound(SoundType.METAL)
						.isRedstoneConductor((state, reader, pos) -> true)
					));
}
