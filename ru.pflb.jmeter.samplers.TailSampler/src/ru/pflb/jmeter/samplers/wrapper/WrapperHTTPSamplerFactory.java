package ru.pflb.jmeter.samplers.wrapper;

import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.util.JOrphanUtils;
import ru.pflb.jmeter.samplers.ITailHTTPImpl;

/**
 * Factory to return the appropriate HTTPSampler for use with classes that need
 * an HTTPSampler; also creates the implementations for use with HTTPSamplerProxy.
 *
 */
public final class WrapperHTTPSamplerFactory{


    // N.B. These values are used in jmeter.properties (jmeter.httpsampler) - do not change
    // They can alse be used as the implementation name
    /** Use the the default Java HTTP implementation */
    public static final String HTTP_SAMPLER_JAVA = "HTTPSampler"; //$NON-NLS-1$

    /** Use Apache HTTPClient HTTP implementation */
    public static final String HTTP_SAMPLER_APACHE = "HTTPSampler2"; //$NON-NLS-1$

    static final String PROTOCOL_FILE = "file";

    //+ JMX implementation attribute values (also displayed in GUI) - do not change
    public static final String IMPL_HTTP_CLIENT4 = "HttpClient4";  // $NON-NLS-1$

    //public static final String IMPL_HTTP_CLIENT3_1 = "HttpClient3.1"; // $NON-NLS-1$

    public static final String IMPL_JAVA = "Java"; // $NON-NLS-1$
    //- JMX

    public static final String DEFAULT_CLASSNAME =
            JMeterUtils.getPropDefault("jmeter.httpsampler", IMPL_HTTP_CLIENT4); //$NON-NLS-1$

    private WrapperHTTPSamplerFactory() {
        // Not intended to be instantiated
    }

    public static String[] getImplementations(){
        return new String[]{IMPL_HTTP_CLIENT4,IMPL_JAVA};
    }

    public static ITailHTTPImpl getImplementation(String impl, HTTPSamplerBase base, int depth){
        if ("file".equals(base.getProtocol())) {
            return new WrapperHTTPFileImpl(base);
        }
        if (JOrphanUtils.isBlank(impl)){
            impl = DEFAULT_CLASSNAME;
        }

        if (IMPL_JAVA.equals(impl) || HTTP_SAMPLER_JAVA.equals(impl)) {
            return new WrapperHTTPJavaImpl(base);
        } else if (IMPL_HTTP_CLIENT4.equals(impl)) {
            return new WrapperHTTPHC4Impl(base);
        } else {
            throw new IllegalArgumentException("Unknown implementation type: '"+impl+"'");
        }
    }
}
