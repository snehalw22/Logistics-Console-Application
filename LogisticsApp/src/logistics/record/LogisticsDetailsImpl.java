package logistics.record;

import logistics.exceptions.InvalidArgumentException;

public class LogisticsDetailsImpl implements LogisticsDetails{

	private String facilityName;
	private double cost;
	private int processingStart;
	private int processingEnd;
	private int travelStart;
	private int travelEnd;
	public String getFacilityName() {
		return facilityName;
	}
	public double getCost() {
		return cost;
	}
	public int getProcessingStart() {
		return processingStart;
	}
	public int getProcessingEnd() {
		return processingEnd;
	}
	public int getTravelStart() {
		return travelStart;
	}
	public int getTravelEnd() {
		return travelEnd;
	}
	private void setFacilityName(String facilityName) throws InvalidArgumentException {
		if (facilityName == null || facilityName.isEmpty())
			throw new InvalidArgumentException(
					"Invalid (empty or null) value passed to LogisticsDetailsImpl 'setFacilityName'");
		else
			this.facilityName = facilityName;
	}
	private void setCost(double cost) throws InvalidArgumentException  {
		 if (cost <= 0.0)
	            throw new InvalidArgumentException("Invalid price (zero or less) value is passed to LogisticsDetailsImpl");
		  this.cost = cost;
		
	}
	private void setProcessingStart(int processingStart) throws InvalidArgumentException {
		if (processingStart <= 0)
            throw new InvalidArgumentException("Invalid processingStart (zero or less) value is passed to LogisticsDetailsImpl");
	  this.processingStart = processingStart;
	
}
	private void setProcessingEnd(int processingEnd) throws InvalidArgumentException {
		if (processingEnd <= 0)
            throw new InvalidArgumentException("Invalid processingEnd (zero or less) value is passed to LogisticsDetailsImpl");
	  this.processingEnd = processingEnd;
	
}
	private void setTravelStart(int travelStart) throws InvalidArgumentException {
		if (travelStart <= 0)
            throw new InvalidArgumentException("Invalid travelStart (zero or less) value is passed to LogisticsDetailsImpl");
	  this.travelStart = travelStart;
	
}
	private void setTravelEnd(int travelEnd) throws InvalidArgumentException {
		if (travelEnd <= 0)
            throw new InvalidArgumentException("Invalid travelEnd (zero or less) value is passed to LogisticsDetailsImpl");
	  this.travelEnd = travelEnd;
	
}
	public LogisticsDetailsImpl(String facilityName, double cost, int processingStart, int processingEnd,
			int travelStart, int travelEnd) throws InvalidArgumentException {
		
		setFacilityName(facilityName);
		setCost(cost);
		setProcessingStart(processingStart);
		setProcessingEnd(processingEnd);
		setTravelStart(travelStart);
		setTravelEnd(travelEnd);
	}
	private LogisticsDetailsImpl(){}
	@Override
	public String toString() {
		return "LogisticsDetailsImpl [facilityName=" + facilityName + ", cost=" + cost + ", processingStart="
				+ processingStart + ", processingEnd=" + processingEnd + ", travelStart=" + travelStart + ", travelEnd="
				+ travelEnd + "]";
	}
	
	
}
