package logistics.factory;

import java.util.HashMap;

import logistics.exceptions.InvalidArgumentException;
import logistics.schedule.Schedule;
import logistics.schedule.ScheduleImpl;

public class ScheduleFactory {
	private ScheduleFactory() {}

	public static Schedule createSchedule(String scheduleType,HashMap<Integer, Integer> schedule) throws InvalidArgumentException {

		if (scheduleType.equalsIgnoreCase("ScheduleImpl"))
			return new ScheduleImpl(schedule);
		else
			return null;
	}
}
