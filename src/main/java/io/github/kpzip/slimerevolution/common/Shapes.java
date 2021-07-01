package io.github.kpzip.slimerevolution.common;

import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.block.Block;

public class Shapes {
	
	public static final VoxelShape SHAPE_BREWING_COLUMN = VoxelShapes.join(Block.box(1, 0, 1, 15, 2, 15), Block.box(7, 2, 7, 9, 16, 9), IBooleanFunction.OR);

}
