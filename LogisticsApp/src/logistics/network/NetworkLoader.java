package logistics.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.NoSuchDataFoundException;
import logistics.exceptions.UnexpectedNodeException;

public interface NetworkLoader {

	public HashMap<String,TreeMap<String,Double>> loadNetwork(String fileName) throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException, SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException;
}
