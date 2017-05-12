package logistics.factory;

import java.util.TreeMap;

import logistics.exceptions.InvalidArgumentException;
import logistics.inventory.Inventory;
import logistics.inventory.InventoryImpl;

public class InventoryFactory {
	private InventoryFactory() {}

	public static Inventory createInventory(String inventoryType,TreeMap<String, Integer> inventories) throws InvalidArgumentException {

		if (inventoryType.equalsIgnoreCase("InventoryImpl"))
			return new InventoryImpl(inventories);
		else
			return null;
	}
}
