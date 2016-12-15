package fr.ecp.is1220.MyFoodoraTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.ecp.is1220.MyFoodora.*;

public class OrderTest {

	private static MyFoodora myFoodora;
	/**
	 * we create an example of restaurant that will be used in the following tests
	 */
	private static Restaurant restaurant;
	/**
	 * we create an example of customer that will be used in the following tests
	 */
	private static Customer customer;
	
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
		}catch(Exception e){}
	}
	
	@Test
	public void testToString() {
		System.out.println(order);
	}
	
	@Test
	public void testSubmit() throws FoodItemNotFoundException{
		//the customer submit the order
		order.submit(true, myFoodora);
		
		Courier courier = order.getCourier();
		System.out.println(courier);
		//we check that the order is on the board of the courier
		System.out.println(courier.getBoard());
	}

	@Test
	public void testValidateOrderByCourier() {
		order.submit(true, myFoodora);

		Courier courier = order.getCourier();
		//the courier validate the order
		order.validateOrderByCourier(myFoodora);
		System.out.println(order);
		//we checked that the order is in completed orders in MyFoodora
		ArrayList<Order> completedOrders = myFoodora.getCompletedOrders();
		assertTrue(completedOrders.contains(order));
	}
}
