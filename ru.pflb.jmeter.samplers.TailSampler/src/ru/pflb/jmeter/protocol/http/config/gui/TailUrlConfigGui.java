package ru.pflb.jmeter.protocol.http.config.gui;

import org.apache.jmeter.gui.util.JSyntaxTextArea;
import org.apache.jmeter.gui.util.JTextScrollPane;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;
import ru.pflb.jmeter.samplers.TailHTTPSamplerProxy;
import org.apache.jmeter.protocol.http.config.gui.UrlConfigGui;

//deprecated
//import org.apache.jmeter.protocol.http.config.gui.MultipartUrlConfigGui;

import javax.swing.*;
import java.awt.*;

/**
 * Basic URL / HTTP Request Tail configuration:
 * <ul>
 * <li>host and port</li>
 * <li>connect and response timeouts</li>
 * <li>path, method, encoding</li>
 * <li>redirects and keepalive</li>
 * <li>embedded resource links</li>
 * </ul>
 */
public class TailUrlConfigGui extends UrlConfigGui {

    /**
     * Embedded resource links
     */
    private JSyntaxTextArea requestData;

    // used by HttpTestSampleGui
    public TailUrlConfigGui() {
        super();
        init();
    }

    public TailUrlConfigGui(boolean showSamplerFields,   boolean showRawBodyPane, boolean showFileUploadPane) {
        super(showSamplerFields, showRawBodyPane, showFileUploadPane);
        init();
    }

    @Override
    public void modifyTestElement(TestElement sampler) {
        super.modifyTestElement(sampler);
        sampler.setProperty(TailHTTPSamplerProxy.EMBRESLINKS, requestData.getText());
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        requestData.setInitialText(element.getPropertyAsString(TailHTTPSamplerProxy.EMBRESLINKS));
        requestData.setCaretPosition(0);
    }


    private void init() {// called from ctor, so must not be overridable
        this.setLayout(new BorderLayout());

        // WEB REQUEST PANEL
        JPanel webRequestPanel = new JPanel();
        webRequestPanel.setLayout(new BorderLayout());
        webRequestPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                JMeterUtils.getResString("web_request"))); // $NON-NLS-1$

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(super.getProtocolAndMethodPanel());
        northPanel.add(super.getPathPanel());

        webRequestPanel.add(northPanel, BorderLayout.NORTH);

        //EMBEDDED RESOURCE PANEL (Tail links)
        webRequestPanel.add(createDataPanel(), BorderLayout.CENTER);

        this.add(super.getWebServerTimeoutPanel(), BorderLayout.NORTH);
        this.add(webRequestPanel, BorderLayout.CENTER);
        this.add(super.getProxyServerPanel(), BorderLayout.SOUTH);
    }

    /*
     * Create a embedded resource panel
     *
     * @return the panel for entering the data
     */
    private JPanel createDataPanel() {
        JLabel label = new JLabel("Embedded resources"); //$NON-NLS-1$

        requestData = JSyntaxTextArea.getInstance(15, 80);
        requestData.setLanguage("text");
        requestData.setName(TailHTTPSamplerProxy.EMBRESLINKS);
        label.setLabelFor(requestData);

        JPanel dataPanel = new JPanel(new BorderLayout(5, 0));
        dataPanel.add(label, BorderLayout.WEST);
        dataPanel.add(JTextScrollPane.getInstance(requestData), BorderLayout.CENTER);

        return dataPanel;
    }

    @Override
    public void clear() {
        super.clear();
        requestData.setInitialText("");
        requestData.setCaretPosition(0);
    }
}
