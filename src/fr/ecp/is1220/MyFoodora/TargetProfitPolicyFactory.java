package fr.ecp.is1220.MyFoodora;


public class TargetProfitPolicyFactory {

	public TargetProfitPolicyFactory() {
	}
	
	public TargetProfitPolicy chooseTargetProfitPolicy (String profitPolicy) {
		TargetProfitPolicy targetProfitPolicy = null;
		switch(profitPolicy){
			case("deliveryCost"):
				targetProfitPolicy = new TargetProfitPolicyDeliveryCost();
				break;
			case("markup"):
				targetProfitPolicy = new TargetProfitPolicyMarkup();
				break;
			case("serviceFee"):
				targetProfitPolicy = new TargetProfitPolicyServiceFee();
				break;
		}
		return (targetProfitPolicy);
	}
}
