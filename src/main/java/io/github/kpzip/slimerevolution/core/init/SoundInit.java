package io.github.kpzip.slimerevolution.core.init;

import io.github.kpzip.slimerevolution.ModVars;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModVars.MOD_ID);
}
