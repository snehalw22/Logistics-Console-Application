package logistics.shortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import logistics.dto.NetworkDto;

public class ShortestPath {

	public HashMap<ArrayList<String>, Double> calculateShortestPath(String fromCityName,String toCityName, HashMap<String, ArrayList<NetworkDto>> networkList) {
		HashMap<String, NetworkDto> seenList = new HashMap<>();
		HashMap<String, NetworkDto> unseenList = new HashMap<>();
		unseenList.put(fromCityName, new NetworkDto(fromCityName, 0));
		String seenNode;
		String leastDistNode = fromCityName;
		do {
			seenList.put(leastDistNode, unseenList.get(leastDistNode));													
			unseenList.remove(leastDistNode);
			seenNode = leastDistNode;
			for (NetworkDto networkDto : networkList.get(seenNode))
			{
				if (!seenList.containsKey(networkDto.getNeighborName())) {
					if (unseenList.containsKey(networkDto.getNeighborName()))																			
					{
						if (unseenList.get(networkDto.getNeighborName())
								.getDistance() > (networkDto.getDistance() + seenList.get(seenNode).getDistance()))
						{
							unseenList.put(networkDto.getNeighborName(), new NetworkDto(seenNode,
									networkDto.getDistance() + seenList.get(seenNode).getDistance()));
						}
					} else {
						unseenList.put(networkDto.getNeighborName(), new NetworkDto(seenNode,
								networkDto.getDistance() + seenList.get(seenNode).getDistance()));
					}
				}
			}
			leastDistNode = getLeastDistNode(unseenList);
		} while (!(unseenList.size() == 0));
		
		String midCity=toCityName;
		ArrayList<String> path= new ArrayList<String>();
		path.add(midCity);
		do {
		path.add(seenList.get(midCity).getNeighborName());
		midCity=seenList.get(midCity).getNeighborName();
		}while (!midCity.equalsIgnoreCase(fromCityName));
		Collections.reverse(path);
		HashMap<ArrayList<String>, Double> result = new HashMap<>();
		result.put(path, seenList.get(toCityName).getDistance());
		return result;
	}

	public String getLeastDistNode(HashMap<String, NetworkDto> unseenList) {
		String leastNode = "";
		double dist = Double.POSITIVE_INFINITY;
		for (Entry<String, NetworkDto> network : unseenList.entrySet())

		{
			if (network.getValue().getDistance() < dist) {
				leastNode = network.getKey();
				dist = network.getValue().getDistance();
			}
		}
		return leastNode;

	}
	public void printData(HashMap<String, NetworkDto> seenList)
	{
		for (Entry<String, NetworkDto> network : seenList.entrySet()) {
			System.out.println(network.getKey() + "  " + network.getValue().toString());
	}
	}
}
