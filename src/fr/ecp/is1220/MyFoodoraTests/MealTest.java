package fr.ecp.is1220.MyFoodoraTests;

import fr.ecp.is1220.MyFoodora.*;
import static org.junit.Assert.*;

import org.junit.*;

public class MealTest {
	
	private static MyFoodora myFoodora;
	private static Meal fullMeal;
	private static Meal halfMeal;
	
	@BeforeClass
	public static void importMyFoodora(){
		myFoodora = MyFoodora.loadMyFoodora();
		try{
			Restaurant restaurant = (Restaurant) myFoodora.login("hoki", "password");
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
		
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDish() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
