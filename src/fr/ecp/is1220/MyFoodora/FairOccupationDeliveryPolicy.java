package fr.ecp.is1220.MyFoodora;

import java.io.Serializable ;

public class FairOccupationDeliveryPolicy implements DeliveryPolicy,Serializable {

	private static final long serialVersionUID = -8248152607689273031L;

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
				
				if (courier.isOnDuty()&&(nbOfCompletedDeliveries < nbOfCompletedDeliveriesMin)){
					chosenCourier = courier;
					nbOfCompletedDeliveriesMin = nbOfCompletedDeliveries ;
				}
			}
		}
		order.setCourier(chosenCourier);
	}
}
