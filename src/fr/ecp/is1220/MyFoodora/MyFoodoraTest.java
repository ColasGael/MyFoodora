package Group9_Project_IS1220_part1_Colas_Prabakaran;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyFoodoraTest {

	@Test
	public void testStartupScenario() {
		MyFoodora myFoodora = new MyFoodora() ;
		myFoodora.registerCustomer("Theo","theo7794","password",null,null);
		myFoodora.saveMyFoodora();
		myFoodora.loadMyFoodora() ;
	}
	
	@Test
	public void testLoginScenario() {
		MyFoodora myFoodora = new MyFoodora() ;
		myFoodora.loadMyFoodora() ;
		
		User currentUser = myFoodora.login("theo7794", "password") ;
		if (currentUser.getUserType() == "Customer"){
			Customer currentCustomer = (Customer)myFoodora.login("theo7794", "password");
		}else if (currentUser.getUserType() == "Courier"){
			Courier currentCourier = (Courier)myFoodora.login("theo7794", "password");
		}else if (currentUser.getUserType() == "Restaurant"){
			Restaurant currentRestaurant = (Restaurant)myFoodora.login("theo7794", "password");
		}		
	}

}
