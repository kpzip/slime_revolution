package io.github.kpzip.slimerevolution.common.energy;

public interface IRealisticEnergyHandler {
	
	public int getStoredEnergy();
	
	public void sendEnergyToNeighbors();
	
	public boolean useEnergy(int ammount, EnergyAction action);
	
	public boolean addEnergy(int ammount, EnergyAction action);
	
	public EnergyHandlerType getEnergyType();
	
	
	
}
