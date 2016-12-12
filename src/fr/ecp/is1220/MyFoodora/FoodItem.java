package fr.ecp.is1220.MyFoodora;

/**
 * this abstract class enables us to deal with both Dish and Meal objects in the sorter
 */
public abstract class FoodItem implements java.io.Serializable {

	private static final long serialVersionUID = -4013771930891098924L;
	/**
	 * the total price of the food item
	 */
	protected double price = 0;
	/**
	 * the number of times the food item has been shipped 
	 */
	protected int counter = 0 ;
	
	public int getCounter(){
		return counter ;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
	
	public void increaseCounter() {
		this.counter++ ;
	}
}
