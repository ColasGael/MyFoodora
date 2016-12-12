package fr.ecp.is1220.MyFoodora;

public class TargetProfitPolicyDeliveryCost implements TargetProfitPolicy {

	/**
	 * Sets the delivery cost
	 * based on last month income
	 * to meet a target profit given the formula : profitForOneOrder = orderPrice * markupPercentage + serviceFee - deliveryCost 
	 * @param myFoodora : MyFoodora system
	 * @param targetProfit : the target profit to meet
	 */
	
	@Override
	public double meetTargetProfit (MyFoodora myFoodora, double targetProfit) throws NonReachableTargetProfitException {
		int numberOfOrders = myFoodora.getCompletedOrders().size();
		double totalIncome = myFoodora.totalIncomeLastMonth();
		double markupPercentage = myFoodora.getMarkupPercentage();
		double serviceFee = myFoodora.getServiceFee();
		
		double deliveryCost = - (targetProfit - totalIncome*markupPercentage)/numberOfOrders + serviceFee; 
	
		if (markupPercentage >= 0){
			return(deliveryCost);
		}else{
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
	}
}
