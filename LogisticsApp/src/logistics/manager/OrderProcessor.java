package logistics.manager;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.NoSuchDataFoundException;
import logistics.exceptions.UnexpectedNodeException;
import logistics.facility.FacilityRecord;
import logistics.factory.LogisticsDetailsFactory;
import logistics.factory.LogisticsRecordFactory;
import logistics.factory.OrderFactory;
import logistics.order.Order;
import logistics.properties.MainProperties;
import logistics.record.LogisticsDetails;
import logistics.record.LogisticsRecord;

public class OrderProcessor {

	private static OrderProcessor orderProcessor;

	private OrderProcessor() {
	}

	public static OrderProcessor getInstance() {
		if (orderProcessor == null) {
			synchronized (OrderProcessor.class) {
				if (orderProcessor == null)
					orderProcessor = new OrderProcessor();
			}
		}
		return orderProcessor;
	}

	public void processOrder(String orderId, String destination, int orderTime, HashMap<String, Integer> items)
			throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException,
			IOException, UnexpectedNodeException, NoSuchDataFoundException {
		HashMap<String, LogisticsRecord> logisticsRecords= new HashMap<>();
		double totalCost=0;
		ArrayList<Integer> orderArrivalDays=new ArrayList<>();
		HashMap<String, Integer> backorders =new HashMap<String, Integer>();
		
		for (Entry<String, Integer> item : items.entrySet()) {
			ArrayList<LogisticsDetails> logisticsDetailsList=new ArrayList<>();
			ArrayList<FacilityRecord> facilityRecords = FacilityManager.getInstance().getFacilityRecords(destination,item.getKey(), item.getValue(), orderTime);
			int reqQuantity = item.getValue();
			double cost = 0;
			int totalQuantity = 0;
			ArrayList<Integer> itemArrivalDays=new ArrayList<>();
			
			
			for (FacilityRecord facilityRecord : facilityRecords) {
				
				double facilityCost;
				int processingEnd;			
				double processingRate = FacilityManager.getInstance().getPtocessingRate(facilityRecord.getItemSource());
				double processingCost = FacilityManager.getInstance().getPtocessingCost(facilityRecord.getItemSource());
				double itemCost = ItemManager.getInstance().getPrice(item.getKey());
				if (reqQuantity > 0) {
					if (reqQuantity > facilityRecord.getNoOfItems()) {
						processingEnd = FacilityManager.getInstance().bookSchedule(facilityRecord.getItemSource(),item.getKey(), facilityRecord.getNoOfItems(), orderTime);
						facilityCost = (facilityRecord.getNoOfItems() * itemCost)+ ((facilityRecord.getNoOfItems() / processingRate) * processingCost)+ (MainProperties.getInstance().getTravelCostPerDay() * facilityRecord.getTravelTime());
						reqQuantity = reqQuantity - facilityRecord.getNoOfItems();
						totalQuantity=totalQuantity+facilityRecord.getNoOfItems();
						
					} else {
						processingEnd = FacilityManager.getInstance().bookSchedule(facilityRecord.getItemSource(),item.getKey(), reqQuantity, orderTime);
						facilityCost = (reqQuantity * itemCost) + ((reqQuantity / processingRate) * processingCost)+ (MainProperties.getInstance().getTravelCostPerDay() * facilityRecord.getTravelTime());
						totalQuantity=totalQuantity+reqQuantity;
						reqQuantity = 0;
					}
					itemArrivalDays.add(processingEnd + facilityRecord.getTravelTime());
					orderArrivalDays.add(processingEnd + facilityRecord.getTravelTime());
					facilityCost=Math.round(facilityCost*100.0)/100.0;
					cost=cost+facilityCost;
					logisticsDetailsList.add(LogisticsDetailsFactory.createLogisticsDetail(
							"LogisticsDetailsImpl", facilityRecord.getItemSource(), facilityCost, orderTime, processingEnd,
							processingEnd + 1, processingEnd + facilityRecord.getTravelTime()));
				} else {
					break;
				}
			}
			
			if(reqQuantity>0)
			{
				backorders.put(item.getKey(), reqQuantity);
			}
			totalCost=totalCost+cost;
			Collections.sort(itemArrivalDays);
			logisticsRecords.put(item.getKey(),LogisticsRecordFactory.createRecord("LogisticsRecordImpl", item.getKey(),totalQuantity, logisticsDetailsList.size(), itemArrivalDays.get(0), itemArrivalDays.get(itemArrivalDays.size()-1), cost, logisticsDetailsList));
		}	
		
		
		Collections.sort(orderArrivalDays);
		Order order=OrderFactory.createOrder("OrderImpl", orderId, orderTime, destination, items, logisticsRecords, orderArrivalDays.get(0), orderArrivalDays.get(orderArrivalDays.size()-1), totalCost, backorders);
		//System.out.println(order.toString());
		OrderManager.getInstance().addOrder(order);
	}
	
	
}
