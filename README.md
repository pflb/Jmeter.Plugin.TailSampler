# Jmeter.Plugin.TailSampler

_Sampler_, performing parallel loading of specified resources.

Plugin makes it easy to load embedded resources, 
making the test as close as possible to the browser operation.
**HTTP Request Tail** converts the list of links into an HTML document, 
GET request for each of these links, downloads embedded resources

[Link][1] to download.

[1]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler.jar?raw=true)

This version of the plugin designed to work with JMeter 3.0, JMeter 2.13. 
Not tested on earlier versions.

### Installation

1. Download the plugin (see links above) ru.pflb.jmeter.samplers.TailSampler.jar.
2. Copy the plugin to `lib/ext` directory JMeter 3.0.
3. Restart JMeter 3.0.

Example directory:
```
D:\TOOLS\apache-jmeter-3.0\lib\ext\
D:\TOOLS\apache-jmeter-3.0\lib\ext\ru.pflb.jmeter.samplers.TailSampler.jar
```
### Description
Default settings:

- [v] **Retrieve All Embedded Resources** - *the checkbox is set by default, it can be removed,
but then subrequests will not be executed, and the HTTP Request Tail will become useless*.
- [v] **Use concurrent pool** -  *the checkbox is set by default, 
  on a large number of embedded resources, multi-threaded download increases the download speed*.
- **Use concurrent pool Size**: `4` - *the default value is 4, the value is used as the JMeter base value*.
   - HttpClient4 *when configuring* **Use concurrent pool Size**: 4 will send up to 4 requests parallel,
     each thread will use 1 persistent connection per domain:
     - Run a thread group, the size of the group determined by the **Use concurrent pool Size** settings;
       `- When configuring [v] **Use keepalive** each thread for each unique domain will create one persistent connection;`
   - By default, Firefox 44.0 browser sends up to *6* requests at the same time for each domain (see ``about:config``):
       - `256` - *network.http.max-connections* - The maximum number of connections;
       - `6` - *network.http.max-persistent-connections-per-server* - The maximum number of persistent connections 
         to the server;
       - `32` - *network.http.max-persistent-connections-per-proxy* - The maximum number of persistent connections 
         to the proxy server;
   - If you focus on Firefox settings and the fact that LT resources usually belong to the same domain, then in {v} you
     can set value to ``6`` instead of the standard value ``4``.
     

Unused settings - settings for the POST-request, the values are not used in any way by either main request or subrequests:
- [ ] **Use multipart/form-data for POST**;
- [ ] **Browser-compatible headers**.

The main request is generated, not sent, the configuration for the POST request does not apply to it.
Subrequests use the method GET, the POST request setting is also not valid for them.

The rest of the settings are valid for subrequests.

Addresses for embedded resources are specified in the text box **Embedded resources**. You con specify relative and 
absolute addresses. Relative addresses are supplemented by field values:

- **Web Server** - *Host and port*:
	- **Server Name or IP**;
	- **Port Number**;
- **Path** - *Catalogue for those links that are relative to the page, not to the host*.

The response to the main request is generated. There is no request, there is only the body of the response.
The response body is an HTML document, text with UTF-8 encoding, where the tag **iframe** is generated for each reference
to the embedded resource.

Example of a document:
```
	<!DOCTYPE HTML>
	<html>
	<head>
	  <meta charset="utf-8">
		<title>Embedded resources</title>
	  </head>
	  <body>
		<iframe src="http://www.google-analytics.com/analytics.js"></iframe>
		<iframe src="/sites/all/themes/pro/static/img/icons.png"></iframe>
		<iframe src="sites/all/themes/pro/static/img/main_3_block90-s.png"></iframe>
		<iframe src="http://www.performance-lab.ru/sites/all/themes/pro/static/img/footer-shadow.png"></iframe>
		<iframe src="http://staticxx.facebook.com/connect/xd_arbiter.php"></iframe>
	  </body>
	</html>
```

#### Temporary configurations
If you uncheck the **Retrieve All Embedded Resources** checkbox or do not specify a single link in **Embedded resources**,
the log will say that the request sent instantly, and the response to it came instantly

Description of temporary configuration:

- *Load Time* reflects the duration of loading embedded resources;
- *Connect time* always `0`;
- *Latency* always `0`.

### Project structure

Source code in the catalogue:

[/src/ru/pflb/jmeter][2]:

- [protocol/http/config/gui][3]:
    - **[TailUrlConfigGui.java][4]** - Control with a large input field for links to embedded resources;
- samplers:
	- [wrapper][5] - Wrappers:
		- **[WrapperHTTPFileImpl.java][6]** - To use protocol handler `file://` for subrequests;
		- **[WrapperHTTPHC3Impl.java][7]** - To use `HttpClient3.1` from settings **Implementation** for subrequests;
		- **[WrapperHTTPHC4Impl.java][8]** - To use `HttpClient4` from settings **Implementation** for subrequests;
		- **[WrapperHTTPJavaImpl.java][9]** - To use `Java` from settings **Implementation** for subrequests;
		- **[WrapperHTTPSamplerFactory.java][10]** - Factory, to create wrappers, returns the handler by protocol
          values and settings **Implementation**;
	- **[EscapeUtils.java][11]** - HTML shielding implementation, allows you to work with Russian domains, unicode and
      special characters in links;
	- **[ITailHTTPImpl.java][12]** - Basic interface for all handlers;
	- **[TailHTTPHC4Impl.java][13]** - Modified **HttpClient4**, which may not send a request and immediately use the 
      specified response body;
	- **[TailHTTPSamplerProxy.java][14]** - The proxy class, which implements all the logic of **TailSampler**, takes 
      a list of links from the settings and sends them to **TailHTTPHC4Impl** for the first request and to standard 
      handlers for requests for embedded resources;
	- **[TailHttpSamplerGui.java][15]** - Visual representation **TailSampler**.

[2]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter)
[3]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/protocol/http/config/gui)
[4]: (https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/protocol/http/config/gui/TailUrlConfigGui.java)
[5]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper)
[6]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPFileImpl.java)
[7]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPHC3Impl.java)
[8]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPHC4Impl.java)
[9]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPJavaImpl.java)
[10]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPSamplerFactory.java)
[11]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/EscapeUtils.java)
[12]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/ITailHTTPImpl.java)
[13]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/TailHTTPHC4Impl.java)
[14]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/TailHTTPSamplerProxy.java)
[15]: (https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/TailHttpSamplerGui.java)

Other auxiliary catalogues serve to debug the project.



