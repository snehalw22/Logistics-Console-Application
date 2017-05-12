package logistics.facility;

public class FacilityRecordImpl implements FacilityRecord{
	
	private String itemSource;
	private int noOfItems;
	private int processingEndDay;
	private int travelTime;
	private Integer arrivaDay;
	
	public String getItemSource() {
		return itemSource;
	}
	private void setItemSource(String itemSource) {
		this.itemSource = itemSource;
	}
	public int getNoOfItems() {
		return noOfItems;
	}
	private void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}
	public int getProcessingEndDay() {
		return processingEndDay;
	}
	private void setProcessingEndDay(int processingEndDay) {
		this.processingEndDay = processingEndDay;
	}
	public int getTravelTime() {
		return travelTime;
	}
	private void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}
	public Integer getArrivaDay() {
		return arrivaDay;
	}
	public void setArrivaDay(Integer arrivaDay) {
		this.arrivaDay = arrivaDay;
	}
	public FacilityRecordImpl(String itemSource, int noOfItems, int processingEndDay, int travelTime, int arrivaDay) {
		setItemSource(itemSource);
		setNoOfItems(noOfItems);
		setProcessingEndDay(processingEndDay);
		setTravelTime(travelTime);
		setArrivaDay(arrivaDay);
	}
	private FacilityRecordImpl() {}

	@Override
	public String toString() {
		return "FacilityRecordImpl [itemSource=" + itemSource + ", noOfItems=" + noOfItems + ", processingEndDay="
				+ processingEndDay + ", travelTime=" + travelTime + ", arrivaDay=" + arrivaDay + "]";
	}
	@Override
	public int compareTo(FacilityRecord o) {
		return this.getArrivaDay().compareTo(o.getArrivaDay());
	}
	
	
}
