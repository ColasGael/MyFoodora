package Group9_Project_IS1220_part1_Colas_Prabakaran;

public class TargetProfitPolicyDeliveryCost implements TargetProfitPolicy {

	/**
	 * Sets the delivery cost
	 * based on last month income
	 * to meet a target profit given the formula : profitForOneOrder = orderPrice * markupPercentage + serviceFee - deliveryCost 
	 * @param myFoodora : MyFoodora system
	 * @param targetProfit : the target profit to meet
	 */
	
	@Override
	public void meetTargetProfit (MyFoodora myFoodora, double targetProfit) throws NonReachableTargetProfitException {
		int numberOfOrders = myFoodora.getCompletedOrders().size();
		double totalIncome = myFoodora.totalIncomeLastMonth();
		double markupPercentage = myFoodora.getMarkupPercentage();
		double serviceFee = myFoodora.getServiceFee();
		
		double deliveryCost = - (targetProfit - totalIncome*markupPercentage)/numberOfOrders + serviceFee; 
	
		if (markupPercentage >= 0){
			myFoodora.setDeliveryCost(deliveryCost);
		}else{
			throw (new NonReachableTargetProfitException("This target profit can not be reached"));
		}
	}
}
