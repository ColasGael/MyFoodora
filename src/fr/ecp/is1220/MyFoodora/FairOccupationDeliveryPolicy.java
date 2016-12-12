package fr.ecp.is1220.MyFoodora;

public class FairOccupationDeliveryPolicy implements DeliveryPolicy {

	public FairOccupationDeliveryPolicy() {
	}

	@Override
	public void allocateCourierToOrder(MyFoodora myFoodora, Order order) {

		Courier chosenCourier = null;
		//nbOfCompletedDeliveriesMin is initiated to infinite
		int nbOfCompletedDeliveriesMin = 999999999;
		
		for (User user: myFoodora.getUsers()){
			if (user instanceof Courier){
				Courier courier = (Courier)user;
				int nbOfCompletedDeliveries = courier.getCounter();
				
				if (nbOfCompletedDeliveries < nbOfCompletedDeliveriesMin){
					chosenCourier = courier;
				}
			}
		}
		order.setCourier(chosenCourier);
	}
}
