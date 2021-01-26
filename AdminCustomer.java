package admincustomerassignment;

import java.util.Scanner;

public class AdminCustomer {
	
	Scanner sc = new Scanner(System.in);//creating scanner object reference
	Administrator admin = new Administrator();//creating Administrator object reference
	Customer customer = new Customer();//creating Customer object reference
	
	//provide main menu structure for the program
	public void mainMenu() {
			
		System.out.println("Login as...\n\n1) Administrator\n2) Customer\n3) Exit");//main menu
		
		int choice = sc.nextInt();//reading choice
		
		switch(choice) {
				
				case 1 : 
					admin.getAdminDetails();//reading user name and password
					if(!admin.validateAdmin()) {//Validating administrator user name and password
						mainMenu();
					}
					admin.createTable();//creating table from administrator
					admin.displayTable();//displaying table
					mainMenu();
					break;
				case 2 :
					customer.getUserDetails();//reading user name and password
					if(!customer.validateCustomer()) {//Validating user's user name and password
						mainMenu();
					}
					customer.customerLogin();//customer login
					double spent = customer.chooseItems();//buying product and calculating total price
					double netSpent = customer.netAmount(spent);//calculating net amount to pay
					System.out.println("Total price : " + netSpent);//showing net amount to pay
					while(true) {
						System.out.println("Enter 0 to pay Bill!");
						if(sc.nextInt() == 0) {
							customer.billPayment(netSpent);//paying bill amount
							break;
						}else {
							System.out.println("Enter valid Input \n");
						}
					}
					mainMenu();
					break;
				case 3 : 
					System.out.println("Successfully exited...");
					System.exit(0);//exiting from application
					break;
				default:
					System.out.println("Invalid Input...");//error message
					mainMenu();
			}
		}//end of mainMenu method

}//end of AdminCustomer
