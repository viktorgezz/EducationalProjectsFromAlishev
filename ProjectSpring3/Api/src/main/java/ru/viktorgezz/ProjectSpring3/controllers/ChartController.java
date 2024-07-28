package ru.viktorgezz.ProjectSpring3.controllers;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.viktorgezz.ProjectSpring3.services.MeasurementService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
public class ChartController {

    private final MeasurementService measurementService;

    @Autowired
    public ChartController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/chart")
    public ResponseEntity<byte[]> getChart() {
        List<Double> yData = measurementService.getTemperatures();
        List<Long> xData = measurementService.getIds();

        XYChart chart = QuickChart.getChart("temperatures", "ID", "Temperature", "y(x)", xData, yData);

        Path tempFile;
        try {
            tempFile = Files.createTempFile("chart", ".png");
            BitmapEncoder.saveBitmap(chart, tempFile.toString(), BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            throw new RuntimeException("Error when creating a temporary file for the chart", e);
        }

        byte[] pngBytes;
        try {
            pngBytes = Files.readAllBytes(tempFile);
        } catch (IOException e) {
            throw new RuntimeException("Error reading temporary chart file", e);
        } finally {
            try {
                Files.delete(tempFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(pngBytes.length);
        return new ResponseEntity<>(pngBytes, headers, HttpStatus.OK);
    }
}
