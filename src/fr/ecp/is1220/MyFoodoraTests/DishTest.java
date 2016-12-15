package fr.ecp.is1220.MyFoodoraTests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.ecp.is1220.MyFoodora.*;

public class DishTest {

	@Test
	public void testGetDishType() {
		MainDish mainDish = new MainDish("quiche aux legumes", 18, "vegetarian");
		assertEquals("The dish type must be compute automatically", "mainDish", mainDish.getDishType());
	}

	@Test
	public void testToString() {
		Starter starter = new Starter("mozzarella", 4, "vegetarian");
		System.out.println(starter);
	}

	@Test
	public void testEqualsObject() {
		Dessert dessert = new Dessert("gateau au pois-chiche", 18, "vegetarian");
		Dessert dessertCopy = new Dessert("gateau au pois-chiche", 18, "vegetarian");
		assertEquals("The overriden equals function enables to compare Dish objects", dessert, dessertCopy);
	}

}
