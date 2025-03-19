package paintshopdeliveryproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PaintShopDeliveryProducerActivator implements BundleActivator {
    private ServiceRegistration<PaintShopDeliveryProducer> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Paint Shop Delivery Producer Started");
        
        PaintShopDeliveryProducer deliveryProducer = new PaintShopDeliveryProducerImpl();
        serviceRegistration = context.registerService(PaintShopDeliveryProducer.class, deliveryProducer, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Paint Shop Delivery Producer Stopped");
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }
}
