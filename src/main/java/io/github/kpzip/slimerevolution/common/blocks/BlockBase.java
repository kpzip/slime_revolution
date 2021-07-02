package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public class BlockBase extends Block {
	
	protected ItemGroup category = ItemGroup.TAB_MISC;
	protected boolean hasItem = true;

	public BlockBase(Properties properties) {
		super(properties);
	}

	public ItemGroup getTab() {
		return category;
	}

	public BlockBase tab(ItemGroup category) {
		this.category = category;
		return this;
	}
	
	public BlockBase setHasItem(boolean b) {
		this.hasItem = b;
		return this;
	}
	
	

}
