package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.SlimeRevolution;
import io.github.kpzip.slimerevolution.core.ModVars;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModVars.MOD_ID);

	public static final RegistryObject<Item> SLIME_BASED_RUBBER = ITEMS.register("slime_based_rubber",
			() -> new Item(new Item.Properties()
						.tab(SlimeRevolution.SLIME_REVOLUTION_GROUP_ITEMS)
					));
	public static final RegistryObject<Item> SLIMEY_IRON = ITEMS.register("slimey_iron",
			() -> new Item(new Item.Properties()
						.tab(SlimeRevolution.SLIME_REVOLUTION_GROUP_ITEMS)
					));

}
