package fr.ecp.is1220.MyFoodoraTests;

import fr.ecp.is1220.MyFoodora.*;
import static org.junit.Assert.*;

import org.junit.*;

public class MealTest {

	private static MyFoodora myFoodora;
	
	@BeforeClass
	public static void importMyFoodora(){
		myFoodora = MyFoodora.loadMyFoodora();
	}
	
	@Test
	public void testMealString() {
		try{
			Restaurant restaurant = (Restaurant) myFoodora.login("hoki", "password");
			Meal meal = restaurant.findMealByName("B2");
			System.out.println(meal);
		}catch(Exception e){}	
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
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMainDish() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMainDish() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDiscountFactor() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
