package fr.ecp.is1220.MyFoodoraTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.ecp.is1220.MyFoodora.*;

public class RestaurantTest {
	
	private static MyFoodora myFoodora;
	/**
	 * 	we create an example of restaurant that will be used in the following tests
	*/

	private static Restaurant restaurant;

	@BeforeClass
	public static void importMyFoodora(){
		myFoodora = MyFoodora.loadMyFoodora();
		try{
			restaurant = (Restaurant) myFoodora.login("hoki", "password");
		}catch(Exception e){}
	}
	
	@Test (expected = FoodItemNotFoundException.class)
	public void testFindDishByName() throws FoodItemNotFoundException {
		restaurant.addDish("mainDish", "maki crevette", 4.5, "standard");
		//when the user misspells a word for example : "crevette" != "crevete"
		Dish dish = restaurant.findDishByName("maki crevete");
	}

	@Test
	public void testAddDish() throws FoodItemNotFoundException {
		restaurant.addDish("mainDish", "maki avocat", 4.5, "standard");
		Dish dish = restaurant.findDishByName("maki avocat");
		System.out.println(dish);
	}

	@Test (expected = FoodItemNotFoundException.class)
	public void testRemoveDish() throws FoodItemNotFoundException {
		//we add a new dish to the menu of the restaurant (tested above)
		restaurant.addDish("mainDish", "maki avocat", 4.5, "standard");
		Dish dish = restaurant.findDishByName("maki avocat");
		//we remove this dish
		restaurant.removeDish("maki avocat");
	}

	@Test
	public void testFindMealByName() throws FoodItemNotFoundException {
		restaurant.addMeal("full", "B2");
		//when the user misspells a word for example : "B2" != "b2"
		Meal meal = restaurant.findMealByName("b2");
	}

	@Test
	public void testAddMeal() throws FoodItemNotFoundException {
		restaurant.addMeal("full", "B2");
		Meal meal = restaurant.findMealByName("B2");
		System.out.println(meal);
	}

	@Test
	public void testRemoveMeal() throws FoodItemNotFoundException {
		//we add a new meal to the menu of the restaurant (tested above)
		restaurant.addMeal("full", "B2");
		Meal meal = restaurant.findMealByName("B2");
		//we remove this meal
		restaurant.removeDish("B2");	}

	@Test
	public void testAddDish2Meal() throws FoodItemNotFoundException, NoPlaceInMealException {
		//we add a new meal to the menu of the restaurant (tested above)
		restaurant.addMeal("full", "B2");
		Meal meal = restaurant.findMealByName("B2");
		//we find dishes to add to the meal
		Dish mainDish = restaurant.findDishByName("brochettes boeuf"); 
		Dish dessert = restaurant.findDishByName("litchee");
		//we add them to the empty meal
		restaurant.addDish2Meal("B2", "brochettes boeuf");
		restaurant.addDish2Meal("B2", "litchee");
		
		System.out.println(meal);
	}

	@Test
	public void testDisplaySortedFoodItems() {
		//we sort the food items of the menu according to their price
		restaurant.chooseShippedOrderPolicy("price");
		//we display the sorted dish
		restaurant.displaySortedFoodItems("dish");
		//we display the sorted meals
		restaurant.displaySortedFoodItems("meal");
	}

	@Test
	public void testDisplayMenu() {
		
	}
}
