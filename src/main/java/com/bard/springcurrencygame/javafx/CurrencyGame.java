package com.bard.springcurrencygame.javafx;

import com.bard.springcurrencygame.SpringCurrencyGameApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class CurrencyGame extends Application {

    private ConfigurableApplicationContext applicationContext;


    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(SpringCurrencyGameApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.applicationContext.publishEvent(new StageEvent(primaryStage));
    }

    @Override
    public void stop(){
        applicationContext.close();
        Platform.exit();
    }
}
