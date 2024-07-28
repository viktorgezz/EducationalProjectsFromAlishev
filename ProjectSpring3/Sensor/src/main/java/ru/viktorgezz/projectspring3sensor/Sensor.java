package ru.viktorgezz.projectspring3sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.viktorgezz.projectspring3sensor.model.SensorObj;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class Sensor {

    public static void main(String[] args) {
        SpringApplication.run(Sensor.class, args);

        Random random = new Random();

        System.out.println("Input: ");
        String input = new Scanner(System.in).nextLine();

        final String urlPost = "http://localhost:8080/measurement/add";

        for (int i = 0; i < 50; i++) {
//            System.out.println("Input: ");
//            String input = new Scanner(System.in).nextLine();
            Map<String, Object> jsonData = new HashMap<>();
            jsonData.put("value", ((double) Math.round((random.nextDouble() * ((150.0 - (-150.0)) + (-150.0)) * 10.0))) / 10);
            jsonData.put("raining", random.nextBoolean());
            jsonData.put("sensor", new SensorObj("sensor 2")); // Map.of("name", sensorName)

            makePostRequestWithJSONData(urlPost, jsonData);
        }
    }

    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("Success");
        } catch (HttpClientErrorException e) {
            System.out.println();
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }

}
