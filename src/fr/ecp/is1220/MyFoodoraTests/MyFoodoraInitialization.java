package fr.ecp.is1220.MyFoodoraTests;

import fr.ecp.is1220.MyFoodora.MyFoodora;

public class MyFoodoraInitialization {

	public MyFoodoraInitialization() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
			//we create an empty myFoodora
			double serviceFee = 10;
			double markupPercentage = 0.05;
			double deliveryCost = 5;
			MyFoodora myFoodora = new MyFoodora(serviceFee, markupPercentage, deliveryCost);
			myFoodora.saveMyFoodora();

	}

}
