package logistics.order;

import java.util.HashMap;

import logistics.record.LogisticsRecord;

public interface Order {
	public HashMap<String, LogisticsRecord> getItemsLogistics();
	public String getOrderId() ;
	public int getOrderTime() ;
	public String getDestination() ;
	public HashMap<String, Integer> getItems();
	public int getFirstArrivalDay() ;
	public int getLastArrivalDay();
	public double getTotalCost() ;
	public HashMap<String, Integer> getBackorders();
}
