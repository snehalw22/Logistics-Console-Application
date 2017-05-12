package logistics.dto;

import java.util.TreeMap;

public class InventoryDto{
	
	private TreeMap<String, Integer> inventoryDtoList;

	public TreeMap<String, Integer> getInventoryDtoList() {
		return inventoryDtoList;
	}

	public void setInventoryDtoList(TreeMap<String, Integer> inventoryDtoList) {
		this.inventoryDtoList = inventoryDtoList;
	}

	public InventoryDto(TreeMap<String, Integer> inventoryDtoList) {
		this.inventoryDtoList = inventoryDtoList;
	}

	private InventoryDto(){}
}
