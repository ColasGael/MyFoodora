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
		fail("Not yet implemented");
	}

	@Test
	public void testTotalProfit() {
		//today
		Calendar calendar2 = Calendar.getInstance();
		//one month ago
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MONTH, -1);
		//computes the total income between today and a month ago
		double totalIncome = manager.totalIncome(calendar1, calendar2);
		
		System.out.println(totalIncome);
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
		
		System.out.println(averageIncomePerCostumer);
	}

	@Test
	public void testMeetTargetProfit() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDeliveryPolicy() {
		fail("Not yet implemented");
	}

	@Test
	public void testMostSellingRestaurant() {
		fail("Not yet implemented");
	}

	@Test
	public void testLeastSellingRestaurant() {
		fail("Not yet implemented");
	}

	@Test
	public void testMostActiveCourier() {
		fail("Not yet implemented");
	}

	@Test
	public void testLeastSellingCourier() {
		fail("Not yet implemented");
	}

}
