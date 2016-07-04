package ru.pflb.jmeter.samplers.wrapper;

import org.apache.jmeter.protocol.http.sampler.HTTPHC4Impl;
import org.apache.jmeter.protocol.http.sampler.HTTPSampleResult;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import ru.pflb.jmeter.samplers.ITailHTTPImpl;

import java.net.URL;

/**
 * Created by a.perevozchikova on 01.03.2016.
 */
public class WrapperHTTPHC4Impl extends HTTPHC4Impl implements ITailHTTPImpl{
    protected WrapperHTTPHC4Impl(HTTPSamplerBase testElement) {
        super(testElement);
    }

    @Override
    public HTTPSampleResult sampleTail(URL url, String method, boolean areFollowingRedirect, int frameDepth) {
        return super.sample(url, method, areFollowingRedirect, frameDepth);
    }

    @Override
    public void notifyFirstSampleAfterLoopRestart(){
        super.notifyFirstSampleAfterLoopRestart();
    }

    @Override
    public void threadFinished() {
        super.threadFinished();
    }
}
