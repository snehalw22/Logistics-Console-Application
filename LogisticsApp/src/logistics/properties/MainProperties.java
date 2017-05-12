package logistics.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;

public class MainProperties {

	private volatile static MainProperties mainProperties;
	private Properties prop;
	
	private MainProperties() throws IOException{	
		prop = new Properties();
		InputStream input = null;
		input = this.getClass().getResourceAsStream("/logistics/resources/logisticsProperties.properties");
		prop.load(input);
		
	}
	public static MainProperties getInstance() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		if (mainProperties == null) {
			synchronized (MainProperties.class) {
				if (mainProperties == null)
					mainProperties = new MainProperties();
			}
		}
		return mainProperties;
	}
	
	public double getHrsPerDay()
	{
		return Double.parseDouble(prop.getProperty("hrsPerDay"));
	}
	public double getMilesPerDay()
	{
		return Double.parseDouble(prop.getProperty("milesPerDay"));
	}
	public int getScheduleDays()
	{
		return Integer.parseInt(prop.getProperty("sheduleDays"));
	}
	public int getTravelCostPerDay()
	{
	return Integer.parseInt(prop.getProperty("travelCostPerDay"));
	}
}
