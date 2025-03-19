package paintshopdeliveryconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import paintshopdeliveryproducer.PaintShopDeliveryProducer;
import java.util.Scanner;

public class PaintShopDeliveryConsumerActivator implements BundleActivator {
    private Scanner scanner = new Scanner(System.in);
    private boolean isLoggedIn = false;
    private ServiceReference<PaintShopDeliveryProducer> serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        serviceReference = context.getServiceReference(PaintShopDeliveryProducer.class);
        PaintShopDeliveryProducer deliveryService = context.getService(serviceReference);

        if (deliveryService == null) {
            System.out.println("Error: PaintShopDeliveryProducer service not available.");
            return;
        }

        System.out.println(deliveryService.wellcome());

        // Driver login loop
        while (!isLoggedIn) {
            System.out.println("\n--- Paint Shop Driver Login ---");
            System.out.print("Enter Your Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter Your Password: ");
            String password = scanner.nextLine().trim();

            isLoggedIn = deliveryService.login(username, password);
            if (!isLoggedIn) {
                System.out.println("Login failed. Please try again.");
            }
        }

        // Show delivery list after successful login
        deliveryService.showList();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Paint Shop Delivery Consumer Stopped.");
        if (serviceReference != null) {
            context.ungetService(serviceReference);
        }
    }
}
