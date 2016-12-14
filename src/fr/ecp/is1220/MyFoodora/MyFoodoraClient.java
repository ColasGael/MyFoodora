package fr.ecp.is1220.MyFoodora;

import java.util.Scanner;

public class MyFoodoraClient {
	
	private static Scanner sc ;
	private static String input ;
	private static MyFoodora myFoodora ;

	public static void main(String[] args) {
		myFoodora = MyFoodora.loadMyFoodora();
		
		myFoodora.displayUsers();
		
		sc = new Scanner(System.in);
		
		System.out.println("Welcome on the MyFoodora application \n"
							+ "Type \"close\" to quit \n");
		
		System.out.println("If you are a new user please \"register\" \n"
							+ "If not please \"login\" \n");
		
		input = sc.next();
				
		while (!input.equals("close")){		
			switch (input){
				case("register"):
					System.out.println("You have successfully created an account !\n"
							+ "Now type \"login\" to log into the system \n or \"close\" to quit \n");
					break;					
				case("login"):
					String userType = login() ;
					if(userType!=null){
						while (!input.equals("disconnect")){
							work(userType);
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
	
	private static String login(){
		System.out.println("Please enter your username : \n");
		String username = sc.next();
		System.out.println("Please enter your password : \n");
		String password = sc.next();
		String userType = null ;
		try{
			User user = myFoodora.login(username,password);
			userType = user.getUserType();
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
	
	private static void work(String userType){		
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
	}

}
