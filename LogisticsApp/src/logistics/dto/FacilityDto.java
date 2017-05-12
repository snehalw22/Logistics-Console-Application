package logistics.dto;

import java.util.ArrayList;

public class FacilityDto {
	private String facilityName;
	private int processingRate;
	private double processingCost;
	private InventoryDto inventoryDtoList;
	private ScheduleDto scheduleDto;
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public int getProcessingRate() {
		return processingRate;
	}
	public void setProcessingRate(int processingRate) {
		this.processingRate = processingRate;
	}
	public double getProcessingCost() {
		return processingCost;
	}
	public void setProcessingCost(double processingCost) {
		this.processingCost = processingCost;
	}

	public InventoryDto getInventoryDtoList() {
		return inventoryDtoList;
	}
	public void setInventoryDtoList(InventoryDto inventoryDtoList) {
		this.inventoryDtoList = inventoryDtoList;
	}
	public ScheduleDto getScheduleDto() {
		return scheduleDto;
	}
	public void setScheduleDto(ScheduleDto scheduleDto) {
		this.scheduleDto = scheduleDto;
	}
	public FacilityDto(String facilityName, int processingRate, double processingCost,
			InventoryDto inventoryList, ScheduleDto scheduleDto) {
		super();
		this.facilityName = facilityName;
		this.processingRate = processingRate;
		this.processingCost = processingCost;
		this.inventoryDtoList = inventoryList;
		this.scheduleDto = scheduleDto;
	}
	private FacilityDto(){}
	@Override
	public String toString() {
		return "FacilityDto [facilityName=" + facilityName + ", processingRate=" + processingRate + ", processingCost="
				+ processingCost + ", inventoryDtoList=" + inventoryDtoList + ", scheduleDto=" + scheduleDto + "]";
	}
	
}
