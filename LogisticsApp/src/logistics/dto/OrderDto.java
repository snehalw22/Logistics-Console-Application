package logistics.dto;

import java.util.HashMap;

public class OrderDto {
	public String orderId;
	public int orderTime;
	public String destination;
	public HashMap<String, Integer> items;
	public int firstArrivalDay;
	public int lastArrivalDay;
	public double totalCost;
	public HashMap<String, LogisticsRecordDto> itemsLogisticsDto;
	public HashMap<String, Integer> backorders;
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
	public HashMap<String, LogisticsRecordDto> getItemsLogisticsDto() {
		return itemsLogisticsDto;
	}
	public HashMap<String, Integer> getBackorders() {
		return backorders;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setOrderTime(int orderTime) {
		this.orderTime = orderTime;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setItems(HashMap<String, Integer> items) {
		this.items = items;
	}
	public void setFirstArrivalDay(int firstArrivalDay) {
		this.firstArrivalDay = firstArrivalDay;
	}
	public void setLastArrivalDay(int lastArrivalDay) {
		this.lastArrivalDay = lastArrivalDay;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public void setItemsLogisticsDto(HashMap<String, LogisticsRecordDto> itemsLogisticsDto) {
		this.itemsLogisticsDto = itemsLogisticsDto;
	}
	public void setBackorders(HashMap<String, Integer> backorders) {
		this.backorders = backorders;
	}
	public OrderDto(String orderId, int orderTime, String destination, HashMap<String, Integer> items,
			int firstArrivalDay, int lastArrivalDay, double totalCost, HashMap<String, LogisticsRecordDto> itemsLogisticsDto,
			HashMap<String, Integer> backorders) {
		this.orderId = orderId;
		this.orderTime = orderTime;
		this.destination = destination;
		this.items = items;
		this.firstArrivalDay = firstArrivalDay;
		this.lastArrivalDay = lastArrivalDay;
		this.totalCost = totalCost;
		this.itemsLogisticsDto = itemsLogisticsDto;
		this.backorders = backorders;
	}
	private OrderDto(){}
	@Override
	public String toString() {
		return "OrderDto [orderId=" + orderId + ", orderTime=" + orderTime + ", destination=" + destination + ", items="
				+ items + ", firstArrivalDay=" + firstArrivalDay + ", lastArrivalDay=" + lastArrivalDay + ", totalCost="
				+ totalCost + ", itemsLogisticsDto=" + itemsLogisticsDto + ", backorders=" + backorders + "]";
	}
	
	
}
