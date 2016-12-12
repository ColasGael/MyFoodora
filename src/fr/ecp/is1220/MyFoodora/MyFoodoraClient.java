package fr.ecp.is1220.MyFoodora;

import java.util.Scanner;

public class MyFoodoraClient {

	public static void main(String[] args) {
		MyFoodora myFoodora = MyFoodora.loadMyFoodora();
		
		myFoodora.displayUsers();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome on the MyFoodora application \n"
							+ "Type \"close\" to quit \n");
		
		System.out.println("If you are a new user please \"register\" \n"
							+ "If not please \"login\" \n");
		String input = sc.next();
				
		while (!input.equals("close")){
		
			switch (input){
				case("register"):
					//TO DO
					System.out.println("You have successfully created an account !\n"
							+ "Now type \"login\" to log into the system \n or \"close\" to quit \n");
					break;
					
				case("login"):
					System.out.println("Please enter your username : \n");
					String username = sc.next();
					System.out.println("Please enter your password : \n");
					String password = sc.next();
					try{
						User user = myFoodora.login(username,password);
						String userType = user.getUserType();
						System.out.println("You have successfully logged in the system !\n"
								+ "Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
						input = sc.next();
						
						switch (userType){
							case("customer"):
								while (!input.equals("disconnect")){
									switch (input){
										case("help"):
											break;
										//TODO
										default:
											System.out.println("This choice is not available, please try again \n");
											break;
									}
									System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
									input = sc.next();
								}
								break;
							case("courier"):
								while (!input.equals("disconnect")){
									switch (input){
										case("help"):
											break;
										//TODO
										default:
											System.out.println("This choice is not available, please try again \n");
											break;
									}
									System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
									input = sc.next();
								}
								break;
							case("restaurant"):
								while (!input.equals("disconnect")){
									switch (input){
										case("help"):
											break;
										//TODO
										default:
											System.out.println("This choice is not available, please try again \n");
											break;
									}
									System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
									input = sc.next();
								}
								break;
							case("manager"):
								while (!input.equals("disconnect")){
									switch (input){
										case("help"):
											break;
										//TODO
										default:
											System.out.println("This choice is not available, please try again \n");
											break;
									}
									System.out.println("Type \"help\" for a list of available commands or \"disconnect\" to be disconnected \n");
									input = sc.next();
								}
								break;
							
						}									
								
					}catch(IdentificationIncorrectException e){
						System.out.println("Sorry " + e.getMessage() + "\n"
								+ "Please try again \n");
					}catch(AccountDeactivatedException e){
						System.out.println("Sorry " + e.getMessage() + "\n"
								+ "Please call a manager : +33 1 41 13 15 79 \n");
						input = "close";
					/*}finally{
						break;*/
					}
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

		System.out.println("Thank you for your visit ! \n Goodbye !\n");
		sc.close();
		MyFoodora.saveMyFoodora(myFoodora);
	}

}
