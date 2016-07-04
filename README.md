# Jmeter.Plugin.TailSampler
Sampler, выполняющий параллельную загрузку указанных ресурсов (embedded resources).

Плагин упрощает загрузку встроенных ресурсов, делая тест максимально близким к работе браузера.
HTTP Request Tail преобразует список ссылок в HTML-документ, загрузка встроенных ресурсов которого создаст GET-запрос по каждой из указанных ссылок.

Ссылка на скачивание плагина: https://github.com/pflb/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler.jar?raw=true

Данная версия плагина обеспечивает работу с JMeter 3.0, JMeter 2.13. На более ранних версиях работа плагина не проверялась.

### Инструкция по установке

1. Скачать плагин (см. ссылки выше) ru.pflb.jmeter.samplers.TailSampler.jar.
2. Скопировать плагин в каталог lib/ext для JMeter 3.0.
3. Перезапустить JMeter 3.0.

Пример каталога:

	D:\TOOLS\apache-jmeter-3.0\lib\ext\
	D:\TOOLS\apache-jmeter-3.0\lib\ext\ru.pflb.jmeter.samplers.TailSampler.jar

### Описание

По умолчанию используются настройки:

- [v] **Retrieve All Embedded Resources** - *по умолчанию галочка поставлена, её можно снять, но тогда не будут выполнять подзапросы и HTTP Request Tail станет бесполезным*;
- [v] **Use concurent pool** - *по умолчанию галочка поставлена, на большом количестве встроенных ресурсов многопоточная загрузка увеличивает скорость закачки*;
- **Use concerent pool Size**: `4` - *по умолчанию используется значение 4, это значение используется JMeter в качестве базового*:
	- HttpClient4 при настройке **Use concerent pool Size**: `4` будет посылать до *4* запросов одновременно, каждый поток будет использовать по *1* постоянному соединению на каждый домен:
		- запустится группа потоков, размер группы определяется настройкой  **Use concerent pool Size**;
		- при настройке [v] **Use keepalive** каждый поток для каждого уникального домена будет создавать одно постоянное соединение (persistent-connection);
	- Браузер Mozilla Firefox 44.0 по умолчанию посылает до *6* одновременных запросов на каждый домен (см. ``about:config``):
		- `256` - *network.http.max-connections* - максимальное число соединений;
		- `6` - *network.http.max-persistent-connections-per-server* - максимальное число постоянных соединений с сервером (keepalive);
		- `32` - *network.http.max-persistent-connections-per-proxy* - максимальное число постоянных соединений с прокси-сервером (keepalive);
	- Если ориентироваться на настройки Firefox, и то, что ссылки на встроенные ресурсы в проектах нагрузочного тестирования обычно принадлежат одному домену, то в **Use concerent pool Size** можно ставить значение ``6``, вместо стандартного значения ``4``.

Неиспользуемые настройки - настройки для POST-запросов, значения никак не используются ни главным запросом ни подзапросами:

- [ ] **Use multipart/form-data for POST**;
- [ ] **Browser-compatible headers**.

Главный запрос генерируется, а не отправляется, на него настройки для POST-запросов не действуют. Подзапросы используют метод GET, для них также не действуют настройки для POST-запросов.

Остальные настройки действуют на подзапросы.

Адреса для встроенных ресурсов указываются в текством поле **Embedded resources**. Можно указывать относительные и абсолютные адреса. Относительные адреса дополняются значениями полей:

- **Web Server** - *хост и порт*:
	- **Server Name or IP**;
	- **Port Number**;
- **Path** - *каталог для тех ссылок, что являются относительными относительно страницы, а не относительно хоста*.

Ответ на основной запрос генерируется. Запроса нет, есть только тело ответа.
Тело ответа представляет собой html-документ, текст с кодировкой UTF-8, где для каждой ссылки на встроенный ресурс сгенерирован тег **iframe**.

Пример документа:

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

#### Временные характеристики
Если снять галочку [ ] **Retrieve All Embedded Resources** или не указать ни одной ссылки в **Embedded resources**, то в логах будет написано, что запрос оптправился мгновенно, и ответ на него пришел мгновенно.

Описание временных характеристик:

- *Load Time* отражает длительность загрузки встроенных ресурсов;
- *Connect time* всегда `0`;
- *Latency* всегда `0`.

### Структура проекта

Исходный код в каталоге:

[/src/ru/pflb/jmeter](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter):

- [protocol/http/config/gui](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/protocol/http/config/gui):
	- **[TailUrlConfigGui.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/blob/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/protocol/http/config/gui/TailUrlConfigGui.java)** - элемент управления с большим полем ввода для ссылок на встроенные ресурсы;
- samplers:
	- [wrapper](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper) - обёртки, чтобы использовать указанный на форме Implementation:
		- **[WrapperHTTPFileImpl.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPFileImpl.java)** - обёртка, чтобы использовать обработчик протокола `file://` для подзапросов;
		- **[WrapperHTTPHC3Impl.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPHC3Impl.java)** - обёртка, чтобы использовать `HttpClient3.1` из настройки **Implementation** для подзапросов;
		- **[WrapperHTTPHC4Impl.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPHC4Impl.java)** - обёртка, чтобы использовать `HttpClient4` из настройки **Implementation** для подзапросов;
		- **[WrapperHTTPJavaImpl.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPJavaImpl.java)** - обёртка, чтобы использовать `Java` из настройки **Implementation** для подзапросов;
		- **[WrapperHTTPSamplerFactory.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/wrapper/WrapperHTTPSamplerFactory.java)** - фабрика, для создания обёрток, возвращает обработчик по значениям протокола и настройке **Implementation**;
	- **[EscapeUtils.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/EscapeUtils.java)** - реализация html-экранирования, позволяет работать с русскими доменами, юникодом и специальными символами в ссылках;
	- **[ITailHTTPImpl.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/ITailHTTPImpl.java)** - базовый интерфейс для всех обработчиков;
	- **[TailHTTPHC4Impl.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/TailHTTPHC4Impl.java)** - модифицированный **HttpClient4**, который может не отправлять запрос, и сразу использовать указанное тело ответа;
	- **[TailHTTPSamplerProxy.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/TailHTTPSamplerProxy.java)** - прокси-класс в котором реализована вся логика работы **TailSampler**, берёт список ссылок из настроек и передаёт в **TailHTTPHC4Impl** для первого запроса, и в стандартные обработчики для запросов на встроенные ресурсы;
	- **[TailHttpSamplerGui.java](https://git.performance-lab.ru/v.smirnov/Jmeter.Plugin.TailSampler/tree/master/ru.pflb.jmeter.samplers.TailSampler/src/ru/pflb/jmeter/samplers/TailHttpSamplerGui.java)** - визуальное представление **TailSampler**.

Другие каталоги вспомогательные, служат для удобства отладки проекта.
