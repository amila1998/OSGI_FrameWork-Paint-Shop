package paintshopadminconsumer;

import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import paintshopadminproducer.PaintShopAdminProducer;
import paintshopmixerproducer.PaintShopMixerProducer;

public class PaintShopAdminConsumerActivator implements BundleActivator {
    private ServiceReference<PaintShopAdminProducer> adminServiceRef;
    private ServiceReference<PaintShopMixerProducer> mixerServiceRef;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Start Admin Consumer Service");

        adminServiceRef = context.getServiceReference(PaintShopAdminProducer.class);
        PaintShopAdminProducer adminService = context.getService(adminServiceRef);

        mixerServiceRef = context.getServiceReference(PaintShopMixerProducer.class);
        PaintShopMixerProducer mixerService = context.getService(mixerServiceRef);

        if (adminService == null || mixerService == null) {
            System.out.println("Error: Unable to retrieve required services.");
            return;
        }

        System.out.println(adminService.myName());

        while (true) {
            System.out.println("\nChoose an option:\n"
                    + "1. Add New Paint Mixer\n"
                    + "2. Add a Delivery User\n"
                    + "3. Exit");
            System.out.print("Enter your choice: ");
            
            String option = scanner.nextLine().trim();
            
            switch (option) {
                case "1":
                    addNewPaint(mixerService);
                    break;
                case "2":
                    addDeliveryUser(adminService);
                    break;
                case "3":
                    System.out.println("Exiting Admin Consumer...");
                    return;
                default:
                    System.out.println("Invalid input! Please enter a valid option.");
            }
        }
    }

    private void addNewPaint(PaintShopMixerProducer mixerService) {
        System.out.println("\n--- Add New Paint ---");
        System.out.print("Enter Color Code: ");
        String colorCode = scanner.nextLine().trim();
        System.out.print("Enter Paint Name: ");
        String paintName = scanner.nextLine().trim();
        System.out.print("Enter Paint Price: ");

        int paintPrice;
        try {
            paintPrice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price! Please enter a valid number.");
            return;
        }

        mixerService.insertNewPaint(paintName, colorCode, paintPrice);
        System.out.println("Paint added successfully!");
    }

    private void addDeliveryUser(PaintShopAdminProducer adminService) {
        System.out.println("\n--- Register New Delivery User ---");
        System.out.print("Enter User Name: ");
        String dName = scanner.nextLine().trim();
        System.out.print("Enter Phone Number: ");
        String dPhone = scanner.nextLine().trim();
        System.out.print("Enter Username: ");
        String dUsername = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String dPassword = scanner.nextLine().trim();

        adminService.insertDeliveryUser(dName, dPhone, dUsername, dPassword);
        System.out.println(adminService.deliveryUserDisplay());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (adminServiceRef != null) {
            context.ungetService(adminServiceRef);
        }
        if (mixerServiceRef != null) {
            context.ungetService(mixerServiceRef);
        }
        System.out.println("Admin Consumer Stopped. Goodbye!");
    }
}
