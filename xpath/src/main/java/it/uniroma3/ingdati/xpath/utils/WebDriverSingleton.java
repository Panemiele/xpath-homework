package it.uniroma3.ingdati.xpath.utils;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverSingleton {
    private static WebDriverSingleton instance = null;
    @Getter
    private WebDriver webDriver;

    private WebDriverSingleton() {

    }

    public static WebDriverSingleton getInstance() {
        if(instance == null) {
            instance = new WebDriverSingleton();
            instance.init();
        }

        return instance;
    }

    public void close() {
        webDriver.close();
    }

    private void init() {
        webDriver = new EdgeDriver();
        webDriver.manage().window().maximize();
    }

}
