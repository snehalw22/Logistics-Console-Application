package logistics.facility;

import logistics.exceptions.InvalidArgumentException;
import logistics.inventory.Inventory;
import logistics.schedule.Schedule;

public class FacilityImpl implements Facility {

	private String facilityName;
	private int processingRate;
	private double processingCost;
	private Inventory inventory;
	private Schedule schedule;

	@Override
	public String getFacilityName() {
		return facilityName;
	}

	@Override
	public int getProcessingRate() {
		return processingRate;
	}

	@Override
	public double getProcessingCost() {
		return processingCost;
	}

	public Inventory getInventory() {
		
		return inventory;
	}

	public Schedule getSchedule() {
		
	
		return schedule;
	}

	private void setFacilityName(String facilityName) throws InvalidArgumentException {
		if (facilityName == null || facilityName.isEmpty())
			throw new InvalidArgumentException(
					"Invalid (empty or null) value passed to FacilityImpl 'setFacilityName'");
		else
			this.facilityName = facilityName;
	}

	private void setProcessingRate(int processingRate) throws InvalidArgumentException {
		if (processingRate <= 0)
			throw new InvalidArgumentException(
					"Invalid processingRate (zero or less) value is passed to FacilityImpl method 'setProcessingRate' ");
		this.processingRate = processingRate;

	}

	private void setProcessingCost(double processingCost) throws InvalidArgumentException {
		if (processingCost <= 0.0)
			throw new InvalidArgumentException(
					"Invalid processingCost (zero or less) value is passed to FacilityImpl method 'setProcessingCost' ");
		this.processingCost = processingCost;

	}

	private void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	private void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public FacilityImpl(String facilityName, int processingRate, double processingCost,
			Inventory inventoryList,Schedule schedule) throws InvalidArgumentException {
		setFacilityName(facilityName);
		setProcessingCost(processingCost);
		setProcessingRate(processingRate);
		setInventory(inventoryList);
		
			/*if(facilityName.equals("Denver, CO"))
			{
				System.out.println("Befor adding "+schedule.toString());
				schedule.addToSchedule(3, 0);
				schedule.addToSchedule(4, 2);
				System.out.println("After adding "+schedule.toString());
			}*/
			
		
		setSchedule(schedule);
	}

	private FacilityImpl() {
	}
	@Override
	public int compareTo(Facility facility) {
		return this.getFacilityName().compareTo(facility.getFacilityName());

	}

	@Override
	public String toString() {
		return "FacilityImpl [facilityName=" + facilityName + ", processingRate=" + processingRate + ", processingCost="
				+ processingCost + ", inventoryL=" + inventory + ", schedule=" + schedule + "]";
	}
	
}
