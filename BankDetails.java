package admincustomerassignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BankDetails {
	
	private Map<String, List<String>> bankDetailsTable = new HashMap<>();//creating bank details table
	private String accountNumber;//variable to store accountNumber
	private String pinNumber;//variable to store pinNumber
	private String bankAccountBalance;//variable to store bankAccountBalance
	private boolean exist;//variable store whether account exist or not
	Scanner sc = new Scanner(System.in);//creating scanner object
	private List<String> demoList;
	
	//default data base for the bank details
	BankDetails() {
		demoList = new ArrayList<String>();
		demoList.add("1000");
		demoList.add("100000");
		bankDetailsTable.put("1000000000", demoList);
		demoList = new ArrayList<String>();
		demoList.add("1001");
		demoList.add("100000");
		bankDetailsTable.put("1000000001", demoList);
		demoList = new ArrayList<String>();
		demoList.add("1002");
		demoList.add("100000");
		bankDetailsTable.put("1000000002", demoList);
	}//end of constructor
	
	//method which returns the value of exist
	public boolean isExist() {
		return exist;
	}//end of isExist method
	
	//new customer can login using this method
	double newLogin() {
		List<String> list = new ArrayList<>();
		do {
			System.out.println("Enter Account Number(Should contain 10 numbers) : ");
			accountNumber = sc.next();//reading accountNumber
		}while(!(accountNumber.length() == 10));
		
		do {
			System.out.println("Enter Pin Number(Should contain 4 digits) : ");
			pinNumber = sc.next();//reading pinNumber
		}while(!(pinNumber.length() == 4));
				
		System.out.println("Enter Bank Account Balance : ");
		bankAccountBalance = sc.next();//reading bankAccountBalance
		list.add(pinNumber);
		list.add(bankAccountBalance);
		bankDetailsTable.put(accountNumber, list);//updating bank details table
		return Double.parseDouble(bankAccountBalance);
	}//end of newLogin method
	
	//already existing customers can login using this method
	double login() {
		do {
			System.out.println("Enter Account Number(Should contain 10 numbers) : ");
			accountNumber = sc.next();//reading accountNumber
		}while(!(accountNumber.length() == 10));
		
		do {
			System.out.println("Enter Pin Number(Should contain 4 digits) : ");
			pinNumber = sc.next();//reading pinNumber
		}while(!(pinNumber.length() == 4));
		
		exist = accountVerifier();//verifying account
		if(!exist) {
			return 0.0;
		}
		
		return Double.parseDouble(bankDetailsTable.get(accountNumber).get(1));
	}//end of login method
	
	/*This method will check whether the account entered by the customer is 
	 * existing or not*/
	boolean accountVerifier() {
		if(bankDetailsTable.containsKey(accountNumber)) {
			if(bankDetailsTable.get(accountNumber).get(0).equals(pinNumber)) {
				System.out.println("Account Exists!");
				return true;
			}else {
				System.out.println("Invalid Pin number!");
				return false;
			}
		}else {
			System.out.println("Invalid Account number!");
			return false;
		}
	}//end of accountVerifier method
	
	//update balance of the customer after deducting bill amount
	boolean updateBalance(double amount) {
		double balance = Double.parseDouble(bankDetailsTable.get(accountNumber).remove(1));
		
		bankDetailsTable.remove(accountNumber);
		
		List<String> list = new ArrayList<>();
		list.add(pinNumber);
		
		if(balance < amount) {
			list.add(Double.toString(balance));
			bankDetailsTable.put(accountNumber, list);//updating table with existing details if no enough balance
			return false;
		}else {
			System.out.println("Print yes to continue!");
			if(!sc.next().equals("yes")) {//getting confirmation from user
				list.add(Double.toString(balance));
				bankDetailsTable.put(accountNumber, list);//updating table with existing details if not confirmed
				updateBalance(amount);
			}
			list.add(Double.toString(balance - amount));
			bankDetailsTable.put(accountNumber, list);//adding updated balance
			System.out.println("Available Balance is " + (balance - amount));//showing available balance
			return true;
		}
	}//end of updateBalance method
	
}//end of BankDetails class
