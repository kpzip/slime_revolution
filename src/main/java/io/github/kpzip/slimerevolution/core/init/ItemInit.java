package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.ModVars;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModVars.MOD_ID);
	
	public static final RegistryObject SLIME_BASED_RUBBER = ITEMS.register("slime_based_rubber", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

}
