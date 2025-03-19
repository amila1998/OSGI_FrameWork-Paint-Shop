package paintshopdeliveryproducer;

public interface PaintShopDeliveryProducer {
	public boolean login(String username, String password);
	public String wellcome();
	public void showList();
}
