package fr.ecp.is1220.MyFoodora;

import java.io.Serializable;

/**
 *this factory class enables us to deals with different kinds of SorterFoodItem
 */
public class SorterFoodItemFactory implements Serializable {

	private static final long serialVersionUID = -9082092467102238018L;

	public SorterFoodItemFactory() {
	}
	
	/**
	 * creates a food item sorter : 
	 * 		sorting according to the number of time the items have been picked in completed orders
	 * 		sorting according to their prices
	 * @param sorterFoodItemName : "counter" or "price"
	 * @return sorterFoodItem : the SorterFoodItem chosen
	 */
	public SorterFoodItem chooseSorterFoodItem (String sorterFoodItemName) {
		SorterFoodItem sorterFoodItem = null;
		switch(sorterFoodItemName){
			case("counter"):
				sorterFoodItem = new SorterCounter();
				break;
			case("price"):
				sorterFoodItem = new SorterPrice();
				break;
		}
		return (sorterFoodItem);
	}
}
