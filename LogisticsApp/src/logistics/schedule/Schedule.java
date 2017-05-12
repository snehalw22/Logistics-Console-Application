package logistics.schedule;

import java.util.HashMap;

import logistics.exceptions.InvalidArgumentException;

public interface Schedule{
	public HashMap<Integer, Integer> getSchedule();
	public  void addToSchedule(Integer day,Integer quantity) throws InvalidArgumentException;
}
