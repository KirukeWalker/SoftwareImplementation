package ie.lyit.serialize;													
import java.io.FileInputStream;								//Importing all the things Java has and we'll use
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;							
import ie.lyit.Hotel.Customer;								//The packages as well
import ie.lyit.Hotel.Name;
															//End of imports
public class CustomerSerializerDAOImpl implements CustomerSerializerDAO 
	{
		private ArrayList<Customer> customerList; 			//We'll need an Array List of Customers
		final String FILENAME = "customerList.ser"; 		//This is the file were we'll save/load the inputs
		
	public CustomerSerializerDAOImpl()								//The empty constructor that creates the ArrayList
	{						
		customerList = new ArrayList<Customer>();		
	}
	
	/* (non-Javadoc)
	 * @see ie.lyit.serialize.CustomerSerializerDAO#addCustomer()
	 */
	public void addCustomer() 								//This method allow us to create a Customer and then
	{														//add it to the Array List of Customers
		Customer newCustomer = new Customer();												
		newCustomer.read();									
		customerList.add(newCustomer);
		
	}
	
	/* (non-Javadoc)
	 * @see ie.lyit.serialize.CustomerSerializerDAO#listAllCustomers()
	 */
	public void listAllCustomers() 							//With this method, we print the list of customers
	{														
		System.out.println("All Customers registered:");
		for(Customer tmpCustomer:customerList)
		System.out.println(tmpCustomer);
	}
	
	/* (non-Javadoc)
	 * @see ie.lyit.serialize.CustomerSerializerDAO#searchCustomer(int)
	 */
	public void searchCustomer(int number)					//This method allows us select a customer and then see
	{														//that customer's details.
		System.out.println("Customer with the number " 		
										+ number + ":");
		for(Customer tmpCustomer:customerList)
		{
			if (tmpCustomer.getNumber() == number)
			{
				System.out.println(tmpCustomer);
				return;
			}
		}
		System.out.println("Sorry, there is no customer "	//If no Customer has the number you set, this message
							+ "with that number");			//will be delivered													
	}														
	
	/* (non-Javadoc)
	 * @see ie.lyit.serialize.CustomerSerializerDAO#editCustomer(int)
	 */
	public void editCustomer(int number)					//The method that allows the user to modify an 
	{														//existing customer, displaying the previous							
		for(Customer tmpCustomer:customerList)				//information regarding that customer.
		{				
			if (tmpCustomer.getNumber() == number)
			{			

				JTextField Title = new JTextField();			
				JTextField Firstname = new JTextField();
				JTextField Surname = new JTextField();
				JTextField Address = new JTextField();
				JTextField PhoneNumber = new JTextField();
				JTextField EMail = new JTextField();
				
				Title.setText(tmpCustomer.getName().getTitle());
				Firstname.setText(tmpCustomer.getName().getFirstName());
				Surname.setText(tmpCustomer.getName().getSurName());
				Address.setText(tmpCustomer.getAddress());
				PhoneNumber.setText(tmpCustomer.getPhoneNumber());
				EMail.setText(tmpCustomer.getEmailAddress());
				
			    Object[] message = 
			    {
				    "Title:", Title,
				    "Firstname:", Firstname,
				    "Surname:", Surname,
				    "Address:", Address,
				    "Phone number:", PhoneNumber,
				    "E-Mail:", EMail,
			    };
			
			    int option = 	JOptionPane.showConfirmDialog(null, 
			    				message, "Modify Customer", 
			    				JOptionPane.DEFAULT_OPTION);
			
			    if (option == JOptionPane.OK_OPTION)
			    {
			    	tmpCustomer.setName(new Name(Title.getText(), Firstname.getText(), Surname.getText()));
			    	tmpCustomer.setAddress(Address.getText());
			    	tmpCustomer.setPhoneNumber(PhoneNumber.getText());
			    	tmpCustomer.setEmailAddress(EMail.getText());
			    }
				return;
			}
		}
		System.out.println("Sorry, there is no customer "	//If no Customer has the number you set, this message
									+ "with that number");	//will be delivered		
	}
	
	/* (non-Javadoc)
	 * @see ie.lyit.serialize.CustomerSerializerDAO#deleteCustomer(int)
	 */
	public void deleteCustomer(int number)					//This following method allows us to completely delete
	{														//an existing customer on our list.									
		for(Customer tmpCustomer:customerList)
		{				
			if (tmpCustomer.getNumber() == number)
			{
				customerList.remove(tmpCustomer);
				return;
			}
		}
		System.out.println("Sorry, there is no customer "	//If no Customer has the number you set, this message
									+ "with that number");	//will be delivered		
	}
	
	/* (non-Javadoc)
	 * @see ie.lyit.serialize.CustomerSerializerDAO#getHighestNumber()
	 */
	public int getHighestNumber()							//This method will be in charge of searching for the 
	{														//highest index on the list, so when we add a 
		int highestNumber = 0;								//new customer, he/she will have assigned the next index.
		for(Customer tmpCustomer:customerList)
		{				
			if (tmpCustomer.getNumber() > highestNumber)
			{
				highestNumber = tmpCustomer.getNumber();
			}
		}
		return highestNumber;
	}
	
	/* (non-Javadoc)
	 * @see ie.lyit.serialize.CustomerSerializerDAO#writeRecordsToFile()
	 */
	public void writeRecordsToFile() 						//This small method here let's us record the Customer list
	{														//to a file, in order to be able to read it later.
		try 
		{												
			FileOutputStream fileStream = new FileOutputStream(FILENAME);
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(customerList);
			os.close();
		}
		
		catch(FileNotFoundException fNFE) 					//If the file that we set to be recorded cannot be
		{													//created, this error will show.
			System.out.println("Couldn't create save file");		
		}
		
		catch(IOException ioE) 
		{
			System.out.println(ioE.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see ie.lyit.serialize.CustomerSerializerDAO#readRecordsFromFile()
	 */
	public void readRecordsFromFile() 						//This method, following the previous one, will allow us
	{														//read said file.
		try 												
		{													
			FileInputStream fileStream = new FileInputStream(FILENAME);
			ObjectInputStream is = new ObjectInputStream(fileStream);
			customerList = (ArrayList<Customer>)is.readObject();
			is.close();
		}
		
		catch(FileNotFoundException fNFE) 					//If the file that we set to be recorded cannot be
		{													//created, this error will show.
			System.out.println("Couldn't find save file");	
		}
		
		catch(IOException ioE) 
		{
			System.out.println(ioE.getMessage());
		}
		
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
