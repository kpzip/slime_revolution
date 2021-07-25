package io.github.kpzip.slimerevolution.core.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public final class BlockAttributeHelper {
	
	private BlockAttributeHelper() {
        throw new IllegalAccessError("Class only uses static methods");
    }
	
	public static boolean always(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> type) {
		return true;
	}
	
	public static boolean always(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
	
	public static boolean never(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> type) {
		return false;
	}
	
	public static boolean never(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}

}
