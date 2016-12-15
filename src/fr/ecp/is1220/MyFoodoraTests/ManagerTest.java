package fr.ecp.is1220.MyFoodoraTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.ecp.is1220.MyFoodora.*;
import java.util.ArrayList;
import java.util.Calendar;

public class ManagerTest {

	private static MyFoodora myFoodora;
	
	private static Manager manager;

	@BeforeClass
	public static void importMyFoodora(){
		try{
			myFoodora = MyFoodora.loadMyFoodora();
			manager = (Manager)myFoodora.findUserByUniqueID(0);
		}catch(Exception e){
		}
	}
	
	@Test
	public void testRemoveUser() throws UserNotFoundException{
		User userToRemove = myFoodora.findUserByUniqueID(1);
		//the manager remove the user
		manager.removeUser(1);
		//we check that the user has been removed
		ArrayList<User> users = myFoodora.getUsers();
		
		assertFalse(users.contains(userToRemove));
	}

	@Test
	public void testActivateUser() throws UserNotFoundException{
		User user = myFoodora.findUserByUniqueID(1);
		user.setActivated(false);
		//the manager activates the deactivated user
		manager.activateUser(1);
		assertTrue(user.isActivated());
	}

	@Test
	public void testDeactivateUser() throws UserNotFoundException{
		User user = myFoodora.findUserByUniqueID(1);
		user.setActivated(true);
		//the manager deactivates the activated user
		manager.deactivateUser(1);
		assertFalse(user.isActivated());
	}

	@Test
	public void testTotalIncome() {
		//today
		Calendar calendar2 = Calendar.getInstance();
		//one month ago
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MONTH, -1);
		//computes the total income between today and a month ago
		double totalIncome = manager.totalIncome(calendar1, calendar2);
		assertTrue(totalIncome > 0);
	}

	@Test
	public void testTotalProfit() {
		//today
		Calendar calendar2 = Calendar.getInstance();
		//one month ago
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MONTH, -1);
		//computes the total income between today and a month ago
		double totalProfit = manager.totalProfit(calendar1, calendar2);
		assertTrue(totalProfit > 0);
	}

	@Test
	public void testAverageIncomePerCostumer() {
		//today
		Calendar calendar2 = Calendar.getInstance();
		//one month ago
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MONTH, -1);
		//computes the total income between today and a month ago
		double averageIncomePerCostumer = manager.averageIncomePerCostumer(calendar1, calendar2);
		double totalIncome = manager.totalIncome(calendar1, calendar2);

		//as there is only one customer
		assertTrue((averageIncomePerCostumer==totalIncome));
	}

	@Test
	public void testMostSellingRestaurant() {
		Restaurant mostSellingRestaurant = manager.mostSellingRestaurant();
		System.out.println(mostSellingRestaurant);
		
		assertEquals("Le Hoki", mostSellingRestaurant.getName());
	}

	@Test
	public void testLeastSellingRestaurant() {
		Restaurant leastSellingRestaurant = manager.leastSellingRestaurant();
		System.out.println(leastSellingRestaurant);	
		
		assertEquals("Dominos", leastSellingRestaurant.getName());
	}

	@Test
	public void testMostActiveCourier() {
		Courier mostActiveCourier = manager.mostActiveCourier();
		System.out.println(mostActiveCourier);
		
		assertEquals("Peter", mostActiveCourier.getName());
	}

	@Test
	public void testLeastActiveCourier() {
		Courier leastActiveCourier = manager.leastActiveCourier();
		System.out.println(leastActiveCourier);
		
		assertEquals("Toby", leastActiveCourier.getName());
	}
}
