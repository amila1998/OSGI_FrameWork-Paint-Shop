package paintshopdeliveryproducer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PaintShopDeliveryProducerImpl implements PaintShopDeliveryProducer {
    private boolean isLoginSuccessful = false;
    private String driverName;
    private final String filePath = System.getProperty("user.home") + File.separator + "driver.txt";
    private final String billRegisterPath = System.getProperty("user.home") + File.separator + "BillRegister.txt";

    @Override
    public boolean login(String username, String password) {
        boolean found = false;
        
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            String record;
            while ((record = raf.readLine()) != null) {
                String[] data = record.split("!");
                if (data.length == 4 && data[2].equals(username) && data[3].equals(password)) {
                    found = true;
                    this.driverName = data[0];
                    this.isLoginSuccessful = true;
                    System.out.println("Hi " + driverName + ", welcome!");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error accessing driver records.");
            e.printStackTrace();
        }
        
        if (!found) {
            System.out.println("No matching account found.");
            this.isLoginSuccessful = false;
        }
        return this.isLoginSuccessful;
    }

    @Override
    public String wellcome() {
        return "_________________________________ Paint Shop Delivery Dashboard _________________________________";
    }

    @Override
    public void showList() {
        boolean deliveryAvailable = false;
        
        try (RandomAccessFile raf = new RandomAccessFile(billRegisterPath, "rw")) {
            System.out.println("|---------------------------------|\n|-------- Delivery Packages -----------|\n|---------------------------------|\n| Bill ID | Price | Status |");
            
            String record;
            while ((record = raf.readLine()) != null) {
                String[] data = record.split("!");
                if (data.length == 3 && "YES".equals(data[2])) {
                    deliveryAvailable = true;
                    System.out.printf("| %-8s | %-6s | %-6s |%n", data[0], data[1], data[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error accessing bill records.");
            e.printStackTrace();
        }
        
        if (!deliveryAvailable) {
            System.out.println("|---------------------------------|\n| No delivery packages available! |\n|---------------------------------|");
        }
    }
}
