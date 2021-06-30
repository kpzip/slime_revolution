package io.github.kpzip.slimerevolution;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.kpzip.slimerevolution.core.init.BlockInit;
import io.github.kpzip.slimerevolution.core.init.ItemInit;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModVars.MOD_ID)
@Mod.EventBusSubscriber(modid = ModVars.MOD_ID, bus = Bus.MOD)
public class SlimeRevolution
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup SLIME_REVOLUTION_GROUP = new SlimeRevolutionGroup("slimerevolutiontab");

    public SlimeRevolution() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        
        MinecraftForge.EVENT_BUS.register(this);
        
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
    	BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(SlimeRevolution.SLIME_REVOLUTION_GROUP))
					.setRegistryName(block.getRegistryName()));
		});
    }
    
    public static class SlimeRevolutionGroup extends ItemGroup {

		public SlimeRevolutionGroup(String label) {
			super(label);
		}

		@Override
		public ItemStack makeIcon() {
			return ItemInit.SLIMEY_IRON.get().getDefaultInstance();
		}
    }
}
