🌤️ Weather App

A simple and beautiful Weather Forecast Application built using Spring Boot, Thymeleaf, and OpenWeather API.
The app fetches real-time weather data and displays temperature, humidity, wind speed, and weather conditions for any city.

🚀 Live Demo

Your application is deployed on Render:

👉 https://weather-app-1gf2.onrender.com

📌 Features

✔️ Search weather by city name
✔️ Real-time temperature, humidity, wind speed
✔️ Beautiful responsive UI
✔️ Spring Boot (Java 17)
✔️ Thymeleaf templating
✔️ OpenWeather API integration
✔️ Deployed on Render (Free Tier)

🛠️ Tech Stack
Layer	Technology
Backend	Spring Boot 3.4, Java 17
Frontend	HTML, CSS, Bootstrap, Thymeleaf
API	OpenWeatherMap
Build Tool	Maven
Deployment	Render Web Service
📂 Project Structure
weather-app/
 ├── src/
 │   ├── main/
 │   │   ├── java/com/chaitanya/weather_app/
 │   │   │   ├── controller/
 │   │   │   ├── service/
 │   │   │   └── WeatherAppApplication.java
 │   │   └── resources/
 │   │       ├── templates/ (index.html)
 │   │       ├── static/ css, js
 │   │       └── application.properties
 ├── pom.xml
 └── README.md

🔧 Installation & Run Locally
1. Clone the repository
git clone https://github.com/CHITHATURUCHAITANYAKRISHNA/weather-app.git
cd weather-app

2. Add your API Key

Create a file:

src/main/resources/application.properties


Add:

weather.api.key=YOUR_API_KEY

3. Build the project
mvn clean package

4. Run
java -jar target/weather-app-0.0.1-SNAPSHOT.jar


The app will start at:

➡️ http://localhost:8080

🔑 Environment Variables (Render)

In Render → Environment Variables:

Key	Value
API_KEY	your OpenWeather API Key
🐳 Docker Support

If you're using Docker:

docker build -t weather-app .
docker run -p 8080:8080 weather-app

📸 Screenshots

Add your screenshots here later (optional).

Example:

![Home Page](screenshots/home.png)

❓ How It Works

User enters a city name

Backend calls OpenWeather API

JSON response is parsed

Thymeleaf displays weather data on UI

👨‍💻 Author

Chaitanya Krishna
GitHub: https://github.com/CHITHATURUCHAITANYAKRISHNA
