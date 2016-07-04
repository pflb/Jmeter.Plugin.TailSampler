package ru.pflb.jmeter.samplers;

import org.apache.jmeter.engine.event.LoopIterationEvent;
import org.apache.jmeter.protocol.http.sampler.HTTPSampleResult;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.samplers.Interruptible;
import ru.pflb.jmeter.samplers.wrapper.WrapperHTTPSamplerFactory;
/**
 * Common constants and methods for pflb - HTTP Sampler Tail
 */
public class TailHTTPSamplerProxy extends HTTPSamplerBase implements Interruptible {

    public static final String EMBRESLINKS = "Tail.Links";
    public static final String EMBRESCONTENT = "Tail.Content";
    private static final long serialVersionUID = 1L;
    private transient ITailHTTPImpl impl;
    private transient volatile boolean notifyFirstSampleAfterLoopRestart;

    public TailHTTPSamplerProxy() {
        super();
    }

    /**
     * Convenience method used to initialise the implementation.
     *
     * @param impl the implementation to use.
     */
    public TailHTTPSamplerProxy(String impl) {
        super();
        setImplementation(impl);
    }

    public String getEmbeddedResourceContent(String embeddedResourceLinks) {
        String[] embeddedResourceLinkArray = embeddedResourceLinks.split("\n");
        StringBuilder contentBuilder = new StringBuilder(embeddedResourceLinks.length() + 100);
        contentBuilder.append("<!DOCTYPE HTML>\n");
        contentBuilder.append("<html>\n<head>\n  <meta charset=\"utf-8\">\n    <title>Embedded resources</title>\n  </head>\n  <body>\n");
        for (String embeddedResourceLink : embeddedResourceLinkArray) {
            String srcLink = embeddedResourceLink.trim();
            if (!srcLink.equals("")) {
                contentBuilder.append("    <iframe src=\"");
                contentBuilder.append(EscapeUtils.escape(srcLink));
                contentBuilder.append("\"></iframe>\n");
            }
        }
        contentBuilder.append("  </body>\n</html>");
        return contentBuilder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected HTTPSampleResult sample(java.net.URL u, String method, boolean areFollowingRedirect, int depth) {
        // When Retrieve Embedded resources + Concurrent Pool is used
        // as the instance of Proxy is cloned, we end up with impl being null
        // testIterationStart will not be executed but it's not a problem for 51380 as it's download of resources
        // so SSL context is to be reused  new TailHTTPHC4Impl(this); //
        if (impl == null) { // Not called from multiple threads, so this is OK
            try {if (depth==0){
                impl = new TailHTTPHC4Impl(this);
            }
                else
            {
                impl = WrapperHTTPSamplerFactory.getImplementation(getImplementation(), this, depth);
            }
            } catch (Exception ex) {
                return errorResult(ex, new HTTPSampleResult());
            }
        }
        // see https://issues.apache.org/bugzilla/show_bug.cgi?id=51380
        if (notifyFirstSampleAfterLoopRestart) {
            impl.notifyFirstSampleAfterLoopRestart();
            notifyFirstSampleAfterLoopRestart = false;
        }
        HTTPSampleResult result;
        if (depth == 0) {
            byte[] content = this.getPropertyAsString(TailHTTPSamplerProxy.EMBRESCONTENT).getBytes();
            if (impl instanceof TailHTTPHC4Impl){
                TailHTTPHC4Impl tailIml = (TailHTTPHC4Impl)impl;
                result = tailIml.sampleTail(u, method, areFollowingRedirect, depth, content);
            }
            else
            {
                throw new RuntimeException("Unknown sampler implementation: " + impl.toString());
            }

        } else {
                result = impl.sampleTail(u, method, areFollowingRedirect, depth);
            }
        return result;
    }

    // N.B. It's not possible to forward threadStarted() to the implementation class.
    // This is because Config items are not processed until later, and HTTPDefaults may define the implementation

    @Override
    public void threadFinished() {
        if (impl != null) {
            impl.threadFinished(); // Forward to sampler
        }
    }

    @Override
    public boolean interrupt() {
        if (impl != null) {
            return impl.interrupt(); // Forward to sampler
        }
        return false;
    }

    /* (non-Javadoc)
     * @see org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase#testIterationStart(org.apache.jmeter.engine.event.LoopIterationEvent)
     */
    @Override
    public void testIterationStart(LoopIterationEvent event) {
        notifyFirstSampleAfterLoopRestart = true;
    }

}