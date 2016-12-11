package Group9_Project_IS1220_part1_Colas_Prabakaran;

public interface TargetProfitPolicy {

	/**
	 * Sets one of the profit related information : service fee, markup percentage or delivery cost
	 * based on last month income
	 * to meet a target profit given the formula : profitForOneOrder = orderPrice * markupPercentage + serviceFee - deliveryCost 
	 * @param myFoodora : MyFoodora system
	 * @param targetProfit : the target profit to meet
	 */
	
	public abstract void meetTargetProfit (MyFoodora myFoodora, double targetProfit) throws NonReachableTargetProfitException;
	
}
