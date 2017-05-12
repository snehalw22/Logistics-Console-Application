package logistics.dto;

import java.io.Serializable;

public class ItemDto implements Serializable{

	private String identifier;
	private double price;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ItemDto(String identifier, double price) {
		this.identifier = identifier;
		this.price = price;
	}
	private ItemDto(){}
	@Override
	public String toString() {
		return "ItemDto [identifier=" + identifier + ", price=" + price + "]";
	}
	
}
