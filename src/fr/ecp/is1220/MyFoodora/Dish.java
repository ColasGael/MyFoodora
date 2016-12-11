package fr.ecp.is1220.MyFoodora;

public abstract class Dish implements FoodItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6534351611225696695L;
	/**
	 * the name of the dish
	 */
	private String name;
	/**
	 * the price of the dish
	 */
	private double price;
	/**
	 * the type of the dish : standard, vegetarian or gluten-free
	 */
	private String type; 
	/**
	 * the number of times the dish has been shipped 
	 */
	int counter = 0 ;
	
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
	
	@Override
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
	
	@Override
	public int getCounter(){
		return counter ;
	}
	
	@Override
	public void increaseCounter() {
		this.counter++ ;
	}
}
