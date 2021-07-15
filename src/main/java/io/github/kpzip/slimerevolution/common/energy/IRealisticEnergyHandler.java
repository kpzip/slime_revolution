package io.github.kpzip.slimerevolution.common.energy;

public interface IRealisticEnergyHandler {
	
	public int getAmps();
	
	public int getVolts();
	
	public int getFrequency();
	
	public void addAmps();
	
	public boolean setVolts();
	
	public void removeAmps();
	
	
	
}
