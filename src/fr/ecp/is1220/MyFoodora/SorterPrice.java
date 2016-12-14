package fr.ecp.is1220.MyFoodora;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * sorts the list according to the area of figures (in descending order)
 */
public class SorterPrice implements SorterFoodItem,Serializable{

	private static final long serialVersionUID = -7504058433135999177L;

	/**
	 * looks over the list of foodItems looking for the biggest price
	 * @param foodItems - list of food items
	 * @return the foodItem with the biggest price
	 */
	public FoodItem maxFoodItem(ArrayList<FoodItem> foodItems){
		FoodItem maxFoodItem = null; 
		double maxPrice = 0 ;
		
		if(foodItems.isEmpty())
			return null;
		else{
			for(FoodItem foodItem:foodItems){
				if(foodItem.getPrice()>=maxPrice){
					maxPrice = foodItem.getPrice() ;
					maxFoodItem = foodItem ;
				}	
			}
		}
		return maxFoodItem; 
	}
	
	/**
	 * sorts the list according to the price of food items (in descending order)
	 */
	@Override
	public ArrayList<FoodItem> sort(ArrayList<FoodItem> foodItems){
		ArrayList<FoodItem> FoodItemsCopy = (ArrayList<FoodItem>) foodItems.clone(); 
		ArrayList<FoodItem> sortedFoodItems = new ArrayList<FoodItem>();
		
		if(foodItems.size()<=1)
			return foodItems;
		else{
			FoodItem maxFoodItem;
			while(FoodItemsCopy.size()>0){
				maxFoodItem = this.maxFoodItem(FoodItemsCopy);
				sortedFoodItems.add(maxFoodItem);
				FoodItemsCopy.remove(maxFoodItem);
			}
			return sortedFoodItems;
		}
	}
}
