package ru.pflb.jmeter.samplers;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.*;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.jmeter.protocol.http.sampler.HTTPHC4Impl;
import org.apache.jmeter.protocol.http.sampler.HTTPSampleResult;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.protocol.http.sampler.HttpWebdav;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Fork of HTTP Sampler using Apache HttpClient 4.x.
 * with special method for download embedded resources
 */
public class TailHTTPHC4Impl extends HTTPHC4Impl implements ITailHTTPImpl{
    private static final Logger log = LoggingManager.getLoggerForClass();

    private volatile HttpUriRequest currentRequest; // Accessed from multiple threads

    protected TailHTTPHC4Impl(HTTPSamplerBase testElement) {
        super(testElement);
    }

    @Override
     public HTTPSampleResult sample(URL url, String method,
                                      boolean areFollowingRedirect, int frameDepth) {
        return super.sample(url, method, areFollowingRedirect, frameDepth);


    }

  //  @Override
    public HTTPSampleResult sampleTail(URL url, String method,
                                          boolean areFollowingRedirect, int frameDepth, byte[] content) {
        if (log.isDebugEnabled()) {
            log.debug("Start : sample " + url.toString());
            log.debug("method " + method + " followingRedirect " + areFollowingRedirect + " depth " + frameDepth);
        }
        HTTPSampleResult res = createSampleResult(url, method);
        HttpRequestBase httpRequest;
        try {
            URI uri = url.toURI();
            if (method.equals(HTTPConstants.POST)) {
                httpRequest = new HttpPost(uri);
            } else if (method.equals(HTTPConstants.PUT)) {
                httpRequest = new HttpPut(uri);
            } else if (method.equals(HTTPConstants.HEAD)) {
                httpRequest = new HttpHead(uri);
            } else if (method.equals(HTTPConstants.TRACE)) {
                httpRequest = new HttpTrace(uri);
            } else if (method.equals(HTTPConstants.OPTIONS)) {
                httpRequest = new HttpOptions(uri);
            } else if (method.equals(HTTPConstants.DELETE)) {
                httpRequest = new HttpDelete(uri);
            } else if (method.equals(HTTPConstants.GET)) {
                httpRequest = new HttpGet(uri);
            } else if (method.equals(HTTPConstants.PATCH)) {
                httpRequest = new HttpPatch(uri);
            } else if (HttpWebdav.isWebdavMethod(method)) {
                httpRequest = new HttpWebdav(method, uri);
            } else {
                throw new IllegalArgumentException("Unexpected method: '" + method + "'");
            }
            setupRequest(url, httpRequest, res); // can throw IOException
        } catch (Exception e) {
            res.sampleStart();
            res.sampleEnd();
            errorResult(e, res);
            return res;
        }

        HttpContext localContext = new BasicHttpContext();

        res.sampleStart();

        try {
            currentRequest = httpRequest;
            handleMethod(method, res, httpRequest, localContext);
            // perform the sample

            res.setResponseData(content);
            res.setDataType(SampleResult.TEXT);
            res.setContentType("text/html");
            res.setEncodingAndType("text/html; charset=UTF-8");

            res.sampleEnd(); // Done with the sampling proper.
            currentRequest = null;

            // Now collect the results into the HTTPSampleResult:
            res.setResponseCode("200");
            res.setResponseMessage("OK");
            res.setSuccessful(true);

            // If we redirected automatically, the URL may have changed
            if (getAutoRedirects()) {
                HttpUriRequest req = (HttpUriRequest) localContext.getAttribute(ExecutionContext.HTTP_REQUEST);
                HttpHost target = (HttpHost) localContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
                URI redirectURI = req.getURI();
                if (redirectURI.isAbsolute()) {
                    res.setURL(redirectURI.toURL());
                } else {
                    res.setURL(new URL(new URL(target.toURI()), redirectURI.toString()));
                }
            }
            // Follow redirects and download page resources if appropriate:
            res = resultProcessing(areFollowingRedirect, frameDepth, res);

        } catch (IOException e) {
            res.sampleEnd();
            log.debug("IOException", e);
            // pick up headers if failed to execute the request
            errorResult(e, res);
            return res;
        } catch (RuntimeException e) {
            res.sampleEnd();
            log.debug("RuntimeException", e);
            errorResult(e, res);
            return res;
        } finally {
            currentRequest = null;
        }
        return res;
    }

    @Override
    public boolean interrupt() {
        HttpUriRequest request = currentRequest;
        if (request != null) {
            currentRequest = null; // don't try twice
            try {
                request.abort();
            } catch (UnsupportedOperationException e) {
                log.warn("Could not abort pending request", e);
            }
        }
        return request != null;
    }
//protected protected
    @Override
     public void notifyFirstSampleAfterLoopRestart() {
        super.notifyFirstSampleAfterLoopRestart();
    }

    @Override
     public void threadFinished() {
        super.threadFinished();
    }

    @Override
    public HTTPSampleResult sampleTail(URL url, String method, boolean areFollowingRedirect, int frameDepth) {
        return super.sample(url,method, areFollowingRedirect,frameDepth);
    }
}