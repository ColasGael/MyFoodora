package fr.ecp.is1220.MyFoodora;

public abstract class Dish extends FoodItem {

	private static final long serialVersionUID = 6534351611225696695L;
	/**
	 * the name of the dish
	 */
	private String name;
	/**
	 * the type of the dish : standard, vegetarian or gluten-free
	 */
	private String type; 
	
	/**
	 * creates a dish object of a given price and type
	 * @param name : the name of the dish
	 * @param price : the price of the dish
	 * @param type : the type of the dish : standard, vegetarian or gluten-free
	 */
	public Dish(String name, double price, String type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	

	@Override
	public String toString() {
		return "[name=" + name + ", price=" + price + ", type=" + type + ", counter=" + counter + "]";
	}
	
	
}
