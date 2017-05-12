package logistics.facility;

public interface FacilityRecord extends Comparable<FacilityRecord>{
	public String getItemSource();
	public int getNoOfItems();
	public int getProcessingEndDay();
	public int getTravelTime();
	public Integer getArrivaDay();

}
