package logistics.order;

import java.util.HashMap;
import java.util.Map;

import logistics.exceptions.InvalidArgumentException;
import logistics.record.LogisticsRecord;

public class OrderImpl implements Order{

	private String orderId;
	private int orderTime;
	private String destination;
	private HashMap<String, Integer> items;
	private int firstArrivalDay;
	private int lastArrivalDay;
	private double totalCost;
	private HashMap<String, LogisticsRecord> itemsLogistics;
	private HashMap<String, Integer> backorders;
	public HashMap<String, LogisticsRecord> getItemsLogistics() {
		return itemsLogistics;
	}
	public String getOrderId() {
		return orderId;
	}
	public int getOrderTime() {
		return orderTime;
	}
	public String getDestination() {
		return destination;
	}
	public HashMap<String, Integer> getItems() {
		return items;
	}
	
	public int getFirstArrivalDay() {
		return firstArrivalDay;
	}
	public int getLastArrivalDay() {
		return lastArrivalDay;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	public HashMap<String, Integer> getBackorders() {
		return backorders;
	}
	private void setItemsLogistics(HashMap<String, LogisticsRecord> itemsLogistics) throws InvalidArgumentException {
		if(itemsLogistics.size()==0)	
			 throw new InvalidArgumentException("Invalid (empty or null) value passed to OrderImpl 'itemsLogistics'");
			 else
				 this.itemsLogistics=itemsLogistics;
	}
	private void setOrderId(String orderId) throws InvalidArgumentException {
		 if(orderId==null||orderId.isEmpty())	 
			 throw new InvalidArgumentException("Invalid (empty or null) value passed to OrderImpl 'OrderId'");
		 else
			 this.orderId=orderId;
		}
	
	private void setOrderTime(int orderTime) throws InvalidArgumentException {
		 if (orderTime <= 0)
	            throw new InvalidArgumentException("Invalid orderTime (zero or less) value is passed to OrderImpl");
		  this.orderTime = orderTime;
	}
	private void setDestination(String destination) throws InvalidArgumentException {
		 if(destination==null||destination.isEmpty())	 
			 throw new InvalidArgumentException("Invalid (empty or null) value passed to OrderImpl 'destination'");
		 else
			 this.destination=destination;
		}
	private void setItems(HashMap<String, Integer> items) throws InvalidArgumentException {
		if(items==null||items.size()==0)
		{
			throw new InvalidArgumentException("Invalid parameter. items is null in OrderImpl");
		}
		
		if(items.size()>0)
		{
		for (Map.Entry<String, Integer> entry : items.entrySet()) {
			if(entry.getKey()==null||entry.getKey().isEmpty())	 
				 throw new InvalidArgumentException("Invalid (empty or null) value passed to itemID of OrderImpl 'items'");
			if (entry.getValue() <= 0)
				throw new InvalidArgumentException("Invalid available (zero or less) value is passed to OrderImpl");
		}
		}
		this.items = items;
	}

	private void setFirstArrivalDay(int firstArrivalDay) throws InvalidArgumentException {
		 if (firstArrivalDay <= 0)
	            throw new InvalidArgumentException("Invalid firstArrivalDay (zero or less) value is passed to LogisticsRecordImpl");
		  this.firstArrivalDay = firstArrivalDay;
	}
	private void setLastArrivalDay(int lastArrivalDay) throws InvalidArgumentException {
		 if (lastArrivalDay <= 0)
	            throw new InvalidArgumentException("Invalid lastArrivalDay (zero or less) value is passed to LogisticsRecordImpl");
		  this.lastArrivalDay = lastArrivalDay;
	}
	private void setTotalCost(double totalCost) throws InvalidArgumentException {
		 if (totalCost <= 0)
	            throw new InvalidArgumentException("Invalid totalCost (zero or less) value is passed to LogisticsRecordImpl");
		  this.totalCost = totalCost;
	}
	
	
	private void setBackorders(HashMap<String, Integer> backorders) throws InvalidArgumentException {
		if(backorders==null)	
			 throw new InvalidArgumentException("Invalid (empty or null) value passed to OrderImpl 'backorders'");
			 else
				 this.backorders=backorders;
	}
	
	
	public OrderImpl(String orderId, int orderTime, String destination, HashMap<String, Integer> items,
			int firstArrivalDay, int lastArrivalDay, double totalCost, HashMap<String, LogisticsRecord> itemsLogistics,
			HashMap<String, Integer> backorders) throws InvalidArgumentException {
		setOrderId(orderId);
		setOrderTime(orderTime);
		setDestination(destination);
		setItems(items);
		setItemsLogistics(itemsLogistics);
		setFirstArrivalDay(firstArrivalDay);
		setLastArrivalDay(lastArrivalDay);
		setTotalCost(totalCost);
		setBackorders(backorders);
	}
	
	
	private OrderImpl(){}
	@Override
	public String toString() {
		return "OrderImpl [orderId=" + orderId + ", orderTime=" + orderTime + ", destination=" + destination
				+ ", items=" + items + ", firstArrivalDay=" + firstArrivalDay + ", lastArrivalDay=" + lastArrivalDay
				+ ", totalCost=" + totalCost + ", itemsLogistics=" + itemsLogistics + ", backorders=" + backorders
				+ "]";
	}
	

}
