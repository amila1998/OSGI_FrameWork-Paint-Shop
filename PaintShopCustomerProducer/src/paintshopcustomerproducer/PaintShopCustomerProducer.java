package paintshopcustomerproducer;

public interface PaintShopCustomerProducer {
	public String wellcome();
	public void insertNewCustomer(String name, String address, String email, String phone);
	public String display();
	public boolean customerLogin(String name, String phone);
	public boolean loggingSucc();
	public String getMyName();
}
