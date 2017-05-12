package logistics.manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.dto.ItemDto;
import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.UnexpectedNodeException;
import logistics.factory.ItemLoaderFactory;
import logistics.item.Item;
import logistics.properties.ItemProperties;

public final class ItemManager {

	private static ItemManager itemManager;
	
	private  ArrayList<Item> itemList;
	
	//constructor
	private ItemManager() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException, UnexpectedNodeException {
		ItemProperties itemProperties= ItemProperties.getInstance();
		itemList = ItemLoaderFactory.getItems(itemProperties.getFileType()).loadItems(itemProperties.getFileLocation(),itemProperties.getItemType());
	}

	public static ItemManager getInstance() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException, UnexpectedNodeException {
		if (itemManager == null) {
			synchronized (ItemManager.class) {
				if (itemManager == null)
					itemManager = new ItemManager();
			}
		}
		return itemManager;
	}
	public ArrayList<ItemDto> getItemDtoList()
	{
		ArrayList<ItemDto> itemDtoList= new ArrayList<>();
		for(Item item: itemList)
		{
			itemDtoList.add(new ItemDto(item.getIdentifier(),item.getPrice()));
		}
		return itemDtoList;
	}
	public boolean ifExists(String itemName)
	{
		for(Item item:itemList)
		{
			if(item.getIdentifier().equals(itemName))
			return true;
		}
		
		return false;
		
	}
	public double getPrice(String itemID)
	{
		for(Item item:itemList)
		{
			if(item.getIdentifier().equalsIgnoreCase(itemID))
			{
				return item.getPrice();
			}
		}
		return 0;
	}
}