

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.dto.FacilityDto;
import logistics.dto.ItemDto;
import logistics.dto.NetworkDto;
import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.NoSuchDataFoundException;
import logistics.exceptions.UnexpectedNodeException;
import logistics.manager.FacilityManager;
import logistics.manager.ItemManager;
import logistics.manager.NetworkManager;
import logistics.properties.MainProperties;
import logistics.utilities.Utility;

public class PhaseOneOutputGenerator {
	public DecimalFormat formatter = new DecimalFormat("0.#");
 static int hig=5;
	public void printItemCatalog() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException,
			SAXException, IOException, UnexpectedNodeException {
		System.out.println("Item Catalog:");
		int count = 4;
		for (ItemDto itemDto : ItemManager.getInstance().getItemDtoList()) {
			if (count > 0) {
				count--;
				System.out
						.print(itemDto.getIdentifier()
								+ Utility
										.printSpaces(
												itemDto.getIdentifier().length())
								+ ": $"
								+ NumberFormat.getNumberInstance()
										.format(itemDto.getPrice())
						+ Utility.printSpaces(
								String.valueOf(NumberFormat.getNumberInstance().format(itemDto.getPrice())).length()));
				if (count == 0) {
					System.out.println();
					count = 4;
				}

			}
		}
	}

	public void printFacility() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException,
			SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException {
		System.out.println();
		System.out.println("Facility Output:");
		for (FacilityDto facilityDto : FacilityManager.getInstance().getFacilityDtoList()) {
			System.out.println(
					"--------------------------------------------------------------------------------------------");
			System.out.println(facilityDto.getFacilityName());
			System.out.println("-----------");
			System.out.println("Rate per Day: " + facilityDto.getProcessingRate());
			System.out.println("Cost per Day: " + facilityDto.getProcessingCost());
			System.out.println();
			System.out.println("Direct Links:");
			ArrayList<NetworkDto> networkDtoList = NetworkManager.getInstance()
					.getNetworkDto(facilityDto.getFacilityName());
			for (NetworkDto networkDto : networkDtoList) {
				System.out
						.print(networkDto.getNeighborName() + " ("
								+ Utility.calcTravelTime(MainProperties.getInstance().getHrsPerDay(),
										MainProperties.getInstance().getMilesPerDay(), networkDto.getDistance())
						+ "d);  ");
			}
			System.out.println();
			System.out.println();
			System.out.println("Active Inventory:");
			System.out.println("    Item ID   Quantity");
			for (Map.Entry<String, Integer> entry : facilityDto.getInventoryDtoList().getInventoryDtoList().entrySet()) {
				System.out.println("    " + entry.getKey()
				+ Utility.printSpaces(entry.getKey().length()) + entry.getValue());
			}
			System.out.println();
			if (FacilityManager.getInstance().getDepletedInventory(facilityDto.getFacilityName()).size() == 0) {
				System.out.println("Depleted (Used-Up) Inventory: None");
			} else {
				System.out.println("Depleted (Used-Up) Inventory: "
						+ FacilityManager.getInstance().getDepletedInventory(facilityDto.getFacilityName()).toString());
			}
			System.out.println();
			System.out.println("Schedule:");
			System.out.print("Day:        ");
			for (int i = 0; i < MainProperties.getInstance().getScheduleDays(); i++) {
				/*if (facilityDto.getScheduleDto().getScheduleDtoList().size() <= i) {
					facilityDto.getScheduleDto().getScheduleDtoList().put(i + 1, facilityDto.getProcessingRate());
				}*/
				if (facilityDto.getScheduleDto().getScheduleDtoList().get(i+1)==null)
				{
					facilityDto.getScheduleDto().getScheduleDtoList().put(i + 1, facilityDto.getProcessingRate());
				}
				System.out.print((i + 1) + Utility.printScheduleSpaces(String.valueOf(i + 1).length()));
			}
			System.out.println();
			System.out.print("Available:  ");
			for (int i = 0; i < MainProperties.getInstance().getScheduleDays(); i++) {
				System.out.print(facilityDto.getScheduleDto().getScheduleDtoList().get(i+1).intValue() + Utility.printScheduleSpaces(
						String.valueOf(facilityDto.getScheduleDto().getScheduleDtoList().get(i+1).intValue()).length()));
			}
			System.out.println();
		}
	}

	public void printShortestPath(ArrayList<String[]> cities) throws InvalidArgumentException, FileNotFoundException,
			ParserConfigurationException, SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException {
		ArrayList<HashMap<ArrayList<String>, Double>> pathData = NetworkManager.getInstance()
				.calculateShortestPath(cities);
		System.out.println();
		System.out.println("Shortest Path Tests: ");
		System.out.println();
		for (int i = 0; i < pathData.size(); i++) {
			System.out.print(Utility.getAlphabets(i) + " ");
			for (Entry<ArrayList<String>, Double> entry : pathData.get(i).entrySet()) {
				System.out
						.println(entry.getKey().get(0) + " to " + entry.getKey().get(entry.getKey().size() - 1) + ": ");
				System.out.print("   -");
				int count = 1;
				for (String link : entry.getKey()) {
					if (count != entry.getKey().size()) {
						System.out.print(link + " -> ");
					} else {
						System.out.print(link);
					}
					count = count + 1;
				}
				System.out
						.println(" = " + NumberFormat.getNumberInstance().format(entry.getValue().intValue()) + " mi");
				System.out
						.println(
								"   -" + NumberFormat.getNumberInstance().format(entry.getValue().intValue())
										+ " mi / (" + formatter.format(MainProperties.getInstance().getHrsPerDay())
										+ " hours per day * "
										+ formatter.format(MainProperties.getInstance().getMilesPerDay()) + " kmph) = "
										+ Utility.calcTravelTime(MainProperties.getInstance().getHrsPerDay(),
												MainProperties.getInstance().getMilesPerDay(), entry.getValue())
						+ " days");
				System.out.println();
			}
		}
	}
	
	public void shortestPath() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException
	{
		ArrayList<String[]> cities = new ArrayList<>();
		cities.add(new String[] { "Santa Fe, NM", "Chicago, IL" });
		cities.add(new String[] { "Atlanta, GA", "St. Louis, MO" });
		cities.add(new String[] { "Seattle, WA", "Nashville, TN" });
		cities.add(new String[] { "New York City, NY", "Phoenix, AZ" });
		cities.add(new String[] { "Fargo, ND", "Austin, TX" });
		cities.add(new String[] { "Denver, CO", "Miami, FL" });
		cities.add(new String[] { "Austin, TX", "Norfolk, VA" });
		cities.add(new String[] { "Miami, FL", "Seattle, WA" });
		cities.add(new String[] { "Los Angeles, CA", "Chicago, IL" });
		cities.add(new String[] { "Detroit, MI", "Nashville, TN" });	
				
		printShortestPath(cities);
	}
}
