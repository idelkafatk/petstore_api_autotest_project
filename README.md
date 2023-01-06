<a id="lift"></a>

# Проект по автоматизации тестирования API [PetStore](https://petstore.swagger.io/)<img width="5%" title="Pet" src="images/logo/pet.svg">

## Содержание :clipboard:

* <a href="#description">Описание проекта и особенности</a>
* <a href="#stack">Cтек технологий</a>
* <a href="#objects">Реализованные проверки</a>
* <a href="#gradle">Запуск тестов</a>
    + <a href="#gradle">Gradle</a>
    + <a href="#jenkins">Jenkins</a>
* <a href="#allure">Отчет о результатах тестирования</a>
    + <a href="#allure">Allure Report</a>
    + <a href="#alluretestops">Интеграция с Allure TestOps</a>
* <a href="#jira">Интеграция с Jira</a>
* <a href="#telegram">Уведомления в Telegram</a>

# <a id="description">Описание</a>
Проект включает в себя API-тесты.\
Некоторые факты о проекте:

- [x] На проекте используется модель `Lombok`
- [x] Генерация данных с помощью библиотеки `Faker`
- [x] Интегрирован `Allure TestOps`
- [x] Интегрирован `Jira`
- [x] Реализованы спецификации для запросов и ответов
- [x] Реализована отправка уведомлений в `Telegram`

# <a id="stack">Технологии и инструменты</a>

<p  align="center">
  <code><img width="5%" title="IntelliJ IDEA" src="images/logo/Idea.svg"></code>
  <code><img width="5%" title="Java" src="images/logo/Java.svg"></code>
  <code><img width="5%" title="Gradle" src="images/logo/Gradle.svg "></code>
  <code><img width="5%" title="JUnit5" src="images/logo/Junit5.svg"></code>
  <code><img width="5%" title="RestAssured" src="images/logo/RestAssured.svg"></code>
  <code><img width="5%" title="Lombok" src="images/logo/lombok.svg"></code>
  <code><img width="5%" title="Allure Report" src="images/logo/Allure.svg"></code>
  <code><img width="5%" title="Allure TestOps" src="images/logo/Allure_TO.svg"></code>
  <code><img width="5%" title="Github" src="images/logo/GitHub.svg"></code>
  <code><img width="5%" title="Jenkins" src="images/logo/Jenkins.svg"></code>
  <code><img width="5%" title="Jira" src="images/logo/Jira.svg"></code>
  <code><img width="5%" title="Telegram" src="images/logo/Telegram.svg"></code>
</p>

>* Автотесты написаны на `Java` с использованием библиотеки `RestAssured`
>* Десериализация с помощью библиотеки `Jackson`
>* Фреймворк для модульного тестирования `JUnit`
>* Для автоматизации сборки проекта исопльзуется `Gradle`
>* Сборка проекта в CI/CD `Jenkins`
>* Формирование отчета в `Allure Report`
>* Отчеты отправляются в `Telegram`

# <a id="objects">Реализованные проверки</a>

1. [ ] _Добавление нового питомца в магазин_
2. [ ] _Получение информации о питомце по ID_
3. [ ] _Обновление информации о питомце_
4. [ ] _Удаление информации о питомце по ID_
5. [ ] _Добавление нового пользователя_
6. [ ] _Получение информации о пользователе по username_
7. [ ] _Изменение информации о пользователе_

# <a id="gradle">Запуск тестов из терминала</a>

```bash
gradle clean test
```

# <img width="4%" title="Jenkins" src="images/logo/Jenkins.svg"><a id="jenkins">Запуск тестов в Jenkins</a>

Параметры запуска:
<p align="center">
  <img src="images/screens/jenkins_param.png" alt="job" width="800">
</p>

После завершения сборки результаты тестов доступны в:
> `Allure Report` \
> `Allure TestOps` - результаты подгружаются автоматически и также обновляются при изменении кода

<p align="center">
  <img src="images/screens/jenkins_results.png" alt="job" width="800">
</p>

# <img width="4%" title="Allure Report" src="images/logo/Allure.svg"><a id="allure">Отчет о результатах тестирования в Allure Report</a>

Главная страница `Allure Report` включает в себя:
> `ALLURE REPORT` - дата и время проведения тестов,а также диаграмма с указанием процента и количества успешных,
> упавших и сломавшихся в процессе выполнения тестов \
> `TREND` - тенденция выполнения для всех запусков \
> `SUITES` - отображение тестов по тестовым наборам \
> `CATEGORIES` - отображение тестов по категориям

<p align="center">
  <img src="images/screens/allure_main.png" alt="job" width="800">
</p>

На странице `Suites` можно посмотреть информацию о тестах:

<p align="center">
  <img src="images/screens/allure_suites.png" alt="job" width="800">
</p>

Также там доступны `логи` запросов и ответов, отфильтрованные в удобочитаемый вариант:

<p align="center">
  <img src="images/screens/allure_artifacts.png" alt="job" width="800">
</p>

# <img width="4%" title="Allure TestOPS" src="images/logo/Allure_TO.svg"><a id="alluretestops">Интеграция с Allure TestOps</a>

Инфографику по проведенным тестам можно посмотреть в разделе `Dashboards`

<p align="center">
  <img src="images/screens/allureto_dashboard.png" alt="job" width="800">
</p>

В разделе `Launches` находится история запусков тестов

<p align="center">
  <img src="images/screens/allureto_launch.png" alt="job" width="800">
</p>

Кликнув на проект, попадаем в `Детали проекта`
> Внутри доступны следующие вкладки:
> * `Overview` - общая информация по тестам
> * `Tree` - информация по каждому тесту
> * `Errors` - логи ошибок
> * `Graphs` - графическое представление времени проведения тестов
> * `Timeline` - показан таймлайн всех тестов

<p align="center">
  <img src="images/screens/allureto_launch_details.png" alt="job" width="800">
</p>

В разделе `Testcases` автоматически подгружаются тест-кейсы

<p align="center">
  <img src="images/screens/allureto_testcases.png" alt="job" width="800">
</p>

> Можно добавлять ручные тест-кейсы \
> Доступен экспорт тест-кейсов в _PDF_, _CSV_ \
> Есть возможность выборочно запускать тесты, создавать тест-планы и т.д.

<p align="center">
  <img src="images/screens/allureto_add.png" alt="job" width="800">
</p>

# <img width="4%" title="Jira" src="images/logo/Jira.svg"><a id="jira">Интеграция с Jira</a>

<p align="center">
  <img src="images/screens/jira_task.png" alt="job" width="800">
</p>

# <img width="4%" title="Jira" src="images/logo/Telegram.svg"><a id="telegram">Уведомления в Telegram</a>
> После завершения сборки, бот автоматически отправляет уведомление в телеграм с результатами теста
<p align="center">
  <img src="images/screens/telegram_notifications.png" alt="job" width="400">
</p>

[Вернуться наверх ⬆](#lift)