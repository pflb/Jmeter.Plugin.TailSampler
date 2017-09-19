package ru.pflb.jmeter.samplers;

import org.apache.jmeter.gui.util.HorizontalPanel;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.testelement.TestElement;
import ru.pflb.jmeter.protocol.http.config.gui.TailUrlConfigGui;

import javax.swing.*;
import java.awt.*;

/**
 * pflb - HTTP Sampler Tail GUI
 */
public class TailHttpSamplerGui extends HttpTestSampleGui {
    private static final long serialVersionUID = 1234567890L;
    private final boolean isAJP;
    private TailUrlConfigGui tailUrlConfigGui;

    public TailHttpSamplerGui() {
        super();
        isAJP = false;
        init();
    }

    protected TailHttpSamplerGui(boolean ajp) {
        super(ajp);
        isAJP = ajp;
        init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(TestElement element) {
        super.configure(element);
        tailUrlConfigGui.configure(element);
    }

    private void reSetDefaultValues(TailHTTPSamplerProxy sampler)
    {
        sampler.setImageParser(true);
        sampler.setConcurrentDwn(true);
        sampler.setConcurrentPool("4");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TestElement createTestElement() {
        TailHTTPSamplerProxy sampler = new TailHTTPSamplerProxy();
        modifyTestElement(sampler);
        reSetDefaultValues(sampler);
        return sampler;
    }

    /**
     * Modifies a given TestElement to mirror the data in the gui components.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void modifyTestElement(TestElement sampler) {
        super.modifyTestElement(sampler);
        tailUrlConfigGui.modifyTestElement(sampler);
    }

    @Override
    public String getStaticLabel() {
        return "pflb - HTTP Request Tail";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLabelResource() {
        return "pflb_HTTP_Request_Tail"; // $NON-NLS-1$
    }

    private void init() {// called from ctor, so must not be overridable
        setLayout(new BorderLayout(0, 5));
        setBorder(super.makeBorder());

        add(super.makeTitlePanel(), BorderLayout.NORTH);

        // URL CONFIG
        tailUrlConfigGui = new TailUrlConfigGui(true, !isAJP, false);
        add(tailUrlConfigGui, BorderLayout.CENTER);

        // Bottom (embedded resources, source address and optional tasks)
        JPanel bottomPane = new VerticalPanel();
        bottomPane.add(super.createEmbeddedRsrcPanel());
        JPanel optionAndSourcePane = new HorizontalPanel();
        optionAndSourcePane.add(super.createSourceAddrPanel());
        optionAndSourcePane.add(super.createOptionalTasksPanel());
        bottomPane.add(optionAndSourcePane);
        add(bottomPane, BorderLayout.SOUTH);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void clearGui() {
        super.clearGui();
        tailUrlConfigGui.clear();
    }
}