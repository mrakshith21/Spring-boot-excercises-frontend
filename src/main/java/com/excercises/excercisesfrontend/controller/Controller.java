package com.excercises.excercisesfrontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@RestController
@CrossOrigin
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClient;

    // 11
    @GetMapping(value = "displayNumbers")
    public ResponseEntity<Object[]> displayNumbers(@RequestParam("number") String number){
        ResponseEntity<Object[]> numbers = restTemplate.getForEntity("http://localhost:8081/displayNumbers?number=" + number, Object[].class);
        return numbers;
    }

    // 12
    @GetMapping(value = "/add")
    public Integer add(@RequestParam("num1") String num1, @RequestParam("num2") String num2){
        Integer result = restTemplate.getForObject("http://localhost:8081/add?num1=" + num1 + "&num2=" + num2, Integer.class);
        return result;
    }

    // 13, 14
    @GetMapping(value = "/calculator")
    public ResponseEntity<Integer> calculator(@RequestParam("num1") String num1, @RequestParam("num2") String num2, @RequestParam("operand") String operand){
        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity("http://localhost:8081/calculator?num1=" + num1 + "&num2=" + num2 + "&operand=" + operand, Integer.class);
        return responseEntity;
    }

    // 15 - counter
    @GetMapping(value = "/counter")
    public Integer counter(){
        return restTemplate.getForObject("http://localhost:8081/counter", Integer.class);
    }


    // 16 - increment, decrement and reset
    @GetMapping(value = "incDecReset")
    public Integer incDecReset(@RequestParam("change") String change){
       return webClient.build()
                .get()
                .uri("http://localhost:8081/incDecReset?change=" + change)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    // 17 - zodiac sign
    @GetMapping(value = "/zodiacSign")
    public String zodiacSign(@RequestParam("day") String day, @RequestParam("month") String month){
        return webClient.build()
                .get()
                .uri("http://localhost:8081/zodiacSign?date=" + day + "&month=" + month)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping(value = "/zodiacSignImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity zodiacSignImage(@RequestParam("zodiac") String zodiac){
        return webClient.build()
                .get()
                .uri("http://localhost:8081/zodiacSignImage?zodiac=" + zodiac)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
    }


    // 18


    @GetMapping(value = "/itemsDisplay")
    public ResponseEntity getItemsImages(@RequestParam("product") String product) throws IOException {

        return webClient.build()
                .get()
                .uri("http://localhost:8081/itemsDisplay?product=" + product)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
    }

    @GetMapping(value = "/itemsDisplay/products/{product}/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity getItemsImages(@PathVariable("product") String product, @PathVariable("image") String image) throws IOException {

        return webClient.build()
                .get()
                .uri("http://localhost:8081/itemsDisplay/products/" + product + "/" + image)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
    }


    // 19 - current date
    @GetMapping(value = "/date")
    public String getDate(){
        return webClient.build()
                .get()
                .uri("http://localhost:8081/date")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 20 - current time
    @GetMapping(value = "/time")
    public String getTime(){
        return webClient.build()
                .get()
                .uri("http://localhost:8081/time")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
