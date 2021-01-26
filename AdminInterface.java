package admincustomerassignment;

public interface AdminInterface {
	
	String adminName = "rahul";//user name of administrator
	
	String adminPassword = "rahulpass";//password of administrator
	
	void getAdminDetails();//get details of administrator
	
	boolean validateAdmin();//validate administrator user name and password
	
	void createTable();//create table containing item details
	
	void displayTable();//display the table created by administrator
	
}//end of AdminInterface
