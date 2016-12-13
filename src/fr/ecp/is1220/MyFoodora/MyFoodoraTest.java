package fr.ecp.is1220.MyFoodora;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyFoodoraTest {

	@Test
	public void testStartupScenario() {
		MyFoodora myFoodora = new MyFoodora(10, 0.05, 5) ;
		//myFoodora.registerCustomer("Theo", "Bob","theo7794","password",null);
		MyFoodora.saveMyFoodora(myFoodora);
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
