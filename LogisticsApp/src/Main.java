import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.NoSuchDataFoundException;
import logistics.exceptions.UnexpectedNodeException;
import logistics.manager.FacilityManager;
import logistics.manager.ItemManager;
import logistics.manager.NetworkManager;

public class Main {

	public static void main(String args[]) throws InvalidArgumentException, FileNotFoundException,
			ParserConfigurationException, SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException {
		
		
		//All the properties are configurable in the logisticsProperties.properties of package logisticsProperties
		ItemManager.getInstance();
		FacilityManager.getInstance();
		NetworkManager.getInstance();
		PhaseOneOutputGenerator phaseOneOutputGenerator = new PhaseOneOutputGenerator();
		//phaseOneOutputGenerator.printItemCatalog();
		//phaseOneOutputGenerator.printFacility();
		phaseOneOutputGenerator.shortestPath();		
		//PhaseTwoOutputGenerator phaseTwoOutputGenerator= new PhaseTwoOutputGenerator();
		//phaseTwoOutputGenerator.processOrder();		
		//phaseOneOutputGenerator.printFacility();
	}

}
