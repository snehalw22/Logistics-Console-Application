package logistics.item;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.UnexpectedNodeException;

public interface ItemLoader {

	public ArrayList<Item> loadItems(String fileName,String itemType) throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException, UnexpectedNodeException;
	
	
}
