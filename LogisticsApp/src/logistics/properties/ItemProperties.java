package logistics.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;

public class ItemProperties {

	private volatile static ItemProperties itemProperties;
	private Properties prop;
	
	private ItemProperties() throws IOException{	
		prop = new Properties();
		InputStream input = null;
		input = this.getClass().getResourceAsStream("/logistics/resources/logisticsProperties.properties");
		prop.load(input);
		
	}
	public static ItemProperties getInstance() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		if (itemProperties == null) {
			synchronized (ItemProperties.class) {
				if (itemProperties == null)
					itemProperties = new ItemProperties();
			}
		}
		return itemProperties;
	}
	
	public String getFileLocation()
	{
		return prop.getProperty("itemFileLocation");
	}
	public String getFileType()
	{
		return prop.getProperty("itemFileType");
	}
	public String getItemType()
	{
		return prop.getProperty("itemType");
	}
}
