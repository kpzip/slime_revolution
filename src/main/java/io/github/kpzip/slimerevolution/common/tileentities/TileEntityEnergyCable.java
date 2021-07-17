package io.github.kpzip.slimerevolution.common.tileentities;

import io.github.kpzip.slimerevolution.common.energy.IRealisticEnergyHandler;
import io.github.kpzip.slimerevolution.core.init.TileEntityInit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnergyCable extends TileEntity implements IRealisticEnergyHandler {
	
	private int amps = 0;
	private int volts = 0;
	private int frequency = 0;
	
	public TileEntityEnergyCable() {
		super(TileEntityInit.ENERGY_CABLE_TILE_ENTITY_TYPE.get());
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		
		nbt.putInt("amps", amps);
		nbt.putInt("volts", volts);
		nbt.putInt("frequency", frequency);
		return nbt;
	}

	@Override
	public int getAmps() {
		return amps;
	}

	@Override
	public int getVolts() {
		return volts;
	}

	@Override
	public int getFrequency() {
		return frequency;
	}

	@Override
	public void addAmps(int ammount) {
		this.setChanged();
		this.amps += ammount;
	}

	@Override
	public boolean setVolts(int volts) {
		this.setChanged();
		this.volts = volts;
		return true;
	}

	@Override
	public void removeAmps(int ammount) {
		this.setChanged();
		this.amps -= ammount;
	}

}
