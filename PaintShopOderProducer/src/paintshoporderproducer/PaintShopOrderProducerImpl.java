package paintshoporderproducer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PaintShopOrderProducerImpl implements PaintShopOrderProducer {

    private String billName = "demo";
    private final String filePath = System.getProperty("user.home");
    private int myBillGrandTot;

    @Override
    public void addToBill(String customerName, String colorCode, int price) {
        billName = customerName + generateANumber();
        File billFile = new File(filePath, billName + ".txt");

        try {
            if (!billFile.exists() && billFile.createNewFile()) {
                System.out.println("New bill file created: " + billFile.getName());
            }

            try (RandomAccessFile raf = new RandomAccessFile(billFile, "rw")) {
                while (raf.getFilePointer() < raf.length()) {
                    String[] lineSplit = raf.readLine().split("!");
                    if (lineSplit.length >= 3 && lineSplit[0].equals(colorCode)) {
                        System.out.println("Already Inserted!!!");
                        return;
                    }
                }

                String newRecord = colorCode + "!1!" + price;
                raf.seek(raf.length());
                raf.writeBytes(newRecord + System.lineSeparator());
                System.out.println("Successfully Inserted!!!");
            }
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void showMyBill() {
        File billFile = new File(filePath, billName + ".txt");

        if (!billFile.exists()) {
            System.out.println("No bill found for: " + billName);
            return;
        }

        System.out.println("\n--- Bill Details ---");
        try (RandomAccessFile raf = new RandomAccessFile(billFile, "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split("!");
                if (parts.length == 3) {
                    System.out.printf("Color Code: %s, Quantity: %s, Price: %s%n", parts[0], parts[1], parts[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the bill: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void addToBillRegister(String deliverStatus) {
        File billFile = new File(filePath, billName + ".txt");
        myBillGrandTot = calculateGrandTotal(billFile);

        File registerFile = new File(filePath, "BillRegister.txt");

        try {
            if (!registerFile.exists() && registerFile.createNewFile()) {
                System.out.println("New bill register file created.");
            }

            try (RandomAccessFile raf = new RandomAccessFile(registerFile, "rw")) {
                raf.seek(raf.length());
                raf.writeBytes(billName + "!" + myBillGrandTot + "!" + deliverStatus + System.lineSeparator());
                System.out.println("Successfully inserted into Bill Register!");
            }
        } catch (IOException e) {
            System.err.println("Error writing to the bill register: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int calculateGrandTotal(File billFile) {
        int total = 0;
        try (RandomAccessFile raf = new RandomAccessFile(billFile, "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split("!");
                if (parts.length == 3) {
                    total += Integer.parseInt(parts[2]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error calculating the grand total: " + e.getMessage());
            e.printStackTrace();
        }
        return total;
    }

    private int generateANumber() {
        return (int) (Math.random() * 1000000) + 1;
    }
}
