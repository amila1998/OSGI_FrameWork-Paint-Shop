package paintshopadminproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PaintShopAdminProducerActivator implements BundleActivator {
    private ServiceRegistration<PaintShopAdminProducer> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("PaintShop Admin Producer Started");
        
        PaintShopAdminProducer adminProducer = new PaintShopAdminProducerImpl();
        serviceRegistration = context.registerService(PaintShopAdminProducer.class, adminProducer, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Admin Producer Stopped");
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }
}
