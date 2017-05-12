package logistics.factory;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.facility.FacilityLoader;
import logistics.facility.XMLFacilityLoader;

public class FacilityLoaderFactory {

	private FacilityLoaderFactory() {}
	
	public static FacilityLoader getFacilityItems(String type) throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException
	{
	if (type.equalsIgnoreCase("xml"))
	{
		return new XMLFacilityLoader();
	}
	else return null;
	}
}
