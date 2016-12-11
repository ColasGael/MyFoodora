package Group9_Project_IS1220_part1_Colas_Prabakaran;

public class TargetProfitPolicyMarkup implements TargetProfitPolicy {
	
	/**
	 * Sets the markup percentage
	 * based on last month income
	 * to meet a target profit given the formula : profitForOneOrder = orderPrice * markupPercentage + serviceFee - deliveryCost 
	 * @param myFoodora : MyFoodora system
	 * @param targetProfit : the target profit for 1 month to meet
	 */
	
	@Override
	public void meetTargetProfit (MyFoodora myFoodora, double targetProfit) throws NonReachableTargetProfitException {
		int numberOfOrders = myFoodora.getCompletedOrders().size();
		double totalIncome = myFoodora.totalIncomeLastMonth();
		double serviceFee = myFoodora.getServiceFee();
		double deliveryCost = myFoodora.getDeliveryCost();
		
		double markupPercentage = (targetProfit - numberOfOrders*(serviceFee - deliveryCost))/totalIncome; 
		
		if (markupPercentage >= 0){
			myFoodora.setMarkupPercentage(markupPercentage);
		}else{
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
	}
}
