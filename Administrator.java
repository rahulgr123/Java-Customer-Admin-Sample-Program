package admincustomerassignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Administrator implements AdminInterface{
	
	String adminUserName;//variable to store user name of administrator
	String adminPass;//variable to store password of administrator
	Scanner sc = new Scanner(System.in);
	static Map<Integer, List<String>> adminTable = new HashMap<>();//Table
	static List<String> itemPrice = new ArrayList<String>();//price array
	
	//get details of administrator like user name and password
	public void getAdminDetails() {
			
			System.out.println("Enter User Name of Admin...");
			adminUserName = sc.next();//reading user name
			System.out.println("Enter Password of Admin...");
			adminPass = sc.next();//reading password
			
	}//end of getAdminDetails() method
	
	//validate the user name and password for administrator
	public boolean validateAdmin() {
		if(adminName.equals(adminUserName)) {
			if(adminPassword.equals(adminPass)) {
				System.out.println("Admin Login Succeessful...\n\nWelcome");//printing message
				return true;
			}//end of inner if
			else {
				System.out.println("Incorrect password! \nAdmin Login Unsuccessful...");//printing message
				return false;
			}//end of inner else
		}//end of outer if
		else {
			System.out.println("Incorrect user name! \nAdmin Login Unsuccessful...");//printing message
			return false;
		}//end of outer else
	}//end of validateAdmin method
	
	public void createTable() {//Table created by Administrator
		System.out.println("Enter number of items to be added : ");
		int num = sc.nextInt();//reading number items to add from Administrator
		
		
		for(int itemNo = 1; itemNo <= num; itemNo++) {
			List<String> array = new ArrayList<String>();
			System.out.println("Entry for item : " + itemNo);
			System.out.println("Enter item name : ");
			array.add(sc.next());//reading item name
			String price;
			do {
				System.out.println("Enter item Price (Should be greater than zero) : ");
				price = sc.next();
			}while(Integer.parseInt(price) <= 0);
			itemPrice.add(price);
			array.add(price);
			String quantity;
			do {
				System.out.println("Enter item Quantity (Should be greater than zero) : ");
				quantity = sc.next();//reading number of items in store
			}while(Integer.parseInt(quantity) <= 0);
			array.add(quantity);
			adminTable.put(itemNo, array);//adding entry for an item
		}//end of for loop
		
	}//end of createTable
	
	//showing table created by administrator
	public void displayTable() {
		System.out.println("ItemNO\tItemName\tItemPrice\tItemQuantity");
		Set<Map.Entry<Integer, List<String>>> setView = adminTable.entrySet();//creating set reference for item details
		for(Map.Entry<Integer, List<String>> entry : setView) {
			System.out.print(entry.getKey() + "\t");
			List<String> value = entry.getValue();
			for(String str : value) {
				System.out.print(str + "\t\t");
			}//end of inner for loop
			System.out.println("");
		}//end of outer for loop
	}//end of displayTable method

}//end of Administrator class
