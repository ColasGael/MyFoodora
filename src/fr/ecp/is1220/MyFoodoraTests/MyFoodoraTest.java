package fr.ecp.is1220.MyFoodoraTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.ecp.is1220.MyFoodora.*;

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
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisplayUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserByUniqueID() {
		fail("Not yet implemented");
	}

}
