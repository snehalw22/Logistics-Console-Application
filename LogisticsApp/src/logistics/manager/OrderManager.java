package logistics.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import logistics.dto.LogisticsDetailsDto;
import logistics.dto.LogisticsRecordDto;
import logistics.dto.OrderDto;
import logistics.order.Order;
import logistics.record.LogisticsDetails;
import logistics.record.LogisticsRecord;

public class OrderManager {

	private static OrderManager orderManager;
	private ArrayList<Order> orderList = new ArrayList<>();

	private OrderManager() {
	}

	public static OrderManager getInstance() {
		if (orderManager == null) {
			synchronized (OrderManager.class) {
				if (orderManager == null)
					orderManager = new OrderManager();
			}
		}
		return orderManager;
	}

	public void addOrder(Order order) {
		if (order != null) {
			orderList.add(order);
		}
	}

	public ArrayList<OrderDto> getOrderList() {
		ArrayList<OrderDto> orderDtos = new ArrayList<>();
		for (Order order : orderList) {
			HashMap<String, LogisticsRecordDto> itemsLogisticsDto = new HashMap<>();
			for (Entry<String, LogisticsRecord> entry : order.getItemsLogistics().entrySet()) {
				ArrayList<LogisticsDetailsDto> logisticsDetailsDtos = new ArrayList<>();
				for (LogisticsDetails logisticsDetails : entry.getValue().getLogisticsDetails()) {
					LogisticsDetailsDto logisticsDetailsDto = new LogisticsDetailsDto(
							logisticsDetails.getFacilityName(), logisticsDetails.getCost(),
							logisticsDetails.getProcessingStart(), logisticsDetails.getProcessingEnd(),
							logisticsDetails.getTravelStart(), logisticsDetails.getTravelEnd());
					logisticsDetailsDtos.add(logisticsDetailsDto);
				}
				itemsLogisticsDto.put(entry.getKey(),
						new LogisticsRecordDto(entry.getValue().getItemId(), entry.getValue().getQuantity(),
								entry.getValue().getNoOfSources(), entry.getValue().getFirstArrivalDay(),
								entry.getValue().getLastArrivalDay(), entry.getValue().getTotalCost(),
								logisticsDetailsDtos));
			}
			HashMap<String, Integer> itemsDto = new HashMap<>();
			for(Entry<String, Integer> entry:order.getItems().entrySet())
			{
				itemsDto.put(entry.getKey()	, entry.getValue());
			}
			HashMap<String, Integer> backorderDto = new HashMap<>();
			for(Entry<String, Integer> entry:order.getBackorders().entrySet())
			{
				backorderDto.put(entry.getKey()	, entry.getValue());
			}
			 OrderDto orderDto=new OrderDto(order.getOrderId(),order.getOrderTime(), order.getDestination(), itemsDto, order.getFirstArrivalDay(), order.getLastArrivalDay(), order.getTotalCost(), itemsLogisticsDto, backorderDto);
			 orderDtos.add(orderDto);
		}
		return orderDtos;
	
	}
}
