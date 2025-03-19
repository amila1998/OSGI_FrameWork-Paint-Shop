package paintshopmixerproducer;

public interface PaintShopMixerProducer {
	public String wellcomeMixer();
	public void insertNewPaint(String name , String code, int price );
	public String display();
	public void viewAllRecords();
	public String getPaintPriceByColorCode(String code);
}
