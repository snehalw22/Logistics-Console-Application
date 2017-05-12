package logistics.inventory;

import java.util.TreeMap;

import logistics.exceptions.InvalidArgumentException;

public interface Inventory{

	public TreeMap<String, Integer> getInventoryList();
}
