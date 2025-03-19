package paintshopcustomerconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import java.util.Scanner;
import paintshopcustomerproducer.PaintShopCustomerProducer;
import paintshopmixerproducer.PaintShopMixerProducer;
import paintshoporderproducer.PaintShopOrderProducer;

public class PaintShopCustomerConsumerActivator implements BundleActivator {

    private ServiceReference<PaintShopCustomerProducer> customerServiceRef;
    private ServiceReference<PaintShopMixerProducer> mixerServiceRef;
    private ServiceReference<PaintShopOrderProducer> orderServiceRef;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Start Customer Consumer Service");
        
        customerServiceRef = context.getServiceReference(PaintShopCustomerProducer.class);
        PaintShopCustomerProducer customerService = context.getService(customerServiceRef);

        mixerServiceRef = context.getServiceReference(PaintShopMixerProducer.class);
        PaintShopMixerProducer mixerService = context.getService(mixerServiceRef);
        
        orderServiceRef = context.getServiceReference(PaintShopOrderProducer.class);
        PaintShopOrderProducer orderService = context.getService(orderServiceRef);

        if (customerService == null || mixerService == null || orderService == null) {
            System.out.println("Error: One or more services could not be retrieved.");
            return;
        }

        System.out.println(customerService.wellcome());

        Scanner scanner = new Scanner(System.in);
        boolean loginSuccess = false;

        while (!loginSuccess) {
            System.out.println("Do you have an account? (Y/N)");
            String decision = scanner.nextLine().trim().toUpperCase();

            switch (decision) {
                case "Y":
                    loginSuccess = handleCustomerLogin(scanner, customerService);
                    break;
                case "N":
                    handleCustomerRegistration(scanner, customerService);
                    break;
                default:
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    break;
            }
        }

        handlePaintSelection(scanner, mixerService, orderService, customerService);
    }

    private boolean handleCustomerLogin(Scanner scanner, PaintShopCustomerProducer customerService) {
        System.out.println("\n--- Paint Shop Customer Login ---\n");
        System.out.print("Enter Your Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Your Phone Number: ");
        String phone = scanner.nextLine().trim();

        boolean loginSuccess = customerService.customerLogin(name, phone);
        if (!loginSuccess) {
            System.out.println("Login failed. Please try again or register.");
        }
        return loginSuccess;
    }

    private void handleCustomerRegistration(Scanner scanner, PaintShopCustomerProducer customerService) {
        System.out.println("\n--- Paint Shop Customer Registration ---\n");
        System.out.print("Enter Your Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Your Phone Number: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Enter Your Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Your Address: ");
        String address = scanner.nextLine().trim();

        customerService.insertNewCustomer(name, address, email, phone);
    }

    private void handlePaintSelection(Scanner scanner, PaintShopMixerProducer mixerService,
                                      PaintShopOrderProducer orderService, PaintShopCustomerProducer customerService) {
        while (true) {
            System.out.println("Do you want to buy paint? (Y/N)");
            String buyDecision = scanner.nextLine().trim().toUpperCase();

            if (buyDecision.equals("N")) {
                orderService.showMyBill();
                System.out.println("Do you want to deliver? (Y/N)");
                String deliveryDecision = scanner.nextLine().trim().toUpperCase();
                orderService.addToBillRegister(deliveryDecision.equals("Y") ? "YES" : "NO");
                break;
            }

            mixerService.viewAllRecords();
            System.out.print("Choose a Paint by color code: ");
            String colorCode = scanner.nextLine().trim();
            String colorPriceString = mixerService.getPaintPriceByColorCode(colorCode);

            if (!colorPriceString.isEmpty() && colorPriceString.matches("\\d+")) { // Ensuring it's a valid number
                String customerName = customerService.getMyName();
                int price = Integer.parseInt(colorPriceString);
                orderService.addToBill(customerName, colorCode, price);
            } else {
                System.out.println("Invalid color code or price format. Please try again.");
            }
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Customer Consumer Stopped!");
        if (customerServiceRef != null) {
            context.ungetService(customerServiceRef);
        }
        if (mixerServiceRef != null) {
            context.ungetService(mixerServiceRef);
        }
        if (orderServiceRef != null) {
            context.ungetService(orderServiceRef);
        }
    }
}
