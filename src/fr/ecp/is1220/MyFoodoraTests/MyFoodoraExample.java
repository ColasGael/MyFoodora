package fr.ecp.is1220.MyFoodoraTests;

import fr.ecp.is1220.MyFoodora.*;

public class MyFoodoraExample {
	/**
	 * creates an example of My Foodora which will be saved in "MyFoodora.ser"
	 * this example is later used in the JUnit tests
	 */
	public static void createMyFoodora() {
		//we create an empty myFoodora
		double serviceFee = 10;
		double markupPercentage = 0.05;
		double deliveryCost = 5;
		MyFoodora myFoodora = new MyFoodora(serviceFee, markupPercentage, deliveryCost);
		
		//we add the first manager
		Manager manager = new Manager("Gael", "Colas", "gcolas", "123456789", myFoodora);
		myFoodora.addUser(manager);
		
		//we create a first restaurant : the HOKI
		Restaurant hoki = new Restaurant("Le Hoki", "hoki", "password", new Position(12.3, 478.21));
		Menu menu = hoki.getMenu();
		//we add dishes and meals to the menu
		Starter starter = new Starter("soupe", 2.5, "vegetarian"); menu.addDish(starter);

		MainDish mainDish = new MainDish("maki thon", 4.5, "standard");	menu.addDish(mainDish);
		mainDish = new MainDish("california maki", 16.5, "standard"); menu.addDish(mainDish);
		mainDish = new MainDish("maki saumon", 4, "standard"); menu.addDish(mainDish);
		
		HalfMeal halfMeal = new HalfMeal("M2", mainDish, starter); menu.addMeal(halfMeal);		//we set the meal of the day
		menu.setMealOfTheWeek(halfMeal);
		
		mainDish = new MainDish("maki cheese", 3.5, "vegetarian"); menu.addDish(mainDish);
		Dessert dessert = new Dessert("litchee", 4, "vegetarian");	menu.addDish(dessert);
		FullMeal fullMeal = new FullMeal("M3", mainDish, starter, dessert); menu.addMeal(fullMeal);

		mainDish = new MainDish("brochettes boeuf", 10.5, "standard"); menu.addDish(mainDish);
		halfMeal = new HalfMeal("B1", mainDish, dessert); menu.addMeal(halfMeal);

		//we add the hoki to myFoodora
		myFoodora.addUser(hoki);
		
		//we create a second restaurant : the DOMINOS
		Restaurant dominos = new Restaurant("Dominos", "domi", "password", new Position(121.3, 41.31));
		menu = dominos.getMenu();
		//we add dishes and meals to the menu
		starter = new Starter("cheesy bread", 2.5, "vegetarian"); menu.addDish(starter);
		dessert = new Dessert("tiramisu", 4.5, "vegetarian");	menu.addDish(dessert);

		mainDish = new MainDish("orientale", 13.5, "standard");	menu.addDish(mainDish);
		
		halfMeal = new HalfMeal("duo", mainDish, starter); menu.addMeal(halfMeal);
		
		mainDish = new MainDish("4 fromages", 12.5, "standard");	menu.addDish(mainDish);
		mainDish = new MainDish("margherita", 10, "standard");	menu.addDish(mainDish);
		mainDish = new MainDish("cannibale", 13, "standard");	menu.addDish(mainDish);

		fullMeal = new FullMeal("family", mainDish, starter, dessert); menu.addMeal(fullMeal);

		mainDish = new MainDish("california maki", 16.5, "standard"); menu.addDish(mainDish);
		mainDish = new MainDish("maki saumon", 4, "standard"); menu.addDish(mainDish);
	
		//we add the dominos to myFoodora
		myFoodora.addUser(dominos);
		
		//we adds manager
		myFoodora.addUser(new Manager("Sylvestre", "Prabakaran", "prabakarans", "birthdaydate", myFoodora));
		
		//we add couriers
		Courier courier = new Courier("Peter", "Parker", "spiderman", "password", new Position(1.23, 854.1), "0651964987"); 
		for (int i=0 ; i<23; i++){
			courier.increaseCounter();
		}
		courier.setOnDuty(true);
		myFoodora.addUser(courier);
		
		courier = new Courier("Spider", "Man", "peterparker", "motdepasse", new Position(1.23, 854.1), "0651964988");
		for (int i=0; i<7; i++){
			courier.increaseCounter();
		}
		courier.setOnDuty(true);
		myFoodora.addUser(courier);

		myFoodora.addUser(new Courier("Toby", "McGuire", "tobmac", "azerty", new Position(12.3, 5.18), "0651964989"));
		
		//we add customers
		Customer customer = new Customer("Xavier", "Collar", "chaperouge", "qsdfghjkl", new Position(23.3, 45.12), "xavier.collar@myecp.fr", "0786822354");
		
		//we give this customer fidelity points
		customer.registerFidelityCard("point");
		((PointFidelityCard)customer.getFidelityCard()).addPoints(123);
		
		myFoodora.addUser(customer);
		myFoodora.addUser(new Customer("Regis", "Troissant", "cptcroche", "hfekljkfemvc", new Position(87.3, 12.5), "regis.troissant@myecp.fr", "0786822355"));
		myFoodora.addUser(new Customer("Baptiste", "Turpin", "alladin", "ieupri", new Position(2.3, 4.12), "baptiste.turpin@myecp.fr", "0786822356"));
		myFoodora.addUser(new Customer("Kevin", "Uzan", "PJ", "fpzkfpzof", new Position(69.2, 12.78), "kevin.uzan@myecp.fr", "0786822354"));
		myFoodora.addUser(new Customer("Theo", "Bob", "theo7794", "password", new Position(84.3, 145.12), "theo.bob@gmail.com", "0786822354"));
		
		//we create an order
		Order order = new Order(customer, hoki);
		//we fill the order with food items
		order.addDish(mainDish);
		order.addMeal(fullMeal);
		//the customer submits the order
		order.submit(true, myFoodora);
		//the courier accepts the delivery call
		order.validateOrderByCourier(myFoodora);
		
		myFoodora.saveMyFoodora();
	}
	
	
	public static void main(String[] args) {
		createMyFoodora();
	}
}
