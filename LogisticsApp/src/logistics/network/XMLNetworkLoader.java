package logistics.network;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.NoSuchDataFoundException;
import logistics.exceptions.UnexpectedNodeException;
import logistics.manager.FacilityManager;
import logistics.utilities.Utility;

public class XMLNetworkLoader implements NetworkLoader{
	
	@Override
	public HashMap<String, TreeMap<String,Double>> loadNetwork(String fileName) throws InvalidArgumentException,FileNotFoundException, ParserConfigurationException, SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException {
		 if(fileName==null||fileName.isEmpty())	 
			 throw new InvalidArgumentException("Invalid (empty or null) value passed to location 'loadNetwork'");
		
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();

         File xml = new File(fileName);
         if (!xml.exists()) {
           
        	 throw new FileNotFoundException("File not found for the network at the location given for 'loadNetwork'");
         }
         Document doc = db.parse(xml);
         doc.getDocumentElement().normalize();

         NodeList networkEntries = doc.getDocumentElement().getChildNodes();
         HashMap<String, TreeMap<String,Double>> networkList = new HashMap<>();
         for (int i = 0; i < networkEntries.getLength(); i++) {
             if (networkEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                 continue;
             }
             
             String entryName = networkEntries.item(i).getNodeName();
             if (!entryName.equals("Network")) {
            	 throw new UnexpectedNodeException("Unexpected Node found in the  XML "+fileName);
              
             }
             
             // Get a node attribute
             NamedNodeMap aMap = networkEntries.item(i).getAttributes();
             String facilityName = aMap.getNamedItem("facilityName").getNodeValue();
             if(!FacilityManager.getInstance().ifExists(facilityName))              
             {
            	 throw new NoSuchDataFoundException("The Facility Name "+facilityName+"given to method 'loadNetwork' does not exist.");
             }
             // Get a named nodes
             Element elem = (Element) networkEntries.item(i);
          
          // Get all nodes named "Inventory" - there can be 0 or more
             TreeMap<String,Double> neighbors = new TreeMap<>();
             NodeList neighborList = elem.getElementsByTagName("Neighbor");
             for (int j = 0; j < neighborList.getLength(); j++) {
                 if (neighborList.item(j).getNodeType() == Node.TEXT_NODE) {
                     continue;
                 }

                 entryName = neighborList.item(j).getNodeName();
                 if (!entryName.equals("Neighbor")) {
                	 throw new UnexpectedNodeException("Unexpected Node found in the  XML "+fileName);
                 }
                 elem = (Element) neighborList.item(j);
                 String neighborName = elem.getElementsByTagName("neighborName").item(0).getTextContent();
                 if(!FacilityManager.getInstance().ifExists(neighborName))              
                 {
                	 throw new NoSuchDataFoundException("The Neighbor Name "+neighborName+ " for Facility Name "+facilityName+" in the  XML "+fileName+" does not exist.");
                 }
                 if(elem.getElementsByTagName("distance").item(0).getTextContent().trim().length()==0)
                 {
                 	throw new NumberFormatException("The distance of "+neighborName+ " for "+facilityName+" is null in 'loadNetwork' ");
                 }
                 if(Utility.isNumeric(elem.getElementsByTagName("distance").item(0).getTextContent().trim())==false)
                 {
                	 throw new InvalidArgumentException("The distance of "+neighborName+"  for "+facilityName+"  is invalid in 'loadNetwork'");
                 }
                 double distance = Integer.parseInt(elem.getElementsByTagName("distance").item(0).getTextContent());
                 neighbors.put(neighborName, distance);
             }
             networkList.put(facilityName, neighbors);
	}
         
         return networkList;
	}
	
}
