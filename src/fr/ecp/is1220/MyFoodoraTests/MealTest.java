package fr.ecp.is1220.MyFoodoraTests;

import fr.ecp.is1220.MyFoodora.*;
import static org.junit.Assert.*;

import org.junit.*;

public class MealTest {
	
	private static MyFoodora myFoodora;
	private static Meal fullMeal;
	private static Meal halfMeal;
	private static Restaurant restaurant;
	
	@BeforeClass
	public static void importMyFoodora(){
		myFoodora = MyFoodora.loadMyFoodora();
		try{
			restaurant = (Restaurant) myFoodora.login("hoki", "password");
			halfMeal = restaurant.findMealByName("B1");
			fullMeal = restaurant.findMealByName("M3");
		}catch(Exception e){}
	}
	
	@Test
	public void testMealString() {
		System.out.println(halfMeal);
		System.out.println(fullMeal);
	}	

	@Test
	public void testComputePrice() {
		double price = fullMeal.computePrice();
		assertEquals("the price of meal M3 is 9.5€", price, 9.5, 0);
	}

	@Test
	public void testUpdate() throws FoodItemNotFoundException {
		restaurant.setMealOfTheWeek("M3");
		double price = fullMeal.getPrice();
		assertEquals("the price of meal M3 is 9€ when meal of the week",price, 9., 0);
	}
	
	@Test
	public void testAddDish() throws NoPlaceInMealException, FoodItemNotFoundException{
		Dish dish = restaurant.findDishByName("maki thon");
		FullMeal fullMeal = new FullMeal("S3");
		fullMeal.addDish(dish);
		assertEquals("the dish has been added to the meal", fullMeal.getMainDish(), dish);
	}

	@Test(expected = NoPlaceInMealException.class)
	public void testAddDishWhenMealFull() throws NoPlaceInMealException, FoodItemNotFoundException{
		Dish dish = restaurant.findDishByName("maki thon");
		halfMeal.addDish(dish);
	}
}
