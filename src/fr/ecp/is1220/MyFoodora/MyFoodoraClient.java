package fr.ecp.is1220.MyFoodora;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException ;
import java.util.StringTokenizer ;
import java.util.ArrayList ;
import java.util.Calendar;

public class MyFoodoraClient {

	private static Scanner sc ;
	private static String input ;
	private static StringTokenizer st ;
	private static MyFoodora myFoodora ;
	private static User currentUser ;
	private static Order currentOrder = null ;

	public static void main(String[] args) {
		myFoodora = MyFoodora.loadMyFoodora();

		myFoodora.displayUsers();

		sc = new Scanner(System.in);

		System.out.println("Welcome on the MyFoodora application \n");

		String commande = "" ;
		closeLoop :
			while (!commande.equals("close")){	
				System.out.println("If you are a new user please \"register\" \n"
						+ "If not please use \"login <username> <password>\"\n"
						+ "Type \"help <>\" to have a list of all available commands");
				input = sc.nextLine();
				st = new StringTokenizer(input) ;
				try{
					commande = st.nextToken() ;
				}catch(NoSuchElementException e){
					commande = "";
				}
				switch (commande){
				case("register"):
					System.out.println("You have successfully created an account !\n"
							+ "Now type \"login <username> <password>\" to log into the system \n"
							+ "Or \"close <>\" to quit \n");
					break;					
				case("login"):
					String userType = login() ;
					String workReturn = "next" ;
					if(userType!=null){
						while(workReturn.equals("next")){
							workReturn = work(userType);
						}
						if (workReturn.equals("logout")){
							System.out.println("You have logged out.");
							break ;
						}
					}
					break;
				case ("runTest"):
					st = new StringTokenizer(input) ;
					String fileName = "" ;
					try{
						st.nextToken("\"");
						fileName = st.nextToken() ;
						File testScenarioFile = new File(fileName+".txt");
						//to read the testScenario file
						sc = new Scanner (testScenarioFile);
						//to write the testScenario file
						OutputStream out = new FileOutputStream(fileName+"Output.txt");
						System.setOut(new PrintStream(out));
					}catch(NoSuchElementException e){
						System.err.println("Invalid number of arguments or syntax error.");
					}catch (FileNotFoundException e){
						System.err.println("File of name \"" + fileName + "\" has not been found");
					}
					break;					
				case("help"):
					System.out.println("\"register <usertype> <name> <surname> <username> <password>\" : register into the system\n"
							+ "\"login <username> <password>\" : log into the system\n"
							+ "\"runTest <testScenarioFile>\" : execute the list of CLUI commands contained in the testScenario file passed as argument\n"
							+ "\"close<>\" : close MyFoodora");
					break ;
				case("close"):
					break closeLoop ;
				default:
					System.err.println("This choice is not available, please try again");
					break;
				}
			}
		System.out.println("Thank you for your visit ! \nGoodbye !\n");
		sc.close();
		myFoodora.saveMyFoodora();
	}

	/**
	 * Method which let the user log in the system
	 * @return the userType of the user.
	 */
	private static String login(){
		String userType = null ;
		try{
			st.nextToken("\"");
			String username = st.nextToken("\"");
			st.nextToken("\"");
			String password = st.nextToken("\"") ;
			currentUser = myFoodora.login(username,password);
			userType = currentUser.getUserType();
			System.out.println("You have successfully logged in the system !\n");
			System.out.println("Welcome "+currentUser.getName());			
		}catch(IdentificationIncorrectException e){
			System.out.println("Sorry " + e.getMessage() + "\n"
					+ "Please try again \n");
		}catch(AccountDeactivatedException e){
			System.out.println("Sorry " + e.getMessage() + "\n"
					+ "Your account has been deactivated : Please call a manager : +33 1 41 13 15 79 \n");
		}catch(NoSuchElementException e){
			System.err.println("Invalid number of parameters or syntax error.");
		}
		return userType ;
	}

	/**
	 * Let the user use the platform with the commands he can use.
	 * @param userType
	 */
	private static String work(String userType){		
		System.out.println("Type \"help\" to have a list of all available commands.");
		input = sc.nextLine();
		st = new StringTokenizer(input) ;
		switch (userType){
		case("customer"):
			return workCustomer() ;
		case("courier"):
			return workCourier() ;
		case("restaurant"):
			return workRestaurant() ;
		case("manager"):
			return workManager() ;
		default :
			return null ;
		}		
	}
	
	/**
	 * Works with all the possible commands that a customer can use
	 * @return "next" to go to the next command or "logout" if the customer wanted to logout.
	 */
	private static String workCustomer(){
		Customer currentCustomer = (Customer)currentUser ;
		String commande ;
		boolean error = false ;
		try{
			commande = st.nextToken() ;
			switch (commande){
			case("help"):
				System.out.println("\"createOrder <restaurantName>\" : create a new order\n"
						+ "\"addItem2Order <itemName>\" : add a dish or a meal to the menu\n"
						+ "\"endOrder <applyReduction> : submit the order to today's date and applies the order depending on the applyReduction value \"yes\" or \"no\"\n"
						+ "\"logout\" : logout\n") ;
				return "next" ;
			case("createOrder"):
				st.nextToken("\"");
				String restaurantName = st.nextToken("\"");
				Restaurant orderedRestaurant = null ;
				try{
					User orderedUser = myFoodora.findUserByName(restaurantName) ;
					if(orderedUser.getUserType().equals("restaurant")){
						orderedRestaurant = (Restaurant)orderedUser ;
					}else{
						System.err.println("oupsThe restaurant \""+restaurantName+"\" does not exist.");
						error = true ;
					}
				}catch(UserNotFoundException e){
					System.err.println("The restaurant \""+restaurantName+"\" does not exist.");
					error = true ;
				}
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					currentOrder = new Order(currentCustomer,orderedRestaurant);
					System.out.println("A new order has been created. Here is the restaurant's menu :");
					currentOrder.getRestaurant().displayMenu();
				}
				return "next" ;
			case("addItem2Order"):
				st.nextToken("\"");
				String itemName = st.nextToken("\"");
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(currentOrder==null){
					System.err.println("You have not created any order.");
					error = true ;
					return "next" ;
				}
				boolean itemNotExisting = true ;
				String itemType = null ;
				Dish orderedDish = null ;
				Meal orderedMeal = null ;
				try{
					orderedDish = currentOrder.getRestaurant().findDishByName(itemName) ;
					itemType = "dish" ;
					itemNotExisting = false ;
				}catch(FoodItemNotFoundException e){
					
				}
				try{
					orderedMeal = currentOrder.getRestaurant().findMealByName(itemName) ;
					itemType = "meal" ;
					itemNotExisting = false ;
				}catch(FoodItemNotFoundException e){
					
				}
				if(itemNotExisting){
					System.err.println("The item \""+itemName+"\" does not exist.");
					error = true ;
				}
				if(!error){
					switch(itemType){
					case("dish"):
						currentOrder.addDish(orderedDish);
						System.out.println("The dish \""+itemName+"\" has been added to your order.");
						break ;
					case("meal"):
						currentOrder.addMeal(orderedMeal);
						System.out.println("The meal \""+itemName+"\" has been added to your order.");
						break ;
					}
					System.out.println("The actual price of your order is "+currentOrder.computePrice());
				}
				return "next" ;
			case("endOrder"):
				st.nextToken("\"");
				String applyReduction = st.nextToken("\"");
				boolean applyReductionBool = false ;
				if(st.hasMoreTokens()){
					if(st.hasMoreTokens()){	
						System.err.println("The command \"endOrder <applyReduction>\" has only 1 parameter.");
						error = true ;
					}
				}
				switch(applyReduction){
				case("yes"):
					applyReductionBool = true ;
					break ;
				case("no"):
					applyReductionBool = false ;
					break ;
				default :
					System.err.println("The applyReduction parameter must be \"yes\" or \"no\".");
					error = true ;
					break ;
				}
				if(currentOrder==null){
					System.err.println("You have not created any order.");
					error = true ;
					return "next" ;
				}
				if(currentOrder.computePrice()==0){
					System.err.println("You have not ordered anything.");
					error = true ;
				}
				if(!error){
					System.out.println("Here is your order :");
					System.out.println(currentOrder);
					if(applyReductionBool){
						if(currentCustomer.getFidelityCard().computeReduction(currentOrder)>0){
							System.out.println("You have won "+currentCustomer.getFidelityCard().computeReduction(currentOrder)+" with your fidelity card.");
						}
					}
					currentCustomer.submitOrder(currentOrder, true, myFoodora);
					currentOrder = null ;
				}
				return "next" ;
			case("logout"):
				if(st.hasMoreTokens()){
					if(st.hasMoreTokens()){	
						System.err.println("The command \"logout <>\" cannot have parameters.");
						return "next" ;
					}
				}
				return "logout" ;				
			default :
				System.err.println("The command "+commande+" does not exist.");	
			}
			return "next" ;
		}catch(NoSuchElementException e){
			System.err.println("Invalid number of parameters or syntax error.");
			return "next" ;
		}
	}
	
	/**
	 * Works with all the possible commands that a courier can use
	 * @return "next" to go to the next command or "logout" if the courier wanted to logout.
	 */
	private static String workCourier(){
		Courier currentCourier = (Courier)currentUser ;
		System.out.println(currentCourier.getBoard());
		boolean error = false ;
		String commande ;
		try{
			commande = st.nextToken() ;
			switch (commande){
			case("help"):
				System.out.println("\"onDuty <>\" : set state to on duty\n"
						+ "\"offDuty <>\" : set state to off duty\n"
						+ "\"accept <orderID> : accept the delivery call for the order of ID\n"
						+ "\"refuse <orderID> : refuse the delivery call for the order of ID"
						+ "\"logout\" : log out\n"
						+ "\"close\" : close MyFoodora");
				break;
			case("onDuty"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"onDuty <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("You were "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
					currentCourier.setOnDuty(true);
					System.out.println("You are now "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
				}
				return "next" ;
			case("offDuty"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"offDuty <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("You were "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
					currentCourier.setOnDuty(false);
					System.out.println("You are now "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
				}
				return "next" ;			
			case("accept"):
				while(!input.equals("exit")){
					System.out.println("Please enter the ID of the order you want to deliver or type \"exit\":");
					input = sc.next();
					try{
						int id = Integer.parseInt(input) ;
						currentCourier.acceptDeliveryCall(true, currentCourier.getBoard().findObsById(id), myFoodora);
						System.out.println("You have accepted to deliver this order :\n"+currentCourier.getBoard().findObsById(id));
						return "next" ;
					}catch(OrderNotFoundException e){
						System.err.println("This Order ID is not in your board.");
					}catch(NumberFormatException e){
						System.err.println("You must enter an ID");
					}
				}
				break ;
			case("refuse"):
				while(!input.equals("exit")){
					System.out.println("Please enter the ID of the order you want to refuse or type \"exit\":");
					input = sc.next();
					try{
						int id = Integer.parseInt(input) ;
						currentCourier.acceptDeliveryCall(false, currentCourier.getBoard().findObsById(id), myFoodora);
						System.out.println("You have accepted to deliver this order :\n"+currentCourier.getBoard().findObsById(id));
						return "next" ;
					}catch(OrderNotFoundException e){
						System.err.println("This Order ID is not in your board.");
					}catch(NumberFormatException e){
						System.err.println("You must enter an ID");
					}
				}
				break ;
			case("logout"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"offDuty <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					return "logout" ;
				}
				return "next" ;	
			default:
				System.out.println("This command is not available, please try again \n");
				break;
			}
			return "next" ;
		}catch(NoSuchElementException e){
			commande = "";
			return "next" ;
		}
	}
	
	/**
	 * Works with all the possible commands that a manager can use
	 * @return "next" to go to the next command or "logout" if the manager wanted to logout.
	 */
	private static String workManager(){
		Manager currentManager = (Manager)currentUser ;
		boolean error = false ;
		String commande ;
		try{
			commande = st.nextToken() ;
			switch (commande){
			case("help"):
				System.out.println("\"registerRestaurant <name> <username> <password> <address> : register a new restaurant\n"
						+ "\"registerCustomer <firstName> <lastName> <username> <password> <address> : register a new customer\n"
						+ "\"registerCourier <firstName> <lastName> <username> <password> <address> : register a new courier\n"
						+ "\"setDeliveryPolicy <delPolicyName>\" : set the delivery policy to \"fastest\" or \"fairOccupation\"\n"
						+ "\"meetTargetProfit <profitInfoName> <targetProfit>\" : show how to set the value of the profit info (\"deliveryCost\", \"serviceFee\" or \"markup\") to meet the target profit.\n"
						+ "\"setProfitInfo <profitInfoName> <value>\" : sets the profitInfo (\"deliveryCost\", \"serviceFee\" or \"markup\") to the value\n"
						+ "\"associateCard <userName> <cardType>\" : associate a new fidelity card (\"basic\", \"point\" or \"lottery\" to the user\n"
						+ "\"showCourierDeliveries <>\" : displays all the Courier ordered by the number of delivered orders\n"
						+ "\"showRestaurantTop <>\" : displays all the Restaurants ordered by the number of orders sold\n"
						+ "\"showCustomers <>\" : displays all the Customers\n"
						+ "\"showMenuItem <restaurantName>\" : displays the menu of the restaurant\n"
						+ "\"logout\" : log out"
						);
				break;
			case("registerRestaurant"):
				st.nextToken("\"");
				String restaurantName = st.nextToken("\"");
				st.nextToken("\"");
				String restaurantUserName = st.nextToken("\"");
				st.nextToken("\"");
				String restaurantPassword = st.nextToken("\"");
				String restaurantXString = st.nextToken("\",");
				double restaurantX = 0;
				String restaurantYString = st.nextToken(",\"");
				double restaurantY = 0;
				try{
					restaurantX = Double.parseDouble(restaurantXString) ;
					restaurantY = Double.parseDouble(restaurantYString) ;
				}catch(NumberFormatException e){
					System.err.println("The address parameter is invalid you must enter two coordinates (ex : \"1.25,1.45\").");
					error = true ;
				}
				st.nextToken("\"");
				if(st.hasMoreTokens()){	
					System.err.println("The command \"registerRestaurant <name> <username> <password> <address>\" has only 4 parameters.");
					error = true ;
				}
				if(!error){
					currentManager.addUser("restaurant", restaurantName, "", restaurantUserName, restaurantPassword);
					try{
						((Restaurant)currentManager.getMyFoodora().findUserByName(restaurantName)).setAddress(new Position(restaurantX,restaurantY));
						System.out.println("The restaurant has been registered. Here are its properties : ") ;
						System.out.println(currentManager.getMyFoodora().findUserByName(restaurantName));
					}catch(UserNotFoundException e){
						System.out.println("Error while creating the user.");
					}
				}
				return "next" ;
			case("registerCustomer"):
				st.nextToken("\"");
				String customerName = st.nextToken("\"");
				st.nextToken("\"");
				String customerSurname = st.nextToken("\"");
				st.nextToken("\"");
				String customerUserName = st.nextToken("\"");
				st.nextToken("\"");
				String customerPassword = st.nextToken("\"");
				String customerXString = st.nextToken("\",");
				double customerX = 0;
				String customerYString = st.nextToken(",\"");
				double customerY = 0;
				try{
					customerX = Double.parseDouble(customerXString) ;
					customerY = Double.parseDouble(customerYString) ;
				}catch(NumberFormatException e){
					System.err.println("The address parameter is invalid you must enter two coordinates (ex : \"1.25,1.45\").");
					error = true ;
				}
				st.nextToken("\"");
				if(st.hasMoreTokens()){	
					System.err.println("The command \"registerCustomer <firstName> <lastName> <username> <password> <address>\" has only 5 parameters.");
					error = true ;
				}
				if(!error){
					currentManager.addUser("customer", customerName, customerSurname, customerUserName, customerPassword);
					try{
						((Customer)currentManager.getMyFoodora().findUserByName(customerName)).setAddress(new Position(customerX,customerY));
						System.out.println("The customer has been registered. Here are its properties : ") ;
						System.out.println(currentManager.getMyFoodora().findUserByName(customerName));
					}catch(UserNotFoundException e){
						System.out.println("Error while creating the user.");
					}
				}
				return "next" ;
			case("registerCourier"):
				st.nextToken("\"");
				String courierName = st.nextToken("\"");
				st.nextToken("\"");
				String courierSurname = st.nextToken("\"");
				st.nextToken("\"");
				String courierUserName = st.nextToken("\"");
				st.nextToken("\"");
				String courierPassword = st.nextToken("\"");
				String courierXString = st.nextToken("\",");
				double courierX = 0;
				String courierYString = st.nextToken(",\"");
				double courierY = 0;
				try{
					courierX = Double.parseDouble(courierXString) ;
					courierY = Double.parseDouble(courierYString) ;
				}catch(NumberFormatException e){
					System.err.println("The address parameter is invalid you must enter two coordinates (ex : \"1.25,1.45\").");
					error = true ;
				}
				st.nextToken("\"");
				if(st.hasMoreTokens()){	
					System.err.println("The command \"registerCourier <firstName> <lastName> <username> <password> <address>\" has only 5 parameters.");
					error = true ;
				}
				if(!error){
					currentManager.addUser("courier", courierName, courierSurname, courierUserName, courierPassword);
					try{
						((Courier)currentManager.getMyFoodora().findUserByName(courierName)).setPosition(new Position(courierX,courierY));
						System.out.println("The courier has been registered. Here are its properties : ") ;
						System.out.println(currentManager.getMyFoodora().findUserByName(courierName));
					}catch(UserNotFoundException e){
						System.out.println("Error while creating the user.");
					}
				}
				return "next" ;
			case("setDeliveryPolicy"):
				st.nextToken("\"");
				String delPolicyName = st.nextToken("\"");
				switch(delPolicyName){
				case("fairOccupation"):case("fastest"):
					break ;
				default :
					System.err.println("The <delPolicyName> parameter must be \"fairOccupation\" or \"fastest\".");
					error = true ;
				}
				if(st.hasMoreTokens()){	
					System.err.println("The command \"setDeliveryPolicy <delPolicyName>\" has only 1 parameter.");
					error = true ;
				}
				if(!error){
					currentManager.setDeliveryPolicy(delPolicyName);
				}
				return "next" ;
			case("meetTargetProfit"):
				st.nextToken("\"");
				String profitPolicyName = st.nextToken("\"");
				switch(profitPolicyName){
				case("serviceFee"):case("markup"):case("deliveryCost"):
					break ;
				default :
					System.err.println("The <profitInfoName> parameter must be \"deliveryCost\", \"markup\" or \"serviceFee\".");
					error = true ;
				}
				st.nextToken("\"");
				String targetProfitString = st.nextToken("\"");
				double targetProfit = 0 ;
				try{
					targetProfit = Double.parseDouble(targetProfitString) ;
				}catch(NumberFormatException e){
					System.err.println("The <targetProfit> parameter is invalid : you must enter a profit value (ex : \"2142.24\").");
					error = true ;
				}
				if(st.hasMoreTokens()){	
					System.err.println("The command \"meetTargetProfit <profitInfoName> <targetProfit> \" has only 2 parameters.");
					error = true ;
				}
				if(!error){
					try{
						System.out.println("You can reach a profit of "+targetProfit+" by changing the "+profitPolicyName+" to "+currentManager.meetTargetProfit(profitPolicyName,targetProfit)+".");
					}catch(NonReachableTargetProfitException e){
						System.err.println("It is impossible to reach the profit value "+targetProfit+".");
					}
				}
				return "next" ;
			case("setProfitInfo"):
				st.nextToken("\"");
				String profitInfoName = st.nextToken("\"");
				switch(profitInfoName){
				case("serviceFee"):case("markup"):case("deliveryCost"):
					break ;
				default :
					System.err.println("The <profitInfoName> parameter must be \"deliveryCost\", \"markup\" or \"serviceFee\".");
					error = true ;
				}
				st.nextToken("\"");
				String valueString = st.nextToken("\"");
				double value = 0 ;
				try{
					value = Double.parseDouble(valueString) ;
				}catch(NumberFormatException e){
					System.err.println("The <value> parameter is invalid : you must enter a value (ex : \"2142.24\").");
					error = true ;
				}
				if(st.hasMoreTokens()){	
					System.err.println("The command \"setProfitInfo <profitInfoName> <value>\" has only 2 parameters.");
					error = true ;
				}
				if(!error){
					currentManager.setProfitInfo(profitInfoName, value);
					System.out.println("The "+profitInfoName+" value has been setted to "+value+".");
				}
				return "next" ;
			case("associateCard"):
				st.nextToken("\"");
				String customerUsername = st.nextToken("\"");
				st.nextToken("\"");
				String cardType = st.nextToken("\"");
				switch(cardType){
				case("basic"):case("point"):case("lottery"):
					break ;
				default :
					System.err.println("The <cardType> parameter must be \"basic\", \"point\" or \"lottery\".");
					error = true ;
				}
				if(st.hasMoreTokens()){	
					System.err.println("The command \"associateCard <userName> <cardType>\" has only 2 parameters.");
					error = true ;
				}
				if(!error){
					try{
						User fidelUser = myFoodora.findUserByUsername(customerUsername);
						if(fidelUser.getUserType().equals("customer")){
							Customer fidelCustomer = (Customer)fidelUser ;
							fidelCustomer.registerFidelityCard(cardType);
							System.out.println("The user of username \""+customerUsername+"\" has now a "+cardType+" fidelity card.");
						}else{
							System.err.println("The user of username \""+customerUsername+"\" is not a customer.");
						}
					}catch(UserNotFoundException e){
						System.err.println("The user of username \""+customerUsername+"\" does not exist.");
					}
				}
				return "next" ;
			case("showCourierDeliveries"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"showCourierDeliveries <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("Here are the activated couriers of the platform :");
					Courier mostActiveCourier = new Courier("","","","") ;
					ArrayList<Integer> activatedCourier = new ArrayList<Integer>() ;
					while (mostActiveCourier!=null){
						mostActiveCourier = currentManager.mostActiveCourier();
						if(mostActiveCourier!=null){
						activatedCourier.add(mostActiveCourier.getUniqueID());
							try{
								currentManager.deactivateUser(mostActiveCourier.getUniqueID());
							}catch(UserNotFoundException e){
								System.err.println("Error while displaying the couriers.");
							}						
							System.out.println(mostActiveCourier);
						}else{
							for(int i:activatedCourier){
								try{
									currentManager.activateUser(i);
								}catch(UserNotFoundException e){
									System.err.println("Error while displaying the couriers.");
								}	
							}
						}
					}
					
				}
				return "next" ;
			case("showRestaurantTop"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"showRestaurantTop <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("Here are the activated restaurants of the platform :");
					Restaurant mostSellingRestaurant = new Restaurant("","","","") ;
					ArrayList<Integer> activatedRestaurants = new ArrayList<Integer>() ;
					while (mostSellingRestaurant!=null){
						mostSellingRestaurant = currentManager.mostSellingRestaurant();
						if(mostSellingRestaurant!=null){
						activatedRestaurants.add(mostSellingRestaurant.getUniqueID());
							try{
								currentManager.deactivateUser(mostSellingRestaurant.getUniqueID());
							}catch(UserNotFoundException e){
								System.err.println("Error while displaying the restaurants.");
							}						
							System.out.println(mostSellingRestaurant);
						}else{
							for(int i:activatedRestaurants){
								try{
									currentManager.activateUser(i);
								}catch(UserNotFoundException e){
									System.err.println("Error while displaying the restaurants.");
								}	
							}
						}
					}
					
				}
				return "next" ;
			case("showCustomers"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"showCustomers <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("Here are the activated customers of the platform :");
					for (User user : myFoodora.getUsers()){
						if((user.getUserType().equals("customer")&&(user.isActivated()))){
							System.out.println(user);							
						}
					}
					
				}
				return "next" ;
			case("showMenuItem"):
				st.nextToken("\"");
				restaurantName = st.nextToken("\"");
				if(st.hasMoreTokens()){	
					System.err.println("The command \"showMenuItem <restaurantName>\" has only 1 parameter.");
					error = true ;
				}
				try{
					if(!myFoodora.findUserByName(restaurantName).getUserType().equals("restaurant")){
						System.err.println("The user named \""+restaurantName+"\" is not a restaurant.");
						error = true ;
					}
				}catch(UserNotFoundException e){
					System.err.println("The restaurant named \""+restaurantName+"\" does not exist.");
					error = true ;
				}
				if(!error){
					System.out.println("Here is the menu of the restaurant \""+restaurantName+"\" :");
					try{
						((Restaurant)myFoodora.findUserByName(restaurantName)).displayMenu();
					}catch(UserNotFoundException e){
						System.err.println("The restaurant named \""+restaurantName+"\" does not exist.");
					}
					
				}
				return "next" ;
			case("showTotalProfit"):
				if(st.hasMoreTokens()){	
					st.nextToken("\"");
					int day1 = 0 ; int month1 = 0 ; int year1 = 0 ;
					int day2 = 0 ; int month2 = 0 ; int year2 = 0 ;
					String stringDay1 = st.nextToken("\"/");
					String stringMonth1 = st.nextToken("/");
					String stringYear1 = st.nextToken("\"/");
					st.nextToken("\"");
					String stringDay2 = st.nextToken("\"/");
					String stringMonth2 = st.nextToken("/");
					String stringYear2 = st.nextToken("\"/");
					if(st.hasMoreTokens()){
						System.err.println("The command \"showTotalProfit <>\" cannot have parameters.");
						error = true ;
					}
					try{
						day1 = Integer.parseInt(stringDay1);
						day2 = Integer.parseInt(stringDay2);
						month1 = Integer.parseInt(stringMonth1);
						month2 = Integer.parseInt(stringMonth2);
						year1 = Integer.parseInt(stringYear1);
						year2 = Integer.parseInt(stringYear2);
						if((day1<1)||(day1>31)||(day2<1)||(day2>31)||(month1<1)||(month1>12)||(month2<1)||(month2>12)){
							System.err.println("Each date parameter must be in format \"DD/MM/YYYY\" (ex : \"28/01/2016\".");
							error = true ;
						}
					}catch(NumberFormatException e){
						System.err.println("Each date parameter must be in format \"DD/MM/YYYY\" (ex : \"28/01/2016\".");
						error = true ;
					}
					Calendar start = Calendar.getInstance() ;
					start.set(year1,month1-1,day1);
					Calendar end = Calendar.getInstance() ;
					end.set(year2,month2-1,day2);
					if(start.compareTo(end)>0){
						System.err.println("The startDate must be before the endDate.");
						error = true ;
					}
					if(!error){
						System.out.println("The total profit during this period is :");
						System.out.println(currentManager.getMyFoodora().totalProfit(start, end));	
					}
				}else{
					Calendar start = Calendar.getInstance() ;
					start.set(0,0,0);
					Calendar end = Calendar.getInstance() ;
					if(!error){
						System.out.println("The total profit since the creation is :");
						System.out.println(currentManager.getMyFoodora().totalProfit(start, end));	
					}
				}
				return "next" ;
			case("logout"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"logout <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					return "logout" ;
				}
				return "next" ;	
			default :
				System.err.println("The command "+commande+" does not exist.");	
			}
			return "next" ;
		}catch(NoSuchElementException e){
			System.err.println("Invalid number of parameters or syntax error.");
			return "next" ;
		}
		/*switch (input){
		case("help"):
			System.out.println("\"activate\" : activate an account\n"
					+ "\"deactivate\" : deactivate an account\n"
					+ "\"compute\" : compute a business value\n"
					+ "\"stat\" : display statistics\n"
					+ "\"setCurrentPolicy\" : set the current delivery policy\n"
					+ "\"meetTargetProfit\" : meet a target profit py changing a business value\n"
					+ "\"logout\" : log out\n"
					+ "\"close\" : close MyFoodora");
			break;			
		case("activate"):
			System.out.println("Here is a list of all the users of MyFoodora :");
			currentManager.getMyFoodora().displayUsers();
			while(!input.equals("exit")){
				System.out.println("Please enter the ID of the user you want to activate or type \"exit\":");
				input = sc.next();
				try{
					int id = Integer.parseInt(input) ;
					currentManager.activateUser(id);
					System.out.println("You have activated the account of this user :\n"+currentManager.getMyFoodora().findUserByUniqueID(id));
					return "next" ;
				}catch(UserNotFoundException e){
					System.err.println("This User ID is not in MyFoodora.");
				}catch(NumberFormatException e){
					System.err.println("You must enter an ID");
				}
			}
			break ;
		case("deactivate"):
			System.out.println("Here is a list of all the users of MyFoodora :");
			currentManager.getMyFoodora().displayUsers();
			while(!input.equals("exit")){
				System.out.println("Please enter the ID of the user you want to deactivate or type \"exit\":");
				input = sc.next();
				try{
					int id = Integer.parseInt(input) ;
					currentManager.deactivateUser(id);
					System.out.println("You have deactivated the account of this user :\n"+currentManager.getMyFoodora().findUserByUniqueID(id));
					return "next" ;
				}catch(UserNotFoundException e){
					System.err.println("This User ID is not in MyFoodora.");
				}catch(NumberFormatException e){
					System.err.println("You must enter an ID");
				}
			}
			break ;
		case("compute"):
			while(!input.equals("close")){
				System.out.println("Do you want to compute a \"totalIncome\", a \"totalProfit\" or an \"averageIncomePerCustomer\" ?");
				input = sc.next() ;
				switch (input){
				case("totalIncome"):case("totalProfit"):case("averageIncomePerCustomer"):
					String input2 = "" ;
					while(!input2.equals("exit")){
						SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
						try{
							System.out.println("Please enter the first day of the period for computing in format DD MM YYYY or type \"exit\":");
							sc.nextLine();
							input2 = sc.nextLine();
							Calendar date1 = Calendar.getInstance() ;
							date1.setTime(sdf.parse(input2)) ;
							System.out.println("Please enter the last day of the period for computing in format DD MM YYYY or type \"exit\":");
							input2 = sc.nextLine();
							Calendar date2 = Calendar.getInstance() ;
							date2.setTime(sdf.parse(input2)) ;
							System.out.println("The "+input+" during the period between "+date1.get(Calendar.DAY_OF_MONTH) + " " + date1.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + date1.get(Calendar.YEAR)
							+" and "+date2.get(Calendar.DAY_OF_MONTH) + " " + date2.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + date2.get(Calendar.YEAR)+" is :\n");
							switch(input){
							case("totalIncome"):
								System.out.println(currentManager.totalIncome(date1, date2));
								return "next" ;
							case("totalProfit"):
								System.out.println(currentManager.totalProfit(date1, date2));
								return "next" ;
							case("averageIncomePerCustomer"):
								System.out.println(currentManager.averageIncomePerCostumer(date1, date2));
								return "next" ;
							}
						}catch(ParseException e){
							System.err.println("You must enter a date in DD MM YYYY format.");
						}
					}
					break ;
				case("disconnect"):
					return "disconnect" ;
				case("close"):
					return "close" ;
				default:
					System.out.println("\nThis choice is not available, please try again \n");
				}
			}
			break ;
		case("stat"):
			System.out.println("He are MyFoodora's stats :");
			System.out.println("The most selling restaurant :\n"+currentManager.mostSellingRestaurant());
			System.out.println("The least selling restaurant :\n"+currentManager.leastSellingRestaurant());
			System.out.println("The most active courier :\n"+currentManager.mostActiveCourier());
			System.out.println("The least active courier :\n"+currentManager.leastActiveCourier());
			return "next" ;
		case("setCurrentPolicy"):
			while(!input.equals("close")){
				System.out.println("Do you want to deliver considering the \"fairOccupation\" or the \"fastest\" delivery ?");
				input = sc.next() ;
				switch (input){
				case("fairOccupation"):case("fastest"):
					currentManager.setDeliveryPolicy(input);
					break ;
				case("disconnect"):
					return "disconnect" ;
				case("close"):
					return "close" ;
				default:
					System.out.println("\nThis choice is not available, please try again \n");
				}
			}
			break ;
		case("meetTargetProfit"):
			while(!input.equals("close")){
				System.out.println("Do you want to change considering the \"markup\" value, the \"serviceFee\" value or the \"deliveryCost\" value ?");
				input = sc.next() ;
				switch (input){
				case("markup"):case("seviceFee"):case("deliveryCost"):
					double profit ;
					boolean profitValidated = false ;
					while(!profitValidated){
						System.out.println("Enter the target profit :");
						try{
							profit = sc.nextDouble() ;
							currentManager.meetTargetProfit(input, profit);
							profitValidated = true ;
							System.out.println("The business value has been modified.");
							return "next" ;
						}catch(InputMismatchException e){
							System.err.println("You must enter a profit value.");
							sc.next();
						}catch(NonReachableTargetProfitException e){
							System.err.println("The target profit is unreachable.");
						}
					}
					break ;
				case("disconnect"):
					return "disconnect" ;
				case("close"):
					return "close" ;
				default:
					System.out.println("\nThis choice is not available, please try again \n");
				}
			}
			break ;
		case("logout"):
			return "logout" ;
		case("close"):
			return "close" ;
		default:
			System.out.println("This choice is not available, please try again \n");
			break;
		}
		*/
	}
	
	/**
	 * Works with all the possible commands that a restaurant can use
	 * @return "next" to go to the next command, "disconnect" or "close".
	 */
	private static String workRestaurant(){
		Restaurant currentRestaurant = (Restaurant)currentUser ;
		String commande ;
		boolean error = false ;
		try{
			commande = st.nextToken() ;
			switch (commande){
			case("help"):
				System.out.println("\"showMenuItem <>\" : display your menu\n"
						+ "\"addDishRestaurantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>\" : add a new dish to your menu\n"
						+ "\"manage\" : manage your discounts\n"
						+ "\"disconnect\" : change user\n"
						+ "\"close\" : close MyFoodora");
				return "next" ;
			case("showMenuItem"):
				if(st.hasMoreTokens()){
					if(st.hasMoreTokens()){	
						System.err.println("The command \"showMenuItem <>\" cannot have parameters.");
						error = true ;
					}
				}
				if(!error){
					System.out.println("Here is your menu :");
					currentRestaurant.displayMenu();
				}
				return "next" ;
			case("addDishRestaurantMenu"):
				st.nextToken("\"");
				String dishName = st.nextToken("\"");
				st.nextToken("\"");
				String dishCategory = st.nextToken("\"");
				switch(dishCategory){
				case("starter"):case("mainDish"):case("dessert"):
					break ;
				default :
					System.err.println("The <dishCategory> parameter is wrong. The possible values are \"starter\", \"mainDish\" and \"dessert\".");
					error = true ;
				}
				st.nextToken("\"");
				String foodCategory = st.nextToken("\"");
				switch(foodCategory){
				case("standard"):case("vegetarian"):case("glutenFree"):
					break ;
				default :
					System.err.println("The <foodCategory> parameter is wrong. The possible values are \"standard\", \"vegetarian\" and \"glutenFree\".");
					error = true ;			
				}
				st.nextToken("\"");
				String priceString = st.nextToken("\"");
				double price = 0;
				try{
					price = Double.parseDouble(priceString) ;
				}catch(NumberFormatException e){
					System.err.println("The price parameter is invalid.");
					error = true ;
				}
				if(price<=0){
					System.err.println("The price parameter must be positive.");
					error = true ;
				}
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					currentRestaurant.addDish(dishCategory, dishName, price, foodCategory);
					System.out.println("The "+foodCategory+" "+dishCategory+" "+dishName+" has been added to your menu for "+price+" euros.");
				}
				return "next" ;
			case("createMeal"):
				st.nextToken("\"");
				String mealName = st.nextToken("\"");
				st.nextToken("\"");
				String mealCategory = st.nextToken("\"");
				switch(mealCategory){
				case("full"):case("half"):case("glutenFree"):
					break ;
				default :
					System.err.println("The <mealCategory> parameter is wrong. The possible values are \"full\" and \"half\" ");
					error = true ;
				}
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					currentRestaurant.addMeal(mealCategory, mealName);
					System.out.println("The "+mealCategory+" "+mealName+" has been created and added to your menu.");
				}
				return "next" ;
			case("addDish2Meal"):
				st.nextToken("\"");
				String dName = st.nextToken("\"");
				st.nextToken("\"");
				String mName = st.nextToken("\"");
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					try{
						currentRestaurant.addDish2Meal(mName,dName);
						System.out.println("Here is the updated meal");
						System.out.println(currentRestaurant.findMealByName(mName));
					}
					catch(NoPlaceInMealException e){
						System.err.println("You cannot add the dish "+dName+ " in the meal "+mName+".");
					}
					catch(FoodItemNotFoundException e){
						System.err.println("The dish "+dName+" or the meal"+mName+" do not exist.");
					}
				}
				return "next" ;
			case("showMeal"):
				st.nextToken("\"");
				mealName = st.nextToken("\"");
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					try{
						System.out.println("Here is the "+mealName+" :");
						System.out.println(currentRestaurant.findMealByName(mealName));
					}
					catch(FoodItemNotFoundException e){
						System.err.println("The meal"+mealName+" does not exist.");
					}
				}
				return "next" ;
			case("setSpecialOffer"):
				st.nextToken("\"");
				mealName = st.nextToken("\"");
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					try{
						currentRestaurant.setMealOfTheWeek(mealName, myFoodora);
						System.out.println("Here is the new meal of the week :");
						System.out.println(currentRestaurant.getMenu().getMealOfTheWeek());
					}
					catch(FoodItemNotFoundException e){
						System.err.println("The meal"+mealName+" does not exist.");
					}
				}
				return "next" ;
			case("logout"):
				if(st.hasMoreTokens()){
					if(st.hasMoreTokens()){	
						System.err.println("The command \"logout <>\" cannot have parameters.");
						return "next" ;
					}
				}
				return "logout" ;				
			default :
				System.err.println("The command "+commande+" does not exist.");	
			}
			return "next" ;
		}catch(NoSuchElementException e){
			System.err.println("Invalid number of parameters or syntax error.");
			return "next" ;
		}
	}
}