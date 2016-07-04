package ru.pflb.jmeter.samplers;

import org.apache.jmeter.protocol.http.sampler.HTTPSampleResult;
import org.apache.jmeter.samplers.Interruptible;

import java.net.URL;

/**
 * Created by a.perevozchikova on 01.03.2016.
 */
public interface ITailHTTPImpl extends Interruptible  {
    HTTPSampleResult sampleTail(URL url, String method, boolean areFollowingRedirect, int frameDepth);
    void notifyFirstSampleAfterLoopRestart();
    void threadFinished();

}
