package fr.ecp.is1220.MyFoodoraTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.ecp.is1220.MyFoodora.Courier;
import fr.ecp.is1220.MyFoodora.Customer;
import fr.ecp.is1220.MyFoodora.Dish;
import fr.ecp.is1220.MyFoodora.Meal;
import fr.ecp.is1220.MyFoodora.MyFoodora;
import fr.ecp.is1220.MyFoodora.Order;
import fr.ecp.is1220.MyFoodora.Restaurant;

public class CourierTest {
	private static MyFoodora myFoodora;
	/**
	 * we create an example of restaurant that will be used in the following tests
	 */
	private static Restaurant restaurant;
	/**
	 * we create an example of customer that will be used in the following tests
	 */
	private static Customer customer;
	/**
	 * we create an example of order that will be used in the following tests
	 */
	private static Order order;

	@BeforeClass
	public static void importMyFoodora(){
		myFoodora = MyFoodora.loadMyFoodora();
		try{
			restaurant = (Restaurant) myFoodora.login("hoki", "password");
			customer = (Customer) myFoodora.login("chaperouge", "qsdfghjkl");
			
			//we create an order
			order = new Order(customer, restaurant);
			//we fill the order with food items
			Dish dish = restaurant.findDishByName("brochettes boeuf");
			order.addDish(dish);
			Meal meal = restaurant.findMealByName("M3");
			order.addMeal(meal);
			//the customer submit the order
			order.submit(true, myFoodora);
			
			Courier courier = order.getCourier();
		}catch(Exception e){}
	}
	@Test
	public void testAcceptDeliveryCall() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPhoneNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBoard() {
		fail("Not yet implemented");
	}

}
