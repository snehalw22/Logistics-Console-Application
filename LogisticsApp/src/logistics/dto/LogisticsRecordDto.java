package logistics.dto;

import java.util.ArrayList;

public class LogisticsRecordDto {
	private String itemId;
	private int quantity;
	private int noOfSources;
	private int firstArrivalDay;
	private int lastArrivalDay;
	private double totalCost;
	private ArrayList<LogisticsDetailsDto> logisticsDetailsDto;
	public String getItemId() {
		return itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getNoOfSources() {
		return noOfSources;
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
	public ArrayList<LogisticsDetailsDto> getLogisticsDetailsDto() {
		return logisticsDetailsDto;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setNoOfSources(int noOfSources) {
		this.noOfSources = noOfSources;
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
	public void setLogisticsDetailsDto(ArrayList<LogisticsDetailsDto> logisticsDetailsDto) {
		this.logisticsDetailsDto = logisticsDetailsDto;
	}
	public LogisticsRecordDto(String itemId, int quantity, int noOfSources, int firstArrivalDay, int lastArrivalDay,
			double totalCost, ArrayList<LogisticsDetailsDto> logisticsDetailsDto) {
		this.itemId = itemId;
		this.quantity = quantity;
		this.noOfSources = noOfSources;
		this.firstArrivalDay = firstArrivalDay;
		this.lastArrivalDay = lastArrivalDay;
		this.totalCost = totalCost;
		this.logisticsDetailsDto = logisticsDetailsDto;
	}
	@Override
	public String toString() {
		return "LogisticsRecordDto [itemId=" + itemId + ", quantity=" + quantity + ", noOfSources=" + noOfSources
				+ ", firstArrivalDay=" + firstArrivalDay + ", lastArrivalDay=" + lastArrivalDay + ", totalCost="
				+ totalCost + ", logisticsDetailsDto=" + logisticsDetailsDto + "]";
	}
	
	
}
