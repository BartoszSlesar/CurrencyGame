package com.bard.springcurrencygame;

import com.bard.springcurrencygame.javafx.CurrencyGame;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCurrencyGameApplication {

    public static void main(String[] args) {

        Application.launch(CurrencyGame.class, args);
    }

}
