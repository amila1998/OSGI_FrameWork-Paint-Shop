package paintshopmixerproducer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import models.Paint;

public class PaintShopMixerProducerImpl implements PaintShopMixerProducer {
    private final Paint paint = new Paint();
    private final String filePath = System.getProperty("user.home") + File.separator + "paint.txt";

    @Override
    public String wellcomeMixer() {
        return "_________________________________ Paint Shop Mixer Dashboard _________________________________\n";
    }

    @Override
    public void insertNewPaint(String name, String code, int price) {
        paint.setName(name);
        paint.setCode(code);
        paint.setPrice(price);
        addRecord();
    }

    @Override
    public String display() {
        return "Display method not implemented yet.";
    }

    @Override
    public void viewAllRecords() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No records found.");
            return;
        }

        System.out.println("\n--- Available Paints in Shop ---\n");
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file.");
            e.printStackTrace();
        }
    }

    private void addRecord() {
        File file = new File(filePath);
        try {
            if (!file.exists() && file.createNewFile()) {
                System.out.println("New file created: " + file.getAbsolutePath());
            }

            try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                raf.seek(raf.length()); // Append mode
                String record = paint.getName() + "!" + paint.getCode() + "!" + paint.getPrice();
                raf.writeBytes(record + System.lineSeparator());
                System.out.println("New Paint added successfully!");
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file.");
            e.printStackTrace();
        }
    }

    @Override
    public String getPaintPriceByColorCode(String colorCode) {
        File file = new File(filePath);
        if (!file.exists()) {
            return "No records found.";
        }

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split("!");
                if (parts.length == 3 && parts[1].equals(colorCode)) {
                    return parts[2];
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file.");
            e.printStackTrace();
        }
        return "Paint with color code " + colorCode + " not found.";
    }
}