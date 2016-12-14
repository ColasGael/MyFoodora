package fr.ecp.is1220.MyFoodora;

import java.io.Serializable ;

/**
 *this factory class enables us to register different types of users
 */
public class UserFactory implements Serializable {

	private static final long serialVersionUID = -6020715672334438764L;

	public UserFactory() {
	}
	
	/**
	 * register a new user in the my Foodora system
	 * @param userType : the type of user who is registered : "customer", "courier", "restaurant" or "manager"
	 * @param name : the name of the user
	 * @param surname : the surname of the user
	 * @param userName : the username of the user
	 * @param password : the password of the user
	 * @param myFoodora : myFoodora core
	 */
	public void registerUser (String userType, String name, String surname, String userName, String password, MyFoodora myFoodora){
		switch(userType){
			case("customer"):
				Customer customer = new Customer(name, surname, userName, password);
				myFoodora.addUser(customer);
				break;
			case("courier"):
				Courier courier = new Courier(name, surname, userName, password);
				myFoodora.addUser(courier);
				break;
			case("restaurant"):
				Restaurant restaurant = new Restaurant(name, surname, userName, password);
				myFoodora.addUser(restaurant);
				break;
			case("manager"):
				Manager manager = new Manager(name, surname, userName, password, myFoodora);
				//to ensure that non-manager users cannot create manager accounts
				manager.setActivated(false);
				myFoodora.addUser(manager);
				break;
			default:
				break;
		}
	}

}
