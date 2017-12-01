package ie.lyit.serialize;

public interface CustomerSerializerDAO {

	public abstract void addCustomer();
	public abstract void listAllCustomers();
	public abstract void searchCustomer(int number);
	public abstract void editCustomer(int number);
	public abstract void deleteCustomer(int number);
	public abstract int getHighestNumber();
	public abstract void writeRecordsToFile();
	public abstract void readRecordsFromFile();
}