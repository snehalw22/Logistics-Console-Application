package logistics.facility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.NoSuchDataFoundException;
import logistics.exceptions.UnexpectedNodeException;
import logistics.factory.FacilityFactory;
import logistics.factory.InventoryFactory;
import logistics.factory.ScheduleFactory;
import logistics.inventory.Inventory;
import logistics.manager.ItemManager;
import logistics.schedule.Schedule;
import logistics.utilities.Utility;

public class XMLFacilityLoader implements FacilityLoader {

	@Override
	public ArrayList<Facility> loadFacilities(String fileName, String facilityType)
			throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException,
			UnexpectedNodeException, NoSuchDataFoundException, NumberFormatException, IOException {
		if (fileName == null || fileName.isEmpty())
			throw new InvalidArgumentException("Invalid (empty or null) value passed to location 'loadFacilities'");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		File xml = new File(fileName);
		if (!xml.exists()) {

			throw new FileNotFoundException(
					"File not found for the facilities at the location given for 'loadFacilities'");
		}
		Document doc = db.parse(xml);
		doc.getDocumentElement().normalize();

		NodeList facilityEntries = doc.getDocumentElement().getChildNodes();
		ArrayList<Facility> facilityList = new ArrayList<Facility>();
		for (int i = 0; i < facilityEntries.getLength(); i++) {
			if (facilityEntries.item(i).getNodeType() == Node.TEXT_NODE) {
				continue;
			}

			String entryName = facilityEntries.item(i).getNodeName();
			if (!entryName.equals("Facility")) {
				throw new UnexpectedNodeException("Unexpected Node found in the  XML " + fileName);

			}

			// Get a node attribute
			NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
			String facilityName = aMap.getNamedItem("facilityName").getNodeValue().trim();
			// Get a named nodes
			Element elem = (Element) facilityEntries.item(i);
			if (elem.getElementsByTagName("processingRate").item(0).getTextContent().trim().length() == 0) {
				throw new NumberFormatException("The Processing rate of " + facilityName + " is null.");
			}
			if (Utility
					.isNumeric(elem.getElementsByTagName("processingRate").item(0).getTextContent().trim()) == false) {
				throw new InvalidArgumentException("The Processing rate of " + facilityName + " is invalid.");
			}
			int processingRate = Integer
					.parseInt(elem.getElementsByTagName("processingRate").item(0).getTextContent().trim());
			if (elem.getElementsByTagName("processingCost").item(0).getTextContent().length() == 0) {
				throw new NumberFormatException("The Processing cost of " + facilityName + " is null.");
			}
			if (Utility
					.isNumeric(elem.getElementsByTagName("processingCost").item(0).getTextContent().trim()) == false) {
				throw new InvalidArgumentException("The Processing cost of " + facilityName + " is invalid.");
			}
			double processingCost = Double
					.parseDouble(elem.getElementsByTagName("processingCost").item(0).getTextContent().trim());

			// Get all nodes named "Inventory" - there can be 0 or more
			TreeMap<String, Integer> inventories = new TreeMap<>();
			NodeList inventoryList = elem.getElementsByTagName("Inventory");
			for (int j = 0; j < inventoryList.getLength(); j++) {
				if (inventoryList.item(j).getNodeType() == Node.TEXT_NODE) {
					continue;
				}

				entryName = inventoryList.item(j).getNodeName();
				if (!entryName.equals("Inventory")) {
					throw new UnexpectedNodeException("Unexpected Node found in the  XML " + fileName);
				}
				elem = (Element) inventoryList.item(j);
				 if(elem.getElementsByTagName("itemId").item(0)==null)
	             {
	            	 throw new InvalidArgumentException("itemId is null in the  XML given for 'loadFacility'");

	             }
				String itemId = elem.getElementsByTagName("itemId").item(0).getTextContent().trim();
				if (!ItemManager.getInstance().ifExists(itemId)) {
					throw new NoSuchDataFoundException(
							"The Item ID " + itemId + " in the  XML " + fileName + " does not exist.");
				}
				if(elem.getElementsByTagName("quantity").item(0)==null)
	             {
	            	 throw new InvalidArgumentException("quantity is null in the  XML given for 'loadFacility'");

	             }
				if (elem.getElementsByTagName("quantity").item(0).getTextContent().trim().length() == 0) {
					throw new NumberFormatException(
							"The quantity of " + facilityName + ", Inventory " + itemId + " is null.");
				}
				if (Utility.isNumeric(elem.getElementsByTagName("quantity").item(0).getTextContent().trim()) == false) {
					throw new InvalidArgumentException(
							"The quantity of " + facilityName + ", Inventory " + itemId + " is invalid.");
				}
				int quantity = Integer.parseInt(elem.getElementsByTagName("quantity").item(0).getTextContent().trim());
				inventories.put(itemId, quantity);
			}
			Inventory inventory = InventoryFactory.createInventory("InventoryImpl", inventories);
			Schedule schedule1 = ScheduleFactory.createSchedule("ScheduleImpl", new HashMap<>());
			Facility facility = FacilityFactory.createFacility(facilityType, facilityName, processingRate,
					processingCost, inventory, schedule1);
			facilityList.add(facility);
		}
		Collections.sort(facilityList);
		return facilityList;
	}

}
