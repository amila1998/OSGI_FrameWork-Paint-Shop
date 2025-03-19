package paintshopadminproducer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import models.DeliveryUser;

public class PaintShopAdminProducerImpl implements PaintShopAdminProducer {
    private DeliveryUser deliveryUser = new DeliveryUser();
    private final String filePath = System.getProperty("user.home") + File.separator + "driver.txt";

    @Override
    public String myName() {
        return "_________________________________ Paint Shop Admin Dashboard _________________________________\n";
    }

    @Override
    public void insertDeliveryUser(String name, String phone, String username, String password) {
        deliveryUser.setName(name);
        deliveryUser.setPhone(phone);
        deliveryUser.setUsername(username);
        deliveryUser.setPassword(password);
        addRecord();
    }

    @Override
    public String deliveryUserDisplay() {
        return deliveryUser.getName() + " " + deliveryUser.getPhone();
    }

    @Override
    public void viewAllRecord() {
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading driver records.");
            e.printStackTrace();
        }
    }

    private void addRecord() {
        boolean isDuplicate = false;
        File file = new File(filePath);

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            String record;
            while ((record = raf.readLine()) != null) {
                String[] details = record.split("!");
                if (details.length == 4 && (details[0].equals(deliveryUser.getName()) || details[1].equals(deliveryUser.getPhone()))) {
                    isDuplicate = true;
                    System.out.println("Delivery user already registered!");
                    break;
                }
            }

            if (!isDuplicate) {
                String newRecord = deliveryUser.getName() + "!" + deliveryUser.getPhone() + "!" + deliveryUser.getUsername() + "!" + deliveryUser.getPassword();
                raf.seek(raf.length());
                raf.writeBytes(newRecord + System.lineSeparator());
                System.out.println("Registration successful! Welcome " + deliveryUser.getName() + " to the Paint Shop Delivery Service.");
            }
        } catch (IOException e) {
            System.out.println("Error writing driver records.");
            e.printStackTrace();
        }
    }
}
