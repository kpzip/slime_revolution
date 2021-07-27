package io.github.kpzip.slimerevolution.common;

import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.block.Block;

public class Shapes {
	
	public static final VoxelShape SHAPE_BREWING_COLUMN = VoxelShapes.join(Block.box(1, 0, 1, 15, 2, 15), Block.box(7, 2, 7, 9, 16, 9), IBooleanFunction.OR);
	
	public static final VoxelShape SHAPE_CABLE_CENTER = Block.box(7, 7, 7, 9, 9, 9);
	
	
	
	public static final VoxelShape SHAPE_CABLE_UP = Block.box(7, 9, 7, 9, 16, 9);
	public static final VoxelShape SHAPE_CABLE_DOWN = Block.box(7, 0, 7, 9, 9, 9);
	public static final VoxelShape SHAPE_CABLE_NORTH = Block.box(7, 7, 0, 9, 9, 7);
	public static final VoxelShape SHAPE_CABLE_EAST = Block.box(9, 7, 7, 16, 9, 9);	
	public static final VoxelShape SHAPE_CABLE_SOUTH = Block.box(7, 7, 9, 9, 9, 16);
	public static final VoxelShape SHAPE_CABLE_WEST = Block.box(0, 7, 7, 7, 9, 9);
	
	public static VoxelShape getShapeForCable(boolean HasNorth, boolean HasEast, boolean HasSouth, boolean HasWest, boolean HasUp, boolean HasDown) {
		VoxelShape shape = SHAPE_CABLE_CENTER;
		if (HasNorth) {
			shape = VoxelShapes.join(shape, SHAPE_CABLE_NORTH, IBooleanFunction.OR);
		}
		if (HasEast) {
			shape = VoxelShapes.join(shape, SHAPE_CABLE_EAST, IBooleanFunction.OR);
		}
		if (HasSouth) {
			shape = VoxelShapes.join(shape, SHAPE_CABLE_SOUTH, IBooleanFunction.OR);
		}
		if (HasWest) {
			shape = VoxelShapes.join(shape, SHAPE_CABLE_WEST, IBooleanFunction.OR);
		}
		if (HasUp) {
			shape = VoxelShapes.join(shape, SHAPE_CABLE_UP, IBooleanFunction.OR);
		}
		if (HasDown) {
			shape = VoxelShapes.join(shape, SHAPE_CABLE_DOWN, IBooleanFunction.OR);
		}
		return shape;
	}
	
}
