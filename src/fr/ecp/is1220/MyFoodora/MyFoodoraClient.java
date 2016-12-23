package fr.ecp.is1220.MyFoodora;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException ;
import java.util.NoSuchElementException ;
import java.util.Locale;
import java.text.ParseException ;
import java.util.Calendar ;
import java.text.SimpleDateFormat ;
import java.util.StringTokenizer ;

public class MyFoodoraClient {

	private static Scanner sc ;
	private static String input ;
	private static StringTokenizer st ;
	private static MyFoodora myFoodora ;
	private static User currentUser ;

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
						if (workReturn.equals("close")){
							break closeLoop;
						}
						if (workReturn.equals("logout")){
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
						File testScenarioFile = new File(fileName);
						sc = new Scanner (testScenarioFile);
					}catch(NoSuchElementException e){
						System.err.println("Invalid number of arguments or syntax error.");
					}catch (FileNotFoundException e){
						System.err.println("File of name " + fileName + " has not been found");
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
			System.out.println(username);
			st.nextToken("\"");
			String password = st.nextToken("\"") ;
			System.out.println(password);
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
			input = "close";
		}catch(NoSuchElementException e){
			System.err.println("This choice is not available, please try again \n");
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
	
	private static String workCustomer(){
		return "next" ;
	}
	
	/**
	 * Works with all the possible commands that a restaurant can use
	 * @return "next" to go to the next command, "disconnect" or "close".
	 */
	private static String workCourier(){
		Courier currentCourier = (Courier)currentUser ;
		System.out.println(currentCourier.getBoard());
		boolean error = false ;
		String commande ;
		try{
			commande = st.nextToken() ;
			st.nextToken("\"");
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
	
	private static String workManager(){
		Manager currentManager = (Manager)currentUser ;
		input = sc.next();	
		switch (input){
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
		System.out.println("\nType \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
		return "next" ;
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
				case("standard"):case("vegetarian"):case("glutenFree"):
					break ;
				default :
					System.err.println("The <mealCategory> parameter is wrong. The possible values are \"standard\", \"vegetarian\" and \"glutenFree\".");
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