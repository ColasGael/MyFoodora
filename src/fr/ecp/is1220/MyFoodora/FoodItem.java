package Group9_Project_IS1220_part1_Colas_Prabakaran;

/**
 * this interface enables us to deal with both Dish and Meal objects in the sorter
 */
public interface FoodItem extends java.io.Serializable {
	
	public double getPrice() ;
	public int getCounter() ;
	public void increaseCounter() ;
}
