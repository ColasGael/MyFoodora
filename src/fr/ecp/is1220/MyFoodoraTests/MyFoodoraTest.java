package fr.ecp.is1220.MyFoodoraTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.ecp.is1220.MyFoodora.*;
import java.util.*;

public class MyFoodoraTest {

	private static MyFoodora myFoodora;

	@BeforeClass
	public static void importMyFoodora(){
		myFoodora = MyFoodora.loadMyFoodora();
	}
	
	@Test
	public void testSaveMyFoodora() {
		myFoodora.saveMyFoodora();
		MyFoodora myFoodoraBis = MyFoodora.loadMyFoodora();
		//we check that the two versions are equal
		assertEquals(myFoodora, myFoodoraBis);
	}

	@Test
	public void testLoadMyFoodora() {
		MyFoodora myFoodora = MyFoodora.loadMyFoodora();
		System.out.println(myFoodora);
	}

	@Test
	public void testRegister() throws UserNotFoundException{
		int lastID = User.getLastID();
		//we register a new user
		myFoodora.register("customer", "Paolo", "Ballarini", "professor", "password");
		//we check that the new user is in the system (of uniqueID : lastID + 1
		User user = myFoodora.findUserByUniqueID(lastID);
		System.out.println(user);
		ArrayList<User> users = myFoodora.getUsers();
		
		assertTrue(users.contains(user));
	}

	@Test
	public void testLogin() throws AccountDeactivatedException, IdentificationIncorrectException{
		Manager manager = (Manager)myFoodora.login("prabakarans", "birthdaydate");
		System.out.println(manager + "\n");
	}
	
	@Test (expected = IdentificationIncorrectException.class)
	public void testLoginWhenWrongIdentification() throws AccountDeactivatedException, IdentificationIncorrectException{
		Manager manager = (Manager)myFoodora.login("prabakarans", "datebirthday");
		System.out.println(manager);
	}
	
	@Test (expected = AccountDeactivatedException.class)
	public void testLoginWhenAccountDeactivated() throws AccountDeactivatedException, IdentificationIncorrectException, UserNotFoundException{
		Manager manager = (Manager)myFoodora.login("prabakarans", "birthdaydate");
		//the manager deactivate the account of the user 0
		manager.deactivateUser(1);
		
		User deactivatedUser = myFoodora.findUserByUniqueID(1);
		//the deactivated user try to connect to the system
		myFoodora.login(deactivatedUser.getUserName(), deactivatedUser.getPassword());
	}

	@Test
	public void testDisplayUsers() {
		//display all the users registered in myFoodora system
		myFoodora.displayUsers();
	}

	@Test (expected = UserNotFoundException.class)
	public void testFindUserByUniqueIDWhenNotInTheSystem() throws UserNotFoundException{
		int lastID = User.getLastID();
		User user = myFoodora.findUserByUniqueID(lastID+1);
	}
}
