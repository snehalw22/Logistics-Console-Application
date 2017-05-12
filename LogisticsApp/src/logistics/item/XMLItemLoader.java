package logistics.item;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
import logistics.exceptions.UnexpectedNodeException;
import logistics.factory.ItemFactory;
import logistics.utilities.Utility;

public class XMLItemLoader implements ItemLoader{
	
	@Override
	public ArrayList<Item> loadItems(String fileName,String itemType) throws InvalidArgumentException,FileNotFoundException, ParserConfigurationException, SAXException, IOException, UnexpectedNodeException {
		System.out.println(fileName);
		if(fileName==null||fileName.isEmpty())	 
			 throw new InvalidArgumentException("Invalid (empty or null) value passed to location 'loadItems'");
		
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();

         File xml = new File(fileName);
         if (!xml.exists()) {
           
        	 throw new FileNotFoundException("File not found for the items at the location given for 'loadItems'");
         }
         Document doc = db.parse(xml);
         doc.getDocumentElement().normalize();

         NodeList ItemEntries = doc.getDocumentElement().getChildNodes();
         ArrayList<Item> itemlist = new ArrayList<Item>();
         for (int i = 0; i < ItemEntries.getLength(); i++) {
             if (ItemEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                 continue;
             }
             
             String entryName = ItemEntries.item(i).getNodeName();
             if (!entryName.equals("Item")) {
            	 throw new UnexpectedNodeException("Unexpected Node found in the  XML given for 'loadItems'");
              
             }
             
             // Get a node attribute
             NamedNodeMap aMap = ItemEntries.item(i).getAttributes();
             String identifier = aMap.getNamedItem("identifier").getNodeValue();

             // Get a named nodes
             Element elem = (Element) ItemEntries.item(i);
             if(elem.getElementsByTagName("price").item(0)==null)
             {
            	 throw new InvalidArgumentException("Price is null in the  XML given for 'loadItems'");

             }
             if(elem.getElementsByTagName("price").item(0).getTextContent().trim().length()==0)
             {
             	throw new NumberFormatException("The price of "+identifier+ " is null in 'loadItems' ");
             }
             if(Utility.isNumeric(elem.getElementsByTagName("price").item(0).getTextContent().trim())==false)
             {
            	 throw new InvalidArgumentException("The price of "+identifier+" is invalid in 'loadItems'");
             }
             double price = Double.parseDouble(elem.getElementsByTagName("price").item(0).getTextContent());
             //System.out.println("id "+identifier +" price "+price);
             Item item = ItemFactory.createItem(itemType,identifier,price);
             itemlist.add(item);
	}
         Collections.sort(itemlist);
         return itemlist;
	}

}
