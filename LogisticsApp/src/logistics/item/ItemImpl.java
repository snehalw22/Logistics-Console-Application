package logistics.item;

import logistics.exceptions.InvalidArgumentException;

public class ItemImpl implements Item{

	private String identifier;
	private double price;
	
	@Override
	public String getIdentifier() {
		return identifier;
	}

	private void setIdentifier(String identifier) throws InvalidArgumentException {
	 if(identifier==null||identifier.isEmpty())	 
		 throw new InvalidArgumentException("Invalid (empty or null) value passed to ItemImpl 'setIdentifier'");
	 else
		 this.identifier=identifier;
	}

	@Override
	public double getPrice() {
		return price;
	}

	
	private void setPrice(double price) throws InvalidArgumentException {	
		  if (price <= 0.0)
	            throw new InvalidArgumentException("Invalid price (zero or less) value is passed to ItemImpl");
		  this.price = price;
		
	}

	@Override
	public String toString() {
		return "ItemImpl [identifier=" + identifier + ", price=" + price + "]";
	}

	public ItemImpl(String identifier, double price) throws InvalidArgumentException {
		setIdentifier(identifier);
		setPrice(price);
	}
	
	private ItemImpl(){}
	
	@Override
	public int compareTo(Item item) {
		return this.getIdentifier().compareTo(item.getIdentifier());

	}
}
