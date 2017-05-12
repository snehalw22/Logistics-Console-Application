package logistics.dto;

public class NetworkDto {
	private String neighborName;
	private double distance;
	
	public String getNeighborName() {
		return neighborName;
	}
	public double getDistance() {
		return distance;
	}
	
	private NetworkDto() {
	}
	public NetworkDto(String neighborName, double distance) {

		this.neighborName = neighborName;
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "NetworkDto [neighborName=" + neighborName + ", distance=" + distance + "]";
	}
	
	
}
