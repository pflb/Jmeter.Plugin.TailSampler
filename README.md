# Jmeter.Plugin.TailSampler
Sampler, выполняющий параллельную загрузку указанных ресурсов (embedded resources).

Плагин упрощает загрузку встроенных ресурсов, делая тест максимально близким к работе браузера.
HTTP Request Tail преобразует список ссылок в HTML-документ, загрузка встроенных ресурсов которого создаст GET-запрос по каждой из указанных ссылок.