package fr.ecp.is1220.MyFoodora;

public interface TargetProfitPolicy {

	/**
	 * compute the value of one of the profit related information (service fee, markup percentage or delivery cost) to meet a target profit	 
	 * 		based on last month income
	 * 		given the formula : profitForOneOrder = orderPrice * markupPercentage + serviceFee - deliveryCost 
	 * @param myFoodora : MyFoodora system
	 * @param targetProfit : the target profit to meet
	 * @return profitInfoValue : the value of the profit related information
	 * @throws NonReachableTargetProfitException : if the target profit cannot be reached 
	 */
	
	public abstract double meetTargetProfit (MyFoodora myFoodora, double targetProfit) throws NonReachableTargetProfitException;
	
}
