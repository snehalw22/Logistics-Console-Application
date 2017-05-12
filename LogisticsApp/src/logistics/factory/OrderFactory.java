package logistics.factory;

import java.util.HashMap;

import logistics.exceptions.InvalidArgumentException;
import logistics.order.Order;
import logistics.order.OrderImpl;
import logistics.record.LogisticsRecord;

public class OrderFactory {
private OrderFactory() {}
	
	public static Order createOrder(String  type,String orderId,int orderTime,String destination,HashMap<String, Integer> items,HashMap<String, LogisticsRecord> logisticsRecord,int firstArrivalDay, int lastArrivalDay,
			double totalCost,HashMap<String, Integer> backorders) throws InvalidArgumentException
	{
	if (type.equalsIgnoreCase("OrderImpl"))
	{
		return new OrderImpl(orderId, orderTime, destination, items, firstArrivalDay, lastArrivalDay, totalCost, logisticsRecord, backorders);	
	}
	else return null;
	}
}
