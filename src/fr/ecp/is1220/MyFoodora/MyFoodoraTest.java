package fr.ecp.is1220.MyFoodora;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyFoodoraTest {

	@Test
	public void testSaveAndLoadScenario() {
		MyFoodora myFoodora = new MyFoodora(10, 0.05, 5) ;
		
		//we create a new restaurant
		Restaurant hoki = new Restaurant("Le Hoki", "hoki", "password", new Position(12.3, 478.21));
		//we add dishes to the menu
		hoki.addDish("mainDish", "maki thon", 4.5, "standard");
		hoki.addDish("mainDish", "maki saumon", 4, "standard");
		hoki.addDish("mainDish", "maki cheese", 3.5, "vegetarian");
		hoki.addDish("mainDish", "california maki", 16.5, "standard");
		hoki.addDish("starter", "soupe", 2.5, "vegetarian");
		hoki.addDish("dessert", "litchee", 4, "vegetarian");
		hoki.addDish("mainDish", "brochettes boeuf", 10.5, "standard");
		
		//we add meals to the menu
		hoki.addMeal("half", "M2");
		hoki.addDish2Meal("M2", "soupe");
		hoki.addDish2Meal("M2", "maki saumon");
		hoki.addMeal("half", "M2");
		hoki.addDish2Meal("M3", "soupe");
		hoki.addDish2Meal("M3", "maki cheese");
		hoki.addDish2Meal("M3", "litchee");
		hoki.addMeal("half", "B1");
		hoki.addDish2Meal("B1", "brochettes boeuf");
		hoki.addDish2Meal("B1", "litchee");
		
		
		myFoodora.register("customer","Theo", "Bob","theo7794","password");
		myFoodora.register("courier","Jean", "Livreur","jean","password");
		myFoodora.register("restaurant","Le Hoki",null,"hoki","password");
		
		myFoodora.saveMyFoodora();
		MyFoodora myFoodorabis = MyFoodora.loadMyFoodora() ;
	}
	
	/*@Test
	public void testLoginScenario() {
		MyFoodora myFoodora = MyFoodora.loadMyFoodora() ;
		
		User currentUser = myFoodora.login("theo7794", "password") ;
		if (currentUser.getUserType() == "Customer"){
			Customer currentCustomer = (Customer)myFoodora.login("theo7794", "password");
		}else if (currentUser.getUserType() == "Courier"){
			Courier currentCourier = (Courier)myFoodora.login("theo7794", "password");
		}else if (currentUser.getUserType() == "Restaurant"){
			Restaurant currentRestaurant = (Restaurant)myFoodora.login("theo7794", "password");
		}		
	}*/

}
