package paintshoporderproducer;

public interface PaintShopOrderProducer {
	public void addToBill(String customerName, String colorCode, int price);
	public void showMyBill();
	public void addToBillRegister(String deliverStatus);
}
