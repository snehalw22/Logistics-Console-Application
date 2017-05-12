package logistics.record;

import java.util.ArrayList;

import logistics.exceptions.InvalidArgumentException;

public class LogisticsRecordImpl implements LogisticsRecord{

	private String itemId;
	private int quantity;
	private int noOfSources;
	private int firstArrivalDay;
	private int lastArrivalDay;
	private double totalCost;
	private ArrayList<LogisticsDetails> logisticsDetails;
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
	public ArrayList<LogisticsDetails> getLogisticsDetails() {
		return logisticsDetails;
	}
	private void setItemId(String itemId) throws InvalidArgumentException {
		 if(itemId==null||itemId.isEmpty())	 
			 throw new InvalidArgumentException("Invalid (empty or null) value passed to LogisticsRecordImpl 'itemId'");
		 else
			 this.itemId=itemId;
	}
	private void setQuantity(int quantity) throws InvalidArgumentException {
		 if (quantity <= 0)
	            throw new InvalidArgumentException("Invalid quantity (zero or less) value is passed to LogisticsRecordImpl");
		  this.quantity = quantity;
	}
	private void setNoOfSources(int noOfSources) throws InvalidArgumentException {
		 if (noOfSources <0)
	            throw new InvalidArgumentException("Invalid noOfSources (less) value is passed to LogisticsRecordImpl");
		  this.noOfSources = noOfSources;
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
	private void setLogisticsDetails(ArrayList<LogisticsDetails> logisticsDetails) throws InvalidArgumentException {
		if(logisticsDetails==null)
		{
			throw new InvalidArgumentException("Invalid logisticsDetails (null) value is passed to LogisticsRecordImpl");
		}
		this.logisticsDetails = logisticsDetails;
	}
	public LogisticsRecordImpl(String itemId, int quantity, int noOfSources, int firstArrivalDay, int lastArrivalDay,
			double totalCost, ArrayList<LogisticsDetails> logisticsDetails) throws InvalidArgumentException {
		setItemId(itemId);
		setQuantity(quantity);
		setNoOfSources(noOfSources);
		setFirstArrivalDay(firstArrivalDay);
		setLastArrivalDay(lastArrivalDay);
		setTotalCost(totalCost);
		setLogisticsDetails(logisticsDetails);
	}
	
	
	private LogisticsRecordImpl(){}
	@Override
	public String toString() {
		return "LogisticsRecordImpl [itemId=" + itemId + ", quantity=" + quantity + ", noOfSources=" + noOfSources
				+ ", firstArrivalDay=" + firstArrivalDay + ", lastArrivalDay=" + lastArrivalDay + ", totalCost="
				+ totalCost + ", logisticsDetails=" + logisticsDetails + "]";
	}
	
	
}
