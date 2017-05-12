package logistics.facility;

import logistics.inventory.Inventory;
import logistics.schedule.Schedule;

public interface Facility extends Comparable<Facility>{

	public String getFacilityName();
	public int getProcessingRate();
	public double getProcessingCost();
	public Inventory getInventory();
	public Schedule getSchedule();
}
