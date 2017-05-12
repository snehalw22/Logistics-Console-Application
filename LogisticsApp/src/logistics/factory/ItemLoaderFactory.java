package logistics.factory;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.item.ItemLoader;
import logistics.item.XMLItemLoader;


public class ItemLoaderFactory {

	private ItemLoaderFactory() {}
	
	public static ItemLoader getItems(String type) throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException
	{
	if (type.equalsIgnoreCase("xml"))
	{
		return new XMLItemLoader();
	}
	else return null;
	}
}
