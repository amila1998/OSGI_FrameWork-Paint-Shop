package paintshopcustomerproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PaintShopCustomerProducerActivator implements BundleActivator {

    private ServiceRegistration<PaintShopCustomerProducer> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Paint Shop Customer Producer Started");

        PaintShopCustomerProducer customerProducer = new PaintShopCustomerProducerImpl();
        serviceRegistration = context.registerService(PaintShopCustomerProducer.class, customerProducer, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Paint Shop Customer Producer Stopped");
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }
}
