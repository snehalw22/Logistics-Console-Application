package logistics.factory;

import java.util.ArrayList;

import logistics.exceptions.InvalidArgumentException;
import logistics.record.LogisticsDetails;
import logistics.record.LogisticsRecord;
import logistics.record.LogisticsRecordImpl;

public class LogisticsRecordFactory {
	
	private LogisticsRecordFactory() {
	}

	public static LogisticsRecord createRecord(String logisticsRecordType, String itemId, int quantity,int noOfSources,int firstArrivalDay,int lastArrivalDay , Double totalCost,
			ArrayList<LogisticsDetails> logisticsDetails ) throws InvalidArgumentException {

		if (logisticsRecordType.equalsIgnoreCase("LogisticsRecordImpl"))
			return new LogisticsRecordImpl(itemId, quantity, noOfSources, firstArrivalDay, lastArrivalDay, totalCost, logisticsDetails);
		else
			return null;

	}
}
