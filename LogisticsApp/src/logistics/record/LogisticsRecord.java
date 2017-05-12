package logistics.record;

import java.util.ArrayList;

public interface LogisticsRecord {

	public String getItemId();
	public int getQuantity() ;
	public int getNoOfSources() ;
	public int getFirstArrivalDay() ;
	public int getLastArrivalDay();
	public double getTotalCost() ;
	public ArrayList<LogisticsDetails> getLogisticsDetails() ;
}
