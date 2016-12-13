package fr.ecp.is1220.MyFoodora;

import java.util.Random ;
import java.util.Calendar ;
import java.util.ArrayList ;

public class LotteryFidelityCard extends FidelityCard {

	private static final long serialVersionUID = 7828283286969973152L;

	/**
	 * The probability of having the first meal free
	 */
	private static double probability = 0.01 ;

	/**
	 * The last day when the Card was used because we can use it once a day
	 */
	private Calendar lastDayWhenUsed ;

	public LotteryFidelityCard() {
		this.type = "lottery" ;
		this.lastDayWhenUsed = Calendar.getInstance() ;
	}
	@Override
	public double computeReduction(Order order){
		ArrayList<Meal> meals = order.getMeals() ;
		double newPrice = order.getPrice() ;
		if((lastDayWhenUsed.get(Calendar.DAY_OF_YEAR)!=Calendar.getInstance().get(Calendar.DAY_OF_YEAR))||(lastDayWhenUsed.get(Calendar.YEAR)!=Calendar.getInstance().get(Calendar.YEAR))){
			Random random = new Random() ;
			int lottery = random.nextInt(1000) ;
			if(lottery<=probability*1000){
				double priceOfOrder = newPrice ;
				double maxPriceOfMeal = 0 ;
				for(Meal meal : meals){
					if(meal.getPrice()>maxPriceOfMeal){
						maxPriceOfMeal = meal.getPrice() ;
					}
					newPrice = (priceOfOrder-maxPriceOfMeal);
				}
			}
		}
		return newPrice ;
	}
	@Override
	public void applyReduction(Order order){
		order.setPrice(computeReduction(order));
	}

	public static double getProbability() {
		return probability;
	}

	public static void setProbability(double probability) {
		LotteryFidelityCard.probability = probability;
	}
}
