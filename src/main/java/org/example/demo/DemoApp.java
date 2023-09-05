package org.example.demo;

public class DemoApp {
    public static void main(String[] args) {
        var context = new CustomContext();
        var printer = context.getBean(MorningGreetingPrinter.class);
        System.out.println(printer.morning("Sasha"));
    }
}
