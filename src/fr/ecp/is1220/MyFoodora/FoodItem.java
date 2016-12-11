package fr.ecp.is1220.MyFoodora;

/**
 * this interface enables us to deal with both Dish and Meal objects in the sorter
 */
public interface FoodItem extends java.io.Serializable {
	
	public double getPrice() ;
	public int getCounter() ;
	public void increaseCounter() ;
}
