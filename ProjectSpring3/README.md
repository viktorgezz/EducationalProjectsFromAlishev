# REST API для метеорологического сенсора

Этот проект представляет собой создание REST API сервиса на основе Spring, который принимает данные от метеорологического сенсора, измеряющего температуру воздуха и наличие дождя. Основные функции API включают регистрацию сенсоров, добавление новых измерений, получение всех измерений и подсчет количества дождливых дней.

### Основные возможности:
- **POST /sensors/registration**: Регистрация нового сенсора.
- **POST /measurements/add**: Добавление нового измерения.
- **GET /measurements**: Получение всех измерений из базы данных.
- **GET /measurements/rainyDaysCount**: Получение количества дождливых дней.

### Дополнительные решённые задачи:
- Создание Java клиента, который отправляет запросы к API.
- Автоматическая отправка 1000 случайных измерений с помощью RestTemplate.
- Построение графика температур (опционально) с использованием библиотеки xchart.

---

# REST API for Weather Sensor

This project involves creating a REST API service based on Spring that receives data from a weather sensor, measuring air temperature and detecting rain. The main functions of the API include registering sensors, adding new measurements, retrieving all measurements, and counting the number of rainy days.

### Key Features:
- **POST /sensors/registration**: Register a new sensor.
- **POST /measurements/add**: Add a new measurement.
- **GET /measurements**: Retrieve all measurements from the database.
- **GET /measurements/rainyDaysCount**: Get the count of rainy days.

### Additional Completed Tasks:
- Creating a Java client that sends requests to the API.
- Automatically sending 1000 random measurements using RestTemplate.
- Plotting temperature graph (optional) using xchart library.
