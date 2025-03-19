package paintshopcustomerproducer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import models.Customer;

public class PaintShopCustomerProducerImpl implements PaintShopCustomerProducer {
    private boolean loginSuccess = false;
    private final String filePath = System.getProperty("user.home") + File.separator + "customer.txt";
    private final Customer customer = new Customer();

    @Override
    public String wellcome() {
        return "_________________________________ Welcome to the Paint Shop! _________________________________";
    }

    @Override
    public void insertNewCustomer(String name, String address, String email, String phone) {
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        addRecord();
    }

    @Override
    public String display() {
        return String.format("%s %s %s %s", customer.getName(), customer.getAddress(), customer.getEmail(), customer.getPhone());
    }

    @Override
    public boolean customerLogin(String name, String phone) {
        boolean found = false;
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Customer file does not exist. Please register first.");
            return false;
        }

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] data = line.split("!");
                if (data.length == 4 && data[0].equals(name) && data[1].equals(phone)) {
                    customer.setName(data[0]);
                    customer.setPhone(data[1]);
                    customer.setEmail(data[2]);
                    customer.setAddress(data[3]);
                    System.out.println("Hi " + name + ", welcome back!");
                    found = true;
                    loginSuccess = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error accessing customer data.");
            e.printStackTrace();
        }

        if (!found) {
            System.out.println("No account found. Please register.");
            loginSuccess = false;
        }

        return found;
    }

    @Override
    public boolean loggingSucc() {
        return loginSuccess;
    }

    @Override
    public String getMyName() {
        return customer.getName();
    }

    private void addRecord() {
        File file = new File(filePath);

        try {
            if (!file.exists() && file.createNewFile()) {
                System.out.println("Customer file created: " + file.getAbsolutePath());
            }

            try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                while (raf.getFilePointer() < raf.length()) {
                    String[] data = raf.readLine().split("!");
                    if (data.length == 4 && (data[0].equals(customer.getName()) || data[1].equals(customer.getPhone()))) {
                        System.out.println("You are already registered!");
                        return;
                    }
                }

                String newRecord = String.join("!", customer.getName(), customer.getPhone(), customer.getEmail(), customer.getAddress());
                raf.seek(raf.length());
                raf.writeBytes(newRecord + System.lineSeparator());
                System.out.println("Registration successful! Welcome " + customer.getName());
                loginSuccess = true;
            }
        } catch (IOException e) {
            System.out.println("Error saving customer data.");
            e.printStackTrace();
        }
    }
}
