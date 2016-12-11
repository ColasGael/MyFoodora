package Group9_Project_IS1220_part1_Colas_Prabakaran;

public class FairOccupationDeliveryPolicy implements DeliveryPolicy {

	public FairOccupationDeliveryPolicy() {
		// TODO Auto-generated constructor stub
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
