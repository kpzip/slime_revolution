package io.github.kpzip.slimerevolution;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.kpzip.slimerevolution.core.init.ItemInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModVars.MOD_ID)
public class SlimeRevolution
{
    public static final Logger LOGGER = LogManager.getLogger();

    public SlimeRevolution() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemInit.ITEMS.register(bus);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

}
