package io.github.kpzip.slimerevolution.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public class BlockBaseTab extends Block {
	
	protected ItemGroup category = ItemGroup.TAB_MISC;

	public BlockBaseTab(Properties properties) {
		super(properties);
	}

	public ItemGroup getTab() {
		return category;
	}

	public BlockBaseTab tab(ItemGroup category) {
		this.category = category;
		return this;
	}
	
	

}
