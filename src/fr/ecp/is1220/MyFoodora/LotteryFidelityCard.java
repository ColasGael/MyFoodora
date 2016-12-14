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
	
	/**
	 * computes the reduction of an order if the user wins the lottery (can be used once a day)
	 * @param order : the order submitted by the user
	 * @return reduction : the reduction which can be applied
	 */
	@Override
	public double computeReduction(Order order){
		double reduction = 0;
		
		ArrayList<Meal> meals = order.getMeals() ; //We get all the meals of the current order

		//if the last day when the lottery was used is not today :
		if(!this.hasPlayed()){
			Random random = new Random() ;
			//if the lottery is successful
			if(random.nextDouble()<=probability){
				double maxPriceOfMeal = 0 ; //we want to get the meal which is the most expensive.
				for(Meal meal : meals){
					if(meal.getPrice()>maxPriceOfMeal){
						maxPriceOfMeal = meal.getPrice() ;
					}
				}
				reduction = maxPriceOfMeal;
			}
			lastDayWhenUsed = Calendar.getInstance() ;
		}
		return reduction ;
	}
	
	/**
	 * applies the reduction calculated with computeReduction to the price of the order
	 * @param order : the order submitted by the user
	 */
	@Override
	public void applyReduction(Order order){
		double originalPrice = order.getPrice();
		double reduction = this.computeReduction(order);
		
		order.setPrice(originalPrice - reduction);
	}
	/**
	 * indicates if the customer has played today
	 * @return hasPlayed : true if the user hasPlayed today
	 */
	public boolean hasPlayed(){
		boolean hasPlayed = (this.lastDayWhenUsed.get(Calendar.DAY_OF_YEAR)==Calendar.getInstance().get(Calendar.DAY_OF_YEAR))&&(lastDayWhenUsed.get(Calendar.YEAR)==Calendar.getInstance().get(Calendar.YEAR));
		return hasPlayed;
	}

	public static double getProbability() {
		return probability;
	}

	public static void setProbability(double probability) {
		LotteryFidelityCard.probability = probability;
	}
	
	@Override
	public String toString(){
		String result = ((FidelityCard)this).toString();
		result += "You have " + (this.hasPlayed() ? "already " : "not ") + "played today\n";
		result += "You have a probability of " + probability + " to win at the lottery\n";
		result += "if you win, the most expensive meal of your order becomes free\n";
		return(result);
	}
}
