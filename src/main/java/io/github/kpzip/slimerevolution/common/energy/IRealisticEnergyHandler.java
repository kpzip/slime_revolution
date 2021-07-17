package io.github.kpzip.slimerevolution.common.energy;

public interface IRealisticEnergyHandler {
	
	public int getAmps();
	
	public int getVolts();
	
	public int getFrequency();
	
	public void addAmps(int ammount);
	
	public boolean setVolts(int volts);
	
	public void removeAmps(int ammount);
	
	
	
}
