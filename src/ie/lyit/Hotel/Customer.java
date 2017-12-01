package ie.lyit.Hotel;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Customer extends Person {
	
	private String emailAddress;
	private int number;	
	
	private static int nextNumber = 1;
	
	public Customer() {
		emailAddress = null;
		number = nextNumber++;
	}
	
	public Customer(String t, String fN, String sN, String a, String pN, String eA) {
		super(t, fN, sN, a, pN);
		emailAddress = eA;
		number = nextNumber++;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public static void setNextNumber(int nextNumber){
		Customer.nextNumber = nextNumber;
	}
	
	public String toString() {
		return name + " " + address + " " + phoneNumber + " " + emailAddress + " " + number;
	}
	
	public boolean equals(Object obj) {
		Customer cObject;
		if (obj instanceof Customer)
			cObject = (Customer)obj;
		else
			return false;
		
		return (this.name.equals(cObject.name) 
				&& this.address.equals(cObject.address)
				&& this.phoneNumber == cObject.phoneNumber
				&& this.emailAddress.equals(cObject.emailAddress)
				&& this.number == cObject.number);
	}
	
	public void read(){								//This will allow the user to create a Customer using our menu
		JTextField title = new JTextField();
		JTextField firstname = new JTextField();
		JTextField surname = new JTextField();
		JTextField address = new JTextField();
		JTextField phoneNumber = new JTextField();
		JTextField eMail = new JTextField();

	    Object[] message = {
	    	"Title:", title,
	    	"Firstname:", firstname,
	    	"Surname:", surname,
	    	"Address:", address,
	    	"Phone number:", phoneNumber,
	    	"E-Mail:", eMail,
	     };
	
	    int option = JOptionPane.showConfirmDialog(	null, 
	    											message, 
	    											"Enter Customer Details", 
	    											JOptionPane.DEFAULT_OPTION);
	
	    if (option == JOptionPane.OK_OPTION){
	    	this.name = new Name(title.getText(), firstname.getText(), surname.getText());
	    	this.address = address.getText();
	    	this.phoneNumber = phoneNumber.getText();
	    	this.emailAddress = eMail.getText();
	    }   
	}
	
}
