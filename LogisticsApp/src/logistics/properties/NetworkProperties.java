package logistics.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;

public class NetworkProperties {
	private volatile static NetworkProperties NetworkProperties;
	private Properties prop;
	
	private NetworkProperties() throws IOException{	
		prop = new Properties();
		InputStream input = null;
		input = this.getClass().getResourceAsStream("/logistics/resources/logisticsProperties.properties");
		prop.load(input);
		
	}
	public static NetworkProperties getInstance() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		if (NetworkProperties == null) {
			synchronized (NetworkProperties.class) {
				if (NetworkProperties == null)
					NetworkProperties = new NetworkProperties();
			}
		}
		return NetworkProperties;
	}
	
	public String getFileLocation()
	{
		return prop.getProperty("networkFileLocation");
	}
	public String getFileType()
	{
		return prop.getProperty("networkFileType");
	}
}
