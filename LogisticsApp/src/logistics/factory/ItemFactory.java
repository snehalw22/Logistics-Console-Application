package logistics.factory;

import logistics.exceptions.InvalidArgumentException;
import logistics.item.Item;
import logistics.item.ItemImpl;

public class ItemFactory {
	
	private ItemFactory() {}
	
	public static Item createItem(String type,String identifier,double price) throws InvalidArgumentException
	{
	if (type.equalsIgnoreCase("ItemImpl"))
	{
			return new ItemImpl(identifier,price);
	}
	else return null;
	}
}
