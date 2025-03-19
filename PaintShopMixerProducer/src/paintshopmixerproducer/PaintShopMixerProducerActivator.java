package paintshopmixerproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PaintShopMixerProducerActivator implements BundleActivator {

    private ServiceRegistration<PaintShopMixerProducer> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Paint Shop Mixer Producer Started");

        PaintShopMixerProducer paintShopMixerProducer = new PaintShopMixerProducerImpl();
        serviceRegistration = context.registerService(PaintShopMixerProducer.class, paintShopMixerProducer, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Paint Shop Mixer Producer Stopped");
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }
}
