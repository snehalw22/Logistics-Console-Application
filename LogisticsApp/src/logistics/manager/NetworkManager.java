package logistics.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logistics.dto.NetworkDto;
import logistics.exceptions.FileNotFoundException;
import logistics.exceptions.InvalidArgumentException;
import logistics.exceptions.NoSuchDataFoundException;
import logistics.exceptions.UnexpectedNodeException;
import logistics.factory.NetworkLoaderFactory;
import logistics.properties.NetworkProperties;
import logistics.shortestPath.ShortestPath;

public final class NetworkManager {
	private static NetworkManager networkManager;
	private HashMap<String, TreeMap<String, Double>> networkList = new HashMap<>();

	private NetworkManager() throws InvalidArgumentException, FileNotFoundException, ParserConfigurationException,
			SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException {
		networkList = NetworkLoaderFactory.getNetwork(NetworkProperties.getInstance().getFileType())
				.loadNetwork(NetworkProperties.getInstance().getFileLocation());
	}

	public static NetworkManager getInstance() throws InvalidArgumentException, FileNotFoundException,
			ParserConfigurationException, SAXException, IOException, UnexpectedNodeException, NoSuchDataFoundException {
		if (networkManager == null) {
			synchronized (NetworkManager.class) {
				if (networkManager == null)
					networkManager = new NetworkManager();
			}
		}
		return networkManager;
	}

	public ArrayList<NetworkDto> getNetworkDto(String cityName) {
		ArrayList<NetworkDto> networkDtoList = new ArrayList<>();
		for (Entry<String, TreeMap<String, Double>> network : networkList.entrySet()) {
			if (network.getKey().equalsIgnoreCase(cityName)) {
				for (Entry<String, Double> neighbor : network.getValue().entrySet()) {
					NetworkDto networkDto = new NetworkDto(neighbor.getKey(), neighbor.getValue());
					networkDtoList.add(networkDto);
				}
				break;
			}
		}
		return networkDtoList;
	}

	public HashMap<String, ArrayList<NetworkDto>> getNetworkList() {
		HashMap<String, ArrayList<NetworkDto>> networkList = new HashMap<>();
		for (Entry<String, TreeMap<String, Double>> network : this.networkList.entrySet()) {
			ArrayList<NetworkDto> networkDtoList = new ArrayList<>();
			for (Entry<String, Double> neighbor : network.getValue().entrySet()) {
				NetworkDto networkDto = new NetworkDto(neighbor.getKey(), neighbor.getValue());
				networkDtoList.add(networkDto);

			}
			networkList.put(network.getKey(), networkDtoList);
		}
		return networkList;
	}

	public ArrayList<HashMap<ArrayList<String>, Double>> calculateShortestPath(ArrayList<String[]> cities) {
		ShortestPath shortestPath = new ShortestPath();
		ArrayList<HashMap<ArrayList<String>, Double>> citiesPath = new ArrayList<HashMap<ArrayList<String>, Double>>();
		for (String[] city : cities) {
			citiesPath.add(shortestPath.calculateShortestPath(city[0], city[1], getNetworkList()));
		}
		return citiesPath;
	}

}
