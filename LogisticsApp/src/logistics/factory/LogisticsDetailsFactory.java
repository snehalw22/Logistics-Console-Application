package logistics.factory;

import logistics.exceptions.InvalidArgumentException;
import logistics.record.LogisticsDetails;
import logistics.record.LogisticsDetailsImpl;

public class LogisticsDetailsFactory {
	private LogisticsDetailsFactory() {
	}

	public static LogisticsDetails createLogisticsDetail(String logisticsDetailType, String facilityName, double cost,int processingStart,int processingEnd,int travelStart , int travelEnd) throws InvalidArgumentException {

		if (logisticsDetailType.equalsIgnoreCase("LogisticsDetailsImpl"))
			return new LogisticsDetailsImpl(facilityName, cost, processingStart, processingEnd, travelStart, travelEnd);	
		else
			return null;

	}
}
