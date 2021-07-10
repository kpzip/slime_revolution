package io.github.kpzip.slimerevolution.common.container.slot;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public enum SlotType {
	
	NETHER_WART_SLOT(Items.NETHER_WART),
	MAIN_INGREDIENT_SLOT(Items.SPIDER_EYE, Items.RABBIT_FOOT, Items.MAGMA_CREAM, Items.BLAZE_POWDER, Items.GHAST_TEAR, Items.GLISTERING_MELON_SLICE, Items.PUFFERFISH, Items.GOLDEN_CARROT, Items.SUGAR, Items.TURTLE_HELMET, Items.PHANTOM_MEMBRANE, Items.SLIME_BALL),
	INVERTER_SLOT(Items.FERMENTED_SPIDER_EYE),
	SPLASH_SLOT(Items.GUNPOWDER),
	MODIFIER_SLOT(Items.REDSTONE),
	LINGERING_SLOT(Items.DRAGON_BREATH);
	
	private List<Item> valid_items = new ArrayList<Item>();
	
	private SlotType(Item... items) {
		for (Item item : items) {
			valid_items.add(item);
		}
	}
	
	public boolean isItemValid(ItemStack stack) {
		for (int i = 0;  i < valid_items.size(); i++) {
			if (valid_items.get(i).equals(stack.getItem())) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
