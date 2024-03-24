package com.bard.springcurrencygame.javafx;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageEvent extends ApplicationEvent {

    public StageEvent(Stage stage){
        super(stage);
    }

    public Stage getStage(){
        return ((Stage) getSource());
    }
}
