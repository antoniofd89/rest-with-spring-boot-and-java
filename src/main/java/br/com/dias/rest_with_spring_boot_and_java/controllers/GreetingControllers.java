package br.com.dias.rest_with_spring_boot_and_java.controllers;

import br.com.dias.rest_with_spring_boot_and_java.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingControllers {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    //http://localhost:8080/greeting?name=Antonio
    @RequestMapping("/greeting")
    public Greeting greeting (@RequestParam(value = "name",defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
