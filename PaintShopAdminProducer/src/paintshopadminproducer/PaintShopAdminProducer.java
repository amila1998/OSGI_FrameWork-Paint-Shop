package paintshopadminproducer;

public interface PaintShopAdminProducer {
	public String myName();
	public void insertDeliveryUser(String name , String phone , String username, String password);
	public String deliveryUserDisplay();
	public void viewAllRecord();
}
