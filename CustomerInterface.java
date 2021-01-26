package admincustomerassignment;

public interface CustomerInterface {
	
	String userName = "gokul";//user name of user
	
	String userPassword = "gokulpass";//password of user
	
	void getUserDetails();//method to get user details like user name and password
	
	boolean validateCustomer();//validating user details with hard coded value
	
	void customerLogin();//executes after customer is successfully logged in
	
	double chooseItems();//provision for customer to choose items from the table created by administrator
	
	double netAmount(double itemPrice);//calculates net amount by processing total price with gst and discounts
	
	void billPayment(double spent);//customer can pay bill using bank details

}//end of CustomerInterface
