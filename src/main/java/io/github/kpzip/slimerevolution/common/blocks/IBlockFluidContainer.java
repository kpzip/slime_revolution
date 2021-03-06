package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockFluidContainer {
	
	public ActionResultType addFluidFromBucket(BlockState state, World world, BlockPos pos, PlayerEntity player, BucketItem inHand, TileEntity te, Hand hand);

}
