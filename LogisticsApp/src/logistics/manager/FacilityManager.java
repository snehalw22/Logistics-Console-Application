package logistics.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.dto.FacilityDto;
import logistics.dto.InventoryDto;
import logistics.dto.ScheduleDto;
import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.NoSuchDataFoundException;
import logistics.exceptions.UnexpectedNodeException;
import logistics.facility.Facility;
import logistics.facility.FacilityRecord;
import logistics.facility.FacilityRecordImpl;
import logistics.factory.FacilityLoaderFactory;
import logistics.properties.FacilityProperties;
import logistics.properties.MainProperties;
import logistics.utilities.Utility;

public final class FacilityManager {

	private static FacilityManager facilityManager;

	private ArrayList<Facility> facilityList;

	// constructor
	private FacilityManager() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException,
			SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException {
		FacilityProperties facilityProperties = FacilityProperties.getInstance();
		facilityList = FacilityLoaderFactory.getFacilityItems(facilityProperties.getFileType())
				.loadFacilities(facilityProperties.getFileLocation(), facilityProperties.getFacilityType());
	}

	public static FacilityManager getInstance() throws InvalidArgumentException, FileNotFoundException,
			ParserConfigurationException, SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException {
		if (facilityManager == null) {
			synchronized (FacilityManager.class) {
				if (facilityManager == null)
					facilityManager = new FacilityManager();
			}
		}
		return facilityManager;
	}

	public ArrayList<FacilityDto> getFacilityDtoList() {
		ArrayList<FacilityDto> facilityDtoList = new ArrayList<>();
		for (Facility facility : facilityList) {
			
			TreeMap<String, Integer> inventoryDtoList = new TreeMap<>();
			for (Map.Entry<String, Integer> entry : facility.getInventory().getInventoryList().entrySet()) {
				inventoryDtoList.put(entry.getKey(), entry.getValue());
			}
				HashMap<Integer, Integer> scheduleDtoList = new HashMap();
			
			for (Map.Entry<Integer, Integer> entry : facility.getSchedule().getSchedule().entrySet()) {
				scheduleDtoList.put(entry.getKey(), entry.getValue());
			}
			
			/*facilityDtoList.add(new FacilityDto(facility.getFacilityName(), facility.getProcessingRate(),
					facility.getProcessingCost(), inventoryDtoList, scheduleDtoList);*/
			
			facilityDtoList.add(new FacilityDto(facility.getFacilityName(), facility.getProcessingRate(), facility.getProcessingCost(), new InventoryDto(inventoryDtoList),new ScheduleDto(scheduleDtoList)));
		}
		return facilityDtoList;

	}

	public ArrayList<String> getDepletedInventory(String facilityName) {
		ArrayList<String> depletedInventoryList = new ArrayList<>();
		for (Facility facility : facilityList) {
			if (facility.getFacilityName().equals(facilityName)) {
				for (Map.Entry<String, Integer> entry : facility.getInventory().getInventoryList().entrySet()) {
					if (entry.getValue() == 0) {
						depletedInventoryList.add(entry.getKey());
					}
				}

				break;
			}
		}
		Collections.sort(depletedInventoryList);
		return depletedInventoryList;
	}

	public boolean ifExists(String facilityName) {
		for (Facility facility : facilityList) {
			if (facility.getFacilityName().equals(facilityName))
				return true;
		}

		return false;

	}
   
	public int getPtocessingRate(String FacilityName)
	{
		for (Facility facility:facilityList)
		{
			if (facility.getFacilityName().equalsIgnoreCase(FacilityName)){
				return facility.getProcessingRate();
			}
		}
		return 0;
	}
	
	public double getPtocessingCost(String FacilityName)
	{
		for (Facility facility:facilityList)
		{
			if (facility.getFacilityName().equalsIgnoreCase(FacilityName)){
				return facility.getProcessingCost();
			}
		}
		return 0;
	}
	
	public ArrayList<String> getCitiesWithItems(String itemId) {
		ArrayList<String> cities = new ArrayList<>();
		for (Facility facility : facilityList) {
			if (facility.getInventory().getInventoryList().containsKey(itemId)
					&& facility.getInventory().getInventoryList().get(itemId).intValue() > 0) {
				cities.add(facility.getFacilityName());
			}
		}

		return cities;
	}

	public ArrayList<FacilityRecord> getFacilityRecords(String destination, String itemId, int quantity, int orderTime)
			throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException,
			IOException, UnexpectedNodeException, NoSuchDataFoundException {

		ArrayList<String> cities = new ArrayList<>();
		ArrayList<String[]> spCities = new ArrayList<>();
		cities = FacilityManager.getInstance().getCitiesWithItems(itemId);
		cities.remove(destination);
		//System.out.println("item "+itemId+"  cities "+cities.toString());
		for (String s : cities) {
			spCities.add(new String[] { destination, s });
		}
		ArrayList<HashMap<ArrayList<String>, Double>> shortestPath = NetworkManager.getInstance()
				.calculateShortestPath(spCities);
		ArrayList<FacilityRecord> facilityRecords = new ArrayList<>();
		for (String city : cities) {
			for (Facility facility : facilityList) {
				if (facility.getFacilityName().equalsIgnoreCase(city)) {
					//System.out.println("City " + city + " :: processing rate " + facility.getProcessingRate()+ " :: processing cost " + facility.getProcessingCost()+ " :: items " + facility.getInventory().getInventoryList().get(itemId).intValue());
					int requiredQuantity,facilityItemQuantity;
					int orderTimeNew = orderTime;
					int days = orderTime - 1;
					if (quantity - facility.getInventory().getInventoryList().get(itemId).intValue() < 0) {
						requiredQuantity = quantity;
					} else {
						requiredQuantity = facility.getInventory().getInventoryList().get(itemId).intValue();
					}
					facilityItemQuantity=requiredQuantity;
					while (requiredQuantity > 0) {
						String itemInSchedule = String
								.valueOf(facility.getSchedule().getSchedule().get(orderTimeNew));
						if (!itemInSchedule.equalsIgnoreCase("null")) {
							if (Integer.parseInt(itemInSchedule) > 0) {
								requiredQuantity = requiredQuantity - Integer.parseInt(itemInSchedule);
							}
						} else {
							requiredQuantity = requiredQuantity - facility.getProcessingRate();
						}
						days = days + 1;
						orderTimeNew = orderTimeNew + 1;
					}
					for (int i = 0; i < shortestPath.size(); i++) {
						for (Entry<ArrayList<String>, Double> entry : shortestPath.get(i).entrySet()) {

							if (city.equalsIgnoreCase(entry.getKey().get(entry.getKey().size() - 1))) {
								int travelTime = (int) Math
										.round(Utility.calcTravelTime(MainProperties.getInstance().getHrsPerDay(),
												MainProperties.getInstance().getMilesPerDay(), entry.getValue()) + .5);
								
								facilityRecords.add(new FacilityRecordImpl(city, facilityItemQuantity, days, travelTime,
										days + travelTime));

							}

						}
					}

				}
			}
		}
		Collections.sort(facilityRecords);
		//System.out.println(facilityRecords.toString());
		return facilityRecords;
	}
	
	public int bookSchedule(String source,String itemID,int itemQuantity,int orderTime) throws InvalidArgumentException
	{
		int orderTimeNew=orderTime;
		for(Facility facility: facilityList)
		{	
			if(facility.getFacilityName().equalsIgnoreCase(source))
			{
				int requiredQuantity=itemQuantity;
				while(requiredQuantity>0){
				String itemInSchedule = String.valueOf(facility.getSchedule().getSchedule().get(orderTimeNew));
				if (!itemInSchedule.equalsIgnoreCase("null")) {
					if (Integer.parseInt(itemInSchedule) > 0) {
						facility.getSchedule().getSchedule().put(orderTimeNew, 0);
						requiredQuantity = requiredQuantity - Integer.parseInt(itemInSchedule);
					}
				} else {
					if(requiredQuantity>=facility.getProcessingRate())
					{
					facility.getSchedule().addToSchedule(orderTimeNew, 0);
					requiredQuantity = requiredQuantity - facility.getProcessingRate();

					}else
					{
						facility.getSchedule().getSchedule().put(orderTimeNew, (facility.getProcessingRate()-requiredQuantity));
						requiredQuantity=0;
					}
				}
				orderTimeNew = orderTimeNew + 1;
				}
			//	System.out.println(facility.getFacilityName()+" "+facility.getSchedule().getSchedule().toString());
				int quantity= facility.getInventory().getInventoryList().get(itemID);
				facility.getInventory().getInventoryList().put(itemID, quantity-itemQuantity);
			//	System.out.println("facility "+facility.toString());
				break;
			}
		}
		return orderTimeNew-1;
	}
}