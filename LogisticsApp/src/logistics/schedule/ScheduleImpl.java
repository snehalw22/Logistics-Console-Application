package logistics.schedule;

import java.util.HashMap;
import java.util.Map;

import logistics.exceptions.InvalidArgumentException;

public class ScheduleImpl implements Schedule {

	private HashMap<Integer, Integer> schedule;

	public HashMap<Integer, Integer> getSchedule() {
		return schedule;
	}

	private void setSchedule(HashMap<Integer, Integer> schedule) throws InvalidArgumentException {
		if(schedule.size()>0)
		{
		for (Map.Entry<Integer, Integer> entry : schedule.entrySet()) {
			if (entry.getKey() <= 0)
				throw new InvalidArgumentException("Invalid day (zero or less) value is passed to ScheduleImpl");
			if (entry.getValue() <= 0)
				throw new InvalidArgumentException("Invalid available (zero or less) value is passed to ScheduleImpl");
		}
		}
		this.schedule = schedule;
	}
	
	private ScheduleImpl(){}

	public ScheduleImpl(HashMap<Integer, Integer> schedule) throws InvalidArgumentException {		
		setSchedule(schedule);
	}

	@Override
	public String toString() {
		return "ScheduleImpl [schedule=" + schedule + "]";
	}

	@Override
	public void addToSchedule(Integer day, Integer quantity) throws InvalidArgumentException {
		if (day <= 0)
			throw new InvalidArgumentException("Invalid day (zero or less) value is passed to ScheduleImpl");
		if (quantity < 0)
			throw new InvalidArgumentException("Invalid available (zero or less) value is passed to ScheduleImpl");
		
		schedule.put(day, quantity);
	}
	
	
}
