package ie.lyit.testers;
import javax.swing.JOptionPane;											//Importing all the things Java has and we'll use
import javax.swing.JTextField;

import ie.lyit.Hotel.Customer;											//The packages as well
import ie.lyit.serialize.CustomerSerializerDAO;
import ie.lyit.serialize.CustomerSerializerDAOImpl;
																		//End of imports
public class CustomerSerializerTester {

	private static CustomerSerializerDAO customerSerializerDAO = new CustomerSerializerDAOImpl();			
	
	public static void main(String[] args) 
	{
		
		boolean programRuns = true;										//True while the program is running
		customerSerializerDAO.readRecordsFromFile();						//Now, we open the save file
		Customer.setNextNumber(customerSerializerDAO.getHighestNumber() 
																+ 1);	//We set here the number for the next customer
																		//adding 1 to the highest 
		while(programRuns)
		{
			JTextField number = new JTextField();						//We create here an interface for the 
			number.requestFocus();										//user to interact with
		    Object[] message = {
		    		"1: Add Customer "
		    	+ "| 2: Search Customer "
		    	+ "| 3: Edit Customer "
		    	+ "| 4: Delete Customer "
		    	+ "| 5: Show all Customers", number,
		    };
		
		    int option = JOptionPane.showConfirmDialog(	null, message, 
		    											"Customer Serializer 1.0", 
		    											JOptionPane.OK_CANCEL_OPTION);
		
		    if (option == JOptionPane.OK_OPTION)
		    {
		    	int selection = Integer.parseInt(number.getText());		//We convert the number input by the user into
		    	switch(selection)										//an integer, to use it for the options
		    	{										
			    	case 1: addCustomer(); 		break;
			    	case 2: viewCustomer(); 	break;
			    	case 3: editCustomer(); 	break;
			    	case 4: deleteCustomer(); 	break;
			    	case 5: listAllCustomers(); break;
			    	
			    	default: programRuns = false;
		    	}
		    	
		    }   
		    else{
		    	programRuns = false;									//If the user inputs a number outside of the
		    }															//given choices, the program ends
		}

		customerSerializerDAO.writeRecordsToFile();						//The changes are saved to the file here
		
		System.out.println("Program ended by user");					//And this is the goodbye message
		
	}
	
	private static void addCustomer()									//Testing the "add Customer", works as 
	{																	//intended
		customerSerializerDAO.addCustomer();								
	}
	
	private static void viewCustomer()									//Testing the "Search Customer", works as
	{																	//intended, the user inputs a number, and the
		int number = getNumberFromUser("search:");						//program prints out the customer with that 
		customerSerializerDAO.searchCustomer(number);						//number
	}																	
	
	private static void editCustomer()									//Testing the "Edit Customer", works as 
	{																	//intended, the user inputs a number, and the
		int number = getNumberFromUser("edit:");						//program selects the customer with that 
		customerSerializerDAO.editCustomer(number);						//number and allows the user to modify it.
	}																	
	
	private static void deleteCustomer()								//Testing the "Delete Customer", works as 
	{																	//intended.
		int number = getNumberFromUser("delete:");						
		customerSerializerDAO.deleteCustomer(number);
	}
	
	private static void listAllCustomers()								//Testing the "Listing all customers", works
	{																	//as intended.
		customerSerializerDAO.listAllCustomers();
		if (customerSerializerDAO.getHighestNumber()==0)
		{
			System.out.println("There is no customers registered.");
		}
	}
	
	private static int getNumberFromUser(String purpose)				//As the name implies, this method allows us 
	{																	//to retrieve the number input by the user
		JTextField number = new JTextField();							//for our different purposes.
		
	    Object[] message = 												//Here, we ask the user to input the number
	    {
	    	"Input the number of the customer that you want to " + purpose, number,	
	    };
	
	    int option = JOptionPane.showConfirmDialog(	null, 				
	    											message, 
	    											"Enter a valid number", 
	    											JOptionPane.DEFAULT_OPTION);
	
	    if (option == JOptionPane.OK_OPTION)							//And here we check if the number input by
	    {																//the user is valid.
	    	return Integer.parseInt(number.getText());		
	    }
	    else
	    {
	    	System.out.println("Error");							
	    	return 0;
	    }
	}

}
