package com.chaitanya.weather_app.controller;

import com.chaitanya.weather_app.model.ForecastResponse;
import com.chaitanya.weather_app.model.WeatherResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Controller
public class WeatherController {

    // <-- put your working API key here
    private final String API_KEY = "ec52070dd6f27ce57ed17c920dff4ee4";

    private final String CURRENT_URL  = "https://api.openweathermap.org/data/2.5/weather";
    private final String FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Current weather
            String currentUrl = CURRENT_URL + "?q=" + city + "&units=metric&appid=" + API_KEY;
            WeatherResponse current = restTemplate.getForObject(currentUrl, WeatherResponse.class);

            if (current == null) {
                model.addAttribute("error", "No data returned for " + city);
                return "index";
            }

            model.addAttribute("weather", current);
            model.addAttribute("city", city);

            // timezone (seconds offset from UTC) — available in current response
            int timezoneSeconds = 0;
            try {
                timezoneSeconds = current.getTimezone();
            } catch (Exception ignored) { }

            // Format sunrise/sunset in local time (simple approach: add offset then format UTC)
            try {
                long sunrise = current.getSys().getSunrise();
                long sunset  = current.getSys().getSunset();

                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                // keep formatter in UTC since we add timezone seconds to epoch manually
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                String sunriseLocal = sdf.format(new Date((sunrise + (long)timezoneSeconds) * 1000L));
                String sunsetLocal  = sdf.format(new Date((sunset  + (long)timezoneSeconds) * 1000L));

                model.addAttribute("sunrise", sunriseLocal);
                model.addAttribute("sunset", sunsetLocal);
            } catch (Exception ignored) { }

            // Wind/humidity/pressure (safe access)
            try {
                model.addAttribute("wind", current.getWind().getSpeed());
            } catch (Exception ignored) { model.addAttribute("wind", "--"); }

            try {
                model.addAttribute("humidity", current.getMain().getHumidity());
            } catch (Exception ignored) { model.addAttribute("humidity", "--"); }

            try {
                model.addAttribute("pressure", current.getMain().getPressure());
            } catch (Exception ignored) { model.addAttribute("pressure", "--"); }

            // 5-day forecast
            String forecastUrl = FORECAST_URL + "?q=" + city + "&units=metric&appid=" + API_KEY;
            ForecastResponse forecast = restTemplate.getForObject(forecastUrl, ForecastResponse.class);
            model.addAttribute("forecast", forecast);

        } catch (HttpClientErrorException ex) {
            String body = ex.getResponseBodyAsString();
            if (body != null && body.contains("Invalid API key")) {
                model.addAttribute("error", "Your API key is invalid. Please create a new one.");
            } else if (body != null && body.contains("city not found")) {
                model.addAttribute("error", "City not found: " + city);
            } else {
                model.addAttribute("error", "API Error: " + ex.getStatusCode());
            }
            model.addAttribute("city", city);
        } catch (Exception e) {
            model.addAttribute("error", "Something went wrong. Try again later.");
            model.addAttribute("city", city);
        }

        return "index";
    }
}
