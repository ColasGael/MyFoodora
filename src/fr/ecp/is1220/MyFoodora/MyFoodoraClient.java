package fr.ecp.is1220.MyFoodora;

import java.util.Scanner;
import java.util.InputMismatchException ;

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
			input = sc.next();					
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
	
	private static String workCourier(){
		return "next" ;
	}
	
	private static String workManager(){
		return "next" ;
	}
	
	private static String workRestaurant(){
		Restaurant currentRestaurant = (Restaurant)currentUser ;
		switch (input){
		case("help"):
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
									System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
									input = sc.next();
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
											System.out.println("\nType \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
											input = sc.next() ;
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
					break;
				case("add"):
						System.out.println("Here is your menu :");
						currentRestaurant.displayMenu();
						System.out.println("Which dish do you want to add to a meal ? Enter its name :");
						sc.next();
						String mealName = sc.nextLine();
						System.out.println("Which meal do you want to complete ? Enter its name :");
						sc.next();
						String dishName = sc.nextLine();
						try{
							currentRestaurant.addDish2Meal(mealName, dishName);
						}catch(NullPointerException e){
							System.err.println("Your dish name and meal name are not valid.");
						}
					break;
				case("disconnect"):
					return "disconnect" ;
				case("close"):
					return "close" ;
				default:
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
		System.out.println("\nType \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
		input = sc.next() ;
		return "next" ;
	}
}
