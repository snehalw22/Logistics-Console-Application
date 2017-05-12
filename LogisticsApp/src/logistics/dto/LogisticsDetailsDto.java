package logistics.dto;

public class LogisticsDetailsDto {
	private String facilityName;
	private double cost;
	private int processingStart;
	private int processingEnd;
	private int travelStart;
	private int travelEnd;
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getProcessingStart() {
		return processingStart;
	}
	public void setProcessingStart(int processingStart) {
		this.processingStart = processingStart;
	}
	public int getProcessingEnd() {
		return processingEnd;
	}
	public void setProcessingEnd(int processingEnd) {
		this.processingEnd = processingEnd;
	}
	public int getTravelStart() {
		return travelStart;
	}
	public void setTravelStart(int travelStart) {
		this.travelStart = travelStart;
	}
	public int getTravelEnd() {
		return travelEnd;
	}
	public void setTravelEnd(int travelEnd) {
		this.travelEnd = travelEnd;
	}
	public LogisticsDetailsDto(String facilityName, double cost, int processingStart, int processingEnd,
			int travelStart, int travelEnd) {
		this.facilityName = facilityName;
		this.cost = cost;
		this.processingStart = processingStart;
		this.processingEnd = processingEnd;
		this.travelStart = travelStart;
		this.travelEnd = travelEnd;
	}
	
	private LogisticsDetailsDto(){}
	}
