package fr.ecp.is1220.MyFoodora;

import java.util.Scanner;
import java.util.InputMismatchException ;
import java.util.Locale;
import java.text.ParseException ;
import java.util.Calendar ;
import java.text.SimpleDateFormat ;

public class MyFoodoraClient {

	private static Scanner sc ;
	private static String input ;
	private static MyFoodora myFoodora ;
	private static User currentUser ;

	public static void main(String[] args) {
		myFoodora = MyFoodora.loadMyFoodora();

		myFoodora.displayUsers();

		sc = new Scanner(System.in);

		System.out.println("Welcome on the MyFoodora application \n"
				+ "Type \"close\" to quit \n");

		System.out.println("If you are a new user please \"register\" \n"
				+ "If not please \"login\" \n");

		input = sc.next();

		closeLoop :
			while (!input.equals("close")){		
				switch (input){
				case("register"):
					System.out.println("You have successfully created an account !\n"
							+ "Now type \"login\" to log into the system \n or \"close\" to quit \n");
					break;					
				case("login"):
					String userType = login() ;
					if(userType!=null){
						disconnectLoop :
						while (!input.equals("disconnect")){
							String workInput = work(userType);
							switch(workInput){
							case("next"):
								break ;
							case("disconnect"):
								break disconnectLoop ;
							case("close"):
								break closeLoop ;
							}
						}
					}
					break;
				case("close"):
					break closeLoop ;
				default:
					System.out.println("This choice is not available, please try again \n");
					break;
				}
				System.out.println("If you are a new user please \"register\" \n"
						+ "If not please \"login\" \n");
				input = sc.next();
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
		System.out.println("Please enter your username : ");
		String username = sc.next();
		System.out.println("Please enter your password : ");
		String password = sc.next();
		String userType = null ;
		try{
			currentUser = myFoodora.login(username,password);
			userType = currentUser.getUserType();
			System.out.println("You have successfully logged in the system !\n");
			System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");				
		}catch(IdentificationIncorrectException e){
			System.out.println("Sorry " + e.getMessage() + "\n"
					+ "Please try again \n");
		}catch(AccountDeactivatedException e){
			System.out.println("Sorry " + e.getMessage() + "\n"
					+ "Your account has been deactivated : Please call a manager : +33 1 41 13 15 79 \n");
			input = "close";
		}finally{			
		}
		return userType ;
	}

	/**
	 * Let the user use the platform with the commands he can use.
	 * @param userType
	 */
	private static String work(String userType){		
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
		System.out.println("\nType \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
		Courier currentCourier = (Courier)currentUser ;
		System.out.println(currentCourier.getBoard());
		input = sc.next();	
		switch (input){
		case("help"):
			System.out.println("\"changeState\" : set state to off duty or on duty\n\"changePosition\" : change your position\n\"accept\" : accept to delivery an order of your board\n\"refuse\" : refuse to delivery an order of your board\n\"disconnect\" : change user\n\"close\" : close MyFoodora");
			break;
		case("changeState"):
			while(!input.equals("close")){
				System.out.println("You are currently "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
				System.out.println("Do you want to change to \"onDuty\" or \"offDuty\" ?");
				input = sc.next() ;
				switch (input){
				case("onDuty"):case("offDuty"):
					currentCourier.setOnDuty(input.equals("onDuty"));
					System.out.println("You are now "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
					return "next" ;
				case("disconnect"):
					return "disconnect" ;
				case("close"):
					return "close" ;
				default:
					System.out.println("\nThis choice is not available, please try again \n");
				}
			}
			break;
		case("changePosition"):
			while(!input.equals("close")){
				System.out.println("Your position is"+(currentCourier.getPosition()));
				Position pos = new Position(0,0) ;
				try{
					System.out.println("Enter the x coordonate of your new position :");
					pos.setX(sc.nextDouble()) ;
					System.out.println("Enter the y coordonate of your new position :");
					pos.setY(sc.nextDouble()) ;
					currentCourier.setPosition(pos);
					System.out.println("Your new position is"+(currentCourier.getPosition()));
					return "next" ;
				}catch(InputMismatchException e){
					System.err.println("You must enter a coordonate.");
					sc.next();
				}
			}
			break ;
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
		case("disconnect"):
			return "disconnect" ;
		case("close"):
			return "close" ;
		default:
			System.out.println("This choice is not available, please try again \n");
			break;
		}
		return "next" ;
	}
	
	private static String workManager(){
		Manager currentManager = (Manager)currentUser ;
		input = sc.next();	
		switch (input){
		case("help"):
			System.out.println("\"activate\" : activate an account\n\"deactivate\" : deactivate an account\n\"compute\" : compute a business value\n\"stat\" : display statistics\n\"setCurrentPolicy\" : set the current delivery policy\n\"disconnect\" : change user\n\"close\" : close MyFoodora");
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
		case("disconnect"):
			return "disconnect" ;
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
		System.out.println("\nType \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
		Restaurant currentRestaurant = (Restaurant)currentUser ;
		input = sc.next();	
		switch (input){
		case("help"):
			System.out.println("\"displayMenu\" : display your menu\n\"edit\" : edit your menu\n\"manage\" : manage your discounts\n\"disconnect\" : change user\n\"close\" : close MyFoodora");
			break;
		case("displayMenu"):
			System.out.println("Here is your menu :");
			currentRestaurant.displayMenu();
			break;
		case("edit"):
			while(!input.equals("close")){
				System.out.println("Type \"create\" to add a new item, \"add\" to add a dish to a meal, or \"remove\" to remove an item :");
				input = sc.next() ;
				switch (input){
				case("create"):
					while(!input.equals("close")){
						System.out.println("Do you want to add a \"meal\" or a \"dish\" ?") ;
						input = sc.next();
						switch(input){
						case("meal"):
							while(!input.equals("close")){
								System.out.println("Do you want to add a \"full\" meal or a \"half\" meal ?");
								input = sc.next();
								switch(input){
								case("full"): case("half"):
									System.out.println("Enter the name of your "+input+" meal :");
									String mealType = input ;
									sc.nextLine();
									input = sc.nextLine();
									currentRestaurant.addMeal(mealType,input);
									System.out.println("The "+mealType+" meal \""+input+"\" has been added to your menu.");
									return "next" ;
								case("disconnect"):
									return "disconnect" ;
								case("close"):
									return "close" ;
								default :
									System.out.println("This choice is not available, please try again \n");
								}
							}
							break;
						case("dish"):
							while(!input.equals("close")){
								System.out.println("Do you want to add a \"starter\" dish, a \"mainDish\" or a \"dessert\" ?");
								input = sc.next();
								switch(input){
								case("starter"): case("mainDish"): case("dessert") :
									System.out.println("Enter the name of your "+input+ " :");
									String dishType = input ;
									sc.nextLine();
									String name = sc.nextLine();
									double price = 0 ;
									while(price<=0){
										System.out.println("Enter the price of your dish :");
										try{
											price = sc.nextDouble() ;
										}catch(InputMismatchException e){
											System.err.println("You must enter a price.");
											sc.next();
										}
									}
									while(!input.equals("close")){
										System.out.println("Is your dish \"standard\", \"vegetarian\" or \"glutenFree\" ?");
										String type = sc.next();
										switch(type){
										case("standard"): case("vegetarian"): case("glutenFree") :											
											currentRestaurant.addDish(dishType,name,price,type);
											System.out.println("The "+type+" "+dishType+" \""+name+"\" has been added to your menu for "+String.valueOf(price)+" euros.");
											return "next" ;
										case("disconnect"):
											return "disconnect" ;
										case("close"):
											return "close" ;
										default :
											System.out.println("This choice is not available, please try again \n");
										}
									}
								case("disconnect"):
									return "disconnect" ;
								case("close"):
									return "close" ;
								default :
									System.out.println("This choice is not available, please try again \n");
								}
							}
							break;
						case("disconnect"):
							return "disconnect" ;
						case("close"):
							return "close" ;
						default :
							System.out.println("This choice is not available, please try again \n");
						}
					}
					break;
				case("remove"):
					while(!input.equals("close")){
						System.out.println("Do you want to remove a \"meal\" or a \"dish\" ?") ;
						input = sc.next();
						switch(input){
						case("meal"):
							System.out.println("Here are your meals :");
							System.out.println(currentRestaurant.getMenu().getMeals());
							System.out.println("\nEnter the name of the meal :");
							sc.nextLine();
							input = sc.nextLine();
							try{
								input = currentRestaurant.findMealByName(input).getName();
								currentRestaurant.removeMeal(input);
								System.out.println("The meal \""+input+"\" has been removed from your menu.");
								return "next" ;
							}catch(FoodItemNotFoundException e){
								System.err.println("This meal does not exist.");
							}
							break ;
						case("dish"):
							System.out.println("Here are your dishes :");
							System.out.println(currentRestaurant.getMenu().getMeals());
							System.out.println("\nEnter the name of the dish :");
							sc.nextLine();
							input = sc.nextLine();
							try{
								currentRestaurant.removeDish(input);
								System.out.println("The dish \""+input+"\" has been removed from your menu.");
								return "next" ;
							}catch(FoodItemNotFoundException e){
								System.err.println("This dish does not exist.");
							}
							break ;
						case("disconnect"):
							return "disconnect" ;
						case("close"):
							return "close" ;
						default :
							System.out.println("This choice is not available, please try again \n");
						}
					}
					break;
				case("add"):
						System.out.println("Here is your menu :");
						currentRestaurant.displayMenu();
						System.out.println("Which dish do you want to add to a meal ? Enter its name :");
						String dishName = sc.next()+sc.nextLine();
						System.out.println("Which meal do you want to complete ? Enter its name :");
						//sc.next();
						String mealName = sc.nextLine();
						try{
							currentRestaurant.addDish2Meal(mealName, dishName);
							System.out.println("The dish \""+dishName+"\" has been added to the meal \""+mealName+"\".");
							System.out.println("Here is your updated meal :");
							System.out.println(currentRestaurant.findMealByName(mealName));
							return "next" ;
						}catch(FoodItemNotFoundException e){
							System.err.println("The dish \""+dishName+"\" and/or the meal \""+mealName+"\" do not exist.");
						}catch(NoPlaceInMealException e){
							System.err.println("You cannot add the \""+dishName+"\" to the meal \""+mealName+"\".");
						}
					break;
				case("disconnect"):
					return "disconnect" ;
				case("close"):
					return "close" ;
				default:
					System.out.println("\nThis choice is not available, please try again \n");
				}
			}
			break;
		case("manage"):
			while(!input.equals("close")){
				System.out.println("Do you want to set the \"generic\" discount factor, the \"special\" discount factor or the \"meal\" of the week ?");
				input = sc.next();
				switch(input){
				case("generic"):
					System.out.println("Your generic discount factor is "+currentRestaurant.getMenu().getGenericDiscountFactor());
					System.out.println("Enter the new value of your generic discount factor :");
					double factor = -1 ;
					while((factor<0)||(factor>1)){
						try{
							factor = sc.nextDouble();
							currentRestaurant.setGenericDiscountFactor(factor);
						}catch(InputMismatchException e){
							System.err.println("You must enter a generic discount factor.");
							sc.next();
						}
					}
					System.out.println("Your new generic discount factor is "+currentRestaurant.getMenu().getGenericDiscountFactor());
					return "next" ;
				case("special"):
					System.out.println("Your special discount factor is "+currentRestaurant.getMenu().getSpecialDiscountFactor());
					System.out.println("Enter the new value of your special discount factor :");
					double specialFactor = -1 ;
					while((specialFactor<0)||(specialFactor>1)){
						try{
							specialFactor = sc.nextDouble();
							currentRestaurant.setSpecialDiscountFactor(specialFactor);
						}catch(InputMismatchException e){
							System.err.println("You must enter a special discount factor.");
							sc.next();
						}
					}
					System.out.println("Your new special discount factor is "+currentRestaurant.getMenu().getSpecialDiscountFactor());
					return "next" ;
				case("meal"):
					System.out.println("Your meal of the week is :"+currentRestaurant.getMenu().getMealOfTheWeek());
					System.out.println("\nHere are your meals :");
					System.out.println(currentRestaurant.getMenu().getMeals());
					System.out.println("Enter the name of the new meal of the week :");
					String mealName = null ;
					sc.nextLine();
					while(mealName == null){
						try{
							input = sc.nextLine();
							currentRestaurant.setMealOfTheWeek(input,myFoodora);
							mealName = currentRestaurant.getMenu().getMealOfTheWeek().getName() ;
						}catch(FoodItemNotFoundException e){
							System.err.println("The meal \""+input+"\" does not exist.");
						}
					}
					System.out.println("Your new meal of the week is "+currentRestaurant.getMenu().getMealOfTheWeek());
					return "next" ;
				case("disconnect"):
					return "disconnect" ;
				case("close"):
					return "close" ;
				default :
					System.out.println("This choice is not available, please try again \n");
				}
			}
			break;
		case("disconnect"):
			return "disconnect" ;
		case("close"):
			return "close" ;
		default:
			System.out.println("This choice is not available, please try again \n");
			break;
		}
		return "next" ;
	}
}
