package fr.ecp.is1220.MyFoodora;

public class TargetProfitPolicyMarkup implements TargetProfitPolicy {
	
	/**
	 * Sets the markup percentage
	 * based on last month income
	 * to meet a target profit given the formula : profitForOneOrder = orderPrice * markupPercentage + serviceFee - deliveryCost 
	 * @param myFoodora : MyFoodora system
	 * @param targetProfit : the target profit for 1 month to meet
	 */
	
	@Override
	public double meetTargetProfit (MyFoodora myFoodora, double targetProfit) throws NonReachableTargetProfitException {
		int numberOfOrders = myFoodora.getCompletedOrders().size();
		double totalIncome = myFoodora.totalIncomeLastMonth();
		double serviceFee = myFoodora.getServiceFee();
		double deliveryCost = myFoodora.getDeliveryCost();
		
		double markupPercentage = (targetProfit - numberOfOrders*(serviceFee - deliveryCost))/totalIncome; 
		
		if (markupPercentage >= 0){
			return(markupPercentage);
		}else{
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
	}
}
