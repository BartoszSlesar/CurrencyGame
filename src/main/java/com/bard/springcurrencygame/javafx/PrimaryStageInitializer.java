package com.bard.springcurrencygame.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


import java.io.IOException;


@Component
public class PrimaryStageInitializer implements ApplicationListener<StageEvent> {

    @Value("classpath:/javafx/primaryStage.fxml")
    private Resource primaryResource;

    private final String applicationTitle;

    private final ApplicationContext applicationContext;

    public PrimaryStageInitializer(@Value("Currency Game") String applicationTitle,
                                   ApplicationContext applicationContext){

        this.applicationContext = applicationContext;
        this.applicationTitle = applicationTitle;
    }


    @Override
    public void onApplicationEvent(StageEvent event) {
        try{
           FXMLLoader fxmlloader = new FXMLLoader(primaryResource.getURL());
           fxmlloader.setControllerFactory(applicationContext::getBean);
            Parent parent = fxmlloader.load();
            Stage stage = event.getStage();
            stage.setTitle(applicationTitle);
            stage.setScene(new Scene(parent,400,300));
            stage.show();

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
