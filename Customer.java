package admincustomerassignment;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Customer implements CustomerInterface {
	
	String nameUser;//variable to store user name from user
	String Userpassword;//variable to store password from user
	Scanner sc = new Scanner(System.in);//creating scanner object
	BankDetails bd = new BankDetails();//creating BankDetails class object
	
	public void getUserDetails() {
		
		System.out.println("Enter User Name of User...");
		nameUser = sc.next();//reading user name
		System.out.println("Enter Password of User...");
		Userpassword = sc.next();//reading password
		
	}//end of getUserDetails() method
	
	public boolean validateCustomer() {
		if(userName.equals(nameUser)) {
			if(userPassword.equals(Userpassword)) {
				System.out.println("Customer Login Successful...\n\nWelcome");//printing message
				return true;
			}//end of inner if
			else {
				System.out.println("Incorrect password! \nCustomer Login Unsuccessful...");//printing message
				return false;
			}//end of inner else
		}//end of outer if
		else {
			System.out.println("Incorrect User Name! \nCustomer Login Unsuccessful...");//printing message
			return false;
		}//end of outer else
	}//end of validateCustomer method
	
	public void customerLogin() {
		/*Creating a set reference for the table of item details created by administrator*/
		Set<Map.Entry<Integer, List<String>>> setView = Administrator.adminTable.entrySet();
		for(Map.Entry<Integer, List<String>> entry : setView) {//printing table
			System.out.print(entry.getKey() + "\t");
			List<String> value = entry.getValue();
			for(String str : value) {
				System.out.print(str + "\t");
			}//end of inner for loop
			System.out.println("");
		}//end of outer for loop
		System.out.println("0-------> Pay Bill");//printing option to pay bill
	}//end of customerLogin method
	
	public double chooseItems() {
		System.out.println("You can buy atmost 3 items!\nEnter number of items to buy!");
		int itemCount = sc.nextInt();//reading number of items to buy
		while(itemCount > 3 || itemCount < 1 ||itemCount > Administrator.adminTable.size()) {
			System.out.println("Enter a valid item count(should less than the available number of items!)...");
			itemCount = sc.nextInt();
		}//end of while
		double itemAmount = 0;
		for(int incr = 0; incr < itemCount; incr++) {
			System.out.println("Enter item Number : ");
			int item = sc.nextInt();//reading items to buy
			while(item > Administrator.adminTable.size()) {
				System.out.println("Enter a valid item number(should less than the available number of items!)...");
				item = sc.nextInt();
			}//end of while
			int quantity;
			//showing number of available item
			int availableQuantity = Integer.parseInt(Administrator.adminTable.get(Integer.valueOf(item)).get(2));
			do {
				System.out.println("Available item quantity is : " + availableQuantity);
				System.out.println("Enter item Quantity (Should less than or equal to available quantity) : ");
				quantity = sc.nextInt();//reading number of items to buy
			}while(availableQuantity <= quantity);//checking that quantity is available or not
			//calculating total price for all items
			itemAmount += (double) (quantity * Integer.parseInt(Administrator.itemPrice.get(item - 1)));
		}
		return itemAmount;
	}//end of chooseItems method
	
	public double netAmount(double itemPrice) {
		if(itemPrice > 5000) {
			return itemPrice - (itemPrice * 0.2) + (itemPrice * 0.15);//calculating net amount for price greater than 5000
		}//end of if
		else {
			return itemPrice + (itemPrice * 0.15);//calculating net amount for price less than or equal to 5000
		}//end of else
	}//end of netAmount method
	
	public void billPayment(double spent) {
		System.out.println("\n\n1) Login\n2) New User\n3) Exit");
		int choice = sc.nextInt();//reading choice
		switch(choice) {
			case 1:
				int repeat = 3;
				while(repeat > 0) {
					System.out.println("Your account balance is " + bd.login());//customer login
					if(bd.isExist()) {//checking account exist or not
						if(bd.updateBalance(spent)) {//updating user balance
							System.out.println("Bill paid Successfully... \n See you next time!");
						}else {
							System.out.println("Not enough balance in the account...");
						}
						break;
					}
					repeat--;
				}
				break;
			case 2:
				System.out.println("Your account balance is " + bd.newLogin());//customer login
				if(bd.updateBalance(spent)) {//updating user balance after pay bill
					System.out.println("Bill paid Successfully... \n See you next time!");
				}else {
					System.out.println("Not enough balance in the account...");
				}//end of else
				break;
			case 3:
				System.out.println("See you next time! \n Successfully exited!");
				System.exit(0);//exiting application
				break;
			default :
				System.out.println("Choose a valid method!");
				billPayment(spent);
		}//end of switch
	}//end of billPayment method

}//end of Customer class
