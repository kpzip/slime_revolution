package io.github.kpzip.slimerevolution.common.tileentities;

import javax.annotation.Nullable;

import io.github.kpzip.slimerevolution.common.energy.EnergyAction;
import io.github.kpzip.slimerevolution.common.energy.EnergyHandlerType;
import io.github.kpzip.slimerevolution.common.energy.IEnergyTransmitter;
import io.github.kpzip.slimerevolution.common.energy.ISlimeEnergyHandler;
import io.github.kpzip.slimerevolution.core.init.TileEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnergyCable extends TileEntity implements IEnergyTransmitter, ITickable, ISlimeEnergyHandler {
	
	public static int MAX_ENERGY = 100000;
	public static int TRANSFER_RATE = 1000;
	public int energy = 0;
	
	public TileEntityEnergyCable() {
		super(TileEntityInit.ENERGY_CABLE_TILE_ENTITY_TYPE.get());
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		
		nbt.putInt("Energy", energy);
		
		return nbt;
	}
	
	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		
		energy = nbt.getInt("Energy");
		
	}
	
	@Override
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT compound = this.getUpdateTag();
		
		compound.putInt("Eneregy", energy);
		
		return new SUpdateTileEntityPacket(this.worldPosition, 1, compound);
	}

	@Override
	public void sendEnergyToNeighbors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean useEnergy(int ammount, EnergyAction action) {
		if (action == EnergyAction.SIMULATE || ammount > energy) {
			return ammount <= energy;
		}
		energy -= ammount;
		return true;
	}

	@Override
	public boolean addEnergy(int ammount, EnergyAction action) {
		if (action == EnergyAction.SIMULATE || ammount > MAX_ENERGY - energy) {
			return ammount <= MAX_ENERGY - energy;
		}
		energy += ammount;
		return true;
	}

	@Override
	public EnergyHandlerType getEnergyType() {
		return EnergyHandlerType.CABLE;
	}

	@Override
	public int getStoredEnergy() {
		return energy;
	}

	@Override
	public void tick() {
		sendEnergyToNeighbors();
	}

	@Override
	public int getCapacity() {
		return MAX_ENERGY;
	}

	@Override
	public int getMaximumTransferRate() {
		return TRANSFER_RATE;
	}

	

}
