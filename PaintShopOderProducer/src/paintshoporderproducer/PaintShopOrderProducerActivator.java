package paintshoporderproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PaintShopOrderProducerActivator implements BundleActivator {

    private ServiceRegistration<PaintShopOrderProducer> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Paint Shop Order Producer Started");

        PaintShopOrderProducer paintShopOrderProducer = new PaintShopOrderProducerImpl();
        serviceRegistration = context.registerService(PaintShopOrderProducer.class, paintShopOrderProducer, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Paint Shop Order Producer Stopped");
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }
}
