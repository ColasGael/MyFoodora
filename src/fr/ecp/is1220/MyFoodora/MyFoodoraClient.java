package fr.ecp.is1220.MyFoodora;

import java.util.Scanner;

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
							
						case("disconnect"):
							break disconnectLoop ;
						case("close"):
							break closeLoop ;
						}
					}
				}
				break;
				case("close"):
					break;
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
			switch (input){
			case("help"):
				break;
			// TODO
			default:
				System.out.println("This choice is not available, please try again \n");
				break;
			}
		System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
		break;
		case("courier"):
			switch (input){
			case("help"):
				break;
			//TODO
			default:
				System.out.println("This choice is not available, please try again \n");
				break;
			}
		System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
		break;
		case("restaurant"):
			Restaurant currentRestaurant = (Restaurant)currentUser ;
			switch (input){
			case("help"):
				break;
			case("edit"):
				while(!input.equals("close")){
					System.out.println("Type \"add\" to add an item or \"remove\" to remove an item :");
					input = sc.next() ;
					switch (input){
					case("add"):
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
										input = sc.next();
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
		System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
		break;
		case("manager"):
			switch (input){
			case("help"):
				break;
			//TODO
			default:
				System.out.println("This choice is not available, please try again \n");
				break;
			}
		System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
		break;

		}
		input = sc.next() ;
		return "next" ;
	}

}
