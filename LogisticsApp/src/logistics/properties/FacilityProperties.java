
package logistics.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;

public class FacilityProperties {
	private volatile static FacilityProperties facilityProperties;
	private Properties prop;
	
	private FacilityProperties() throws IOException{	
		prop = new Properties();
		InputStream input = null;
		input = this.getClass().getResourceAsStream("/logistics/resources/logisticsProperties.properties");
		prop.load(input);
		
	}
	public static FacilityProperties getInstance() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		if (facilityProperties == null) {
			synchronized (FacilityProperties.class) {
				if (facilityProperties == null)
					facilityProperties = new FacilityProperties();
			}
		}
		return facilityProperties;
	}
	
	public String getFileLocation()
	{
		return prop.getProperty("facilityFileLocation");
	}
	public String getFileType()
	{
		return prop.getProperty("facilityFileType");
	}
	public String getFacilityType()
	{
		return prop.getProperty("facilityType");
	}
}
