package logistics.factory;

import java.util.ArrayList;

import logistics.exceptions.InvalidArgumentException;
import logistics.facility.Facility;
import logistics.facility.FacilityImpl;
import logistics.inventory.Inventory;
import logistics.schedule.Schedule;

public class FacilityFactory {

	private FacilityFactory() {
	}

	public static Facility createFacility(String facilityType,String facilityName, int processingRate, double processingCost,
			Inventory inventoryList,Schedule scheduleList) throws InvalidArgumentException {

		if (facilityType.equalsIgnoreCase("FacilityImpl"))
			return new FacilityImpl(facilityName, processingRate, processingCost, inventoryList,scheduleList);
		else
			return null;

	}

}
