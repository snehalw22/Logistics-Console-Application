package logistics.inventory;

import java.util.Map;
import java.util.TreeMap;

import logistics.exceptions.InvalidArgumentException;

public class InventoryImpl implements Inventory{

	private TreeMap<String, Integer> inventoryList;

	public TreeMap<String, Integer> getInventoryList() {
		return inventoryList;
	}

	private void setInventoryList(TreeMap<String, Integer> inventoryList) throws InvalidArgumentException {
		if(inventoryList.size()>0)
		{
		for (Map.Entry<String, Integer> entry : inventoryList.entrySet()) {
			if(entry.getKey()==null||entry.getKey().isEmpty())	 
				 throw new InvalidArgumentException("Invalid (empty or null) value passed to itemID of InventoryImpl 'setItemId'");
			if (entry.getValue() <= 0)
				throw new InvalidArgumentException("Invalid available (zero or less) value is passed to ScheduleImpl");
		}
		}
		this.inventoryList = inventoryList;
	}
	
	private InventoryImpl() {}

	public InventoryImpl(TreeMap<String, Integer> inventoryList) throws InvalidArgumentException {
		setInventoryList(inventoryList);
	}

	@Override
	public String toString() {
		return "InventoryImpl [inventoryList=" + inventoryList + "]";
	}	
}
