package org.example.demo;

@Bean("morningBean")
public class MorningGreetingPrinter {

    String morning(String name){
        return "Good morning, " + name;
    }
}
