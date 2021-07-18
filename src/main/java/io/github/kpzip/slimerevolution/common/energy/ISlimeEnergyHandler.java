package io.github.kpzip.slimerevolution.common.energy;

public interface ISlimeEnergyHandler {
	
	public int getStoredEnergy();
	
	public boolean useEnergy(int ammount, EnergyAction action);
	
	public boolean addEnergy(int ammount, EnergyAction action);
	
	public EnergyHandlerType getEnergyType();
	
	public int getCapacity();
	
	public int getMaximumTransferRate();
	
	
	
}
