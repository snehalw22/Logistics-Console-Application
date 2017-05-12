import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.dto.LogisticsRecordDto;
import logistics.dto.OrderDto;
import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.NoSuchDataFoundException;
import logistics.exceptions.UnexpectedNodeException;
import logistics.manager.OrderManager;
import logistics.manager.OrderProcessor;
import logistics.record.LogisticsRecord;
import logistics.utilities.Utility;

public class PhaseTwoOutputGenerator {

	public void processOrder() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException
	{
		HashMap<String, Integer> items= new HashMap<>();	
		items.put("ABC123", 180);
		items.put("CR2032", 320);
		OrderProcessor.getInstance().processOrder("TO-001", "Miami, FL", 1, items);
		
		items= new HashMap<>();
		
		items.put("PL132-C", 5);
		items.put("XLK200B", 120);
		OrderProcessor.getInstance().processOrder("TO-002", "Boston, MA", 1, items);
		
		items= new HashMap<>();
		
		items.put("XTP202", 100);
		items.put("E241i", 105);
		OrderProcessor.getInstance().processOrder("TO-003", "Seattle, WA", 4, items);
		
		items= new HashMap<>();
		
		items.put("CR2032",200);
		items.put("SR71-D", 85);
		OrderProcessor.getInstance().processOrder("TO-004", "Phoenix, AZ", 10, items);
		
		items= new HashMap<>();
		
		items.put("XLK200B", 120);
		items.put("RX100-3", 200);
		OrderProcessor.getInstance().processOrder("TO-005", "Chicago, IL", 7, items);
		
		items= new HashMap<>();
		
		items.put("CT1928", 120);
		OrderProcessor.getInstance().processOrder("TO-006", "Denver, CO", 8, items);
		//OrderManager.getInstance().printOrders();
		ArrayList<OrderDto> orderDtos= OrderManager.getInstance().getOrderList();
		
		
		System.out.println("----------------------------------------------------------------------------------");
		for (int i = 0; i < orderDtos.size(); i++) {
			
			System.out.println("Order #" + (i + 1));
			System.out.println("Order Id:            " + orderDtos.get(i).getOrderId());
			System.out.println("Order Time:          " + orderDtos.get(i).getOrderTime());
			System.out.println("Destination:         " + orderDtos.get(i).getDestination());
			System.out.println("List of Order Items: ");
			int count = 1;
			for (Entry<String, Integer> entry : orderDtos.get(i).getItems().entrySet()) {
				System.out.println(count + ") " + "Item ID: " + entry.getKey()
						+ Utility.printSpaces(entry.getKey().length()) + entry.getValue());
				count++;
			}
			System.out.println();
			System.out.println("Processing Solution:");
			System.out.println("Total Cost:          " + orderDtos.get(i).getTotalCost());
			System.out.println("1st Delivery Day:    " + orderDtos.get(i).getFirstArrivalDay());
			System.out.println("Last Delivery Day:   " + orderDtos.get(i).getLastArrivalDay());
			System.out.println("Order Items:");
			System.out.println();
			System.out.println("Item ID   Quantity  Cost      #Sources  First Day Last Day");
			for (Entry<String, LogisticsRecordDto> logisticRecord : orderDtos.get(i).getItemsLogisticsDto().entrySet()) {
				System.out.print(logisticRecord.getKey() + Utility.printSpaces(logisticRecord.getKey().length()));
				System.out.print(logisticRecord.getValue().getQuantity()
						+ Utility.printSpaces(String.valueOf(logisticRecord.getValue().getQuantity()).length()));
				System.out.print(logisticRecord.getValue().getTotalCost()
						+ Utility.printSpaces(String.valueOf(logisticRecord.getValue().getTotalCost()).length()));
				System.out.print(logisticRecord.getValue().getNoOfSources()
						+ Utility.printSpaces(String.valueOf(logisticRecord.getValue().getNoOfSources()).length()));
				System.out.print(logisticRecord.getValue().getFirstArrivalDay()
						+ Utility.printSpaces(String.valueOf(logisticRecord.getValue().getFirstArrivalDay()).length()));
				System.out.print(logisticRecord.getValue().getLastArrivalDay()
						+ Utility.printSpaces(String.valueOf(logisticRecord.getValue().getLastArrivalDay()).length()));
				System.out.println();
			}
			System.out.println();
			if (orderDtos.get(i).getBackorders().size() > 0) {
				System.out.println("BackOrders");
				System.out.println("ItemID    Quantity");

				for (Entry<String, Integer> backOrder : orderDtos.get(i).getBackorders().entrySet()) {
					System.out.println(backOrder.getKey() + Utility.printSpaces(backOrder.getKey().length())
							+ backOrder.getValue());
				}
			}
			System.out.println("----------------------------------------------------------------------------------");
		}
	
	}
	
	
	
}
