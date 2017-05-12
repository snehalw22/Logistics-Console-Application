package logistics.dto;

import java.util.HashMap;

public class ScheduleDto {
	private HashMap<Integer, Integer> scheduleDtoList;
	
	
	public HashMap<Integer, Integer> getScheduleDtoList() {
		return scheduleDtoList;
	}


	public void setScheduleDtoList(HashMap<Integer, Integer> scheduleDtoList) {
		this.scheduleDtoList = scheduleDtoList;
	}


	public ScheduleDto(HashMap<Integer, Integer> scheduleDtoList) {
		super();
		this.scheduleDtoList = scheduleDtoList;
	}


	private ScheduleDto(){}
}
