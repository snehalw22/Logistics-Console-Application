package logistics.factory;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.network.NetworkLoader;
import logistics.network.XMLNetworkLoader;

public class NetworkLoaderFactory {
private NetworkLoaderFactory() {}
	
	public static NetworkLoader getNetwork(String type) throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException
	{
	if (type.equalsIgnoreCase("xml"))
	{
		return new XMLNetworkLoader();
	}
	else return null;
	}
}
