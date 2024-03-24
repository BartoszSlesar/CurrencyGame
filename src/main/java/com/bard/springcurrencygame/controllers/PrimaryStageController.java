package com.bard.springcurrencygame.controllers;


import com.bard.springcurrencygame.model.Currency;
import com.bard.springcurrencygame.model.CurrencyList;
import com.bard.springcurrencygame.model.Symbol;
import com.bard.springcurrencygame.services.CurrentCurrencyService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.stereotype.Controller;

@Controller
public class PrimaryStageController {

    private final CurrentCurrencyService currencyService;
    private boolean gameStarted;
    private CurrencyList currencyList;

    @FXML
    public Button startButton;
    @FXML
    public Button restartButton;
    @FXML
    public TextArea currencyTextField;
    @FXML
    public Label startGameLabel;
    @FXML
    public TextField answerTextField;

    private Currency currency;


    public PrimaryStageController(CurrentCurrencyService currencyService) {
        this.currencyService = currencyService;
        this.gameStarted = false;
    }


    public void startGame(ActionEvent actionEvent) {

        if (!this.gameStarted) {
            this.currencyList = this.currencyService.getCurrentCurrencyRates();

            Symbol symbol = this.currencyService.getRandomCurrency();
            double rate = currencyList.getRate(symbol.getSymbol());
            this.currency = new Currency(symbol.getSymbol(), rate);
            this.startGameLabel.setText("Gra Rozpoczęta, proszę zgadnij aktualy kurs dla: "
                    + symbol.getFullName());
            this.gameStarted = true;
           
        }


    }

    public void restartGame(ActionEvent actionEvent) {
        this.gameStarted = false;
        this.currencyTextField.setText("");
        this.startGame(actionEvent);
    }

    public void guessAnswer(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER && this.gameStarted) {
            try {
                String answer = this.answerTextField.getText();
                double answerParsed = Double.parseDouble(answer);
                answerParsed = Math.round(answerParsed * 100.0) / 100.0;

                int compareValue = Double.compare(this.currency.getValue(), answerParsed);

                if (compareValue == 0) {
                    showWinningWindow(answerParsed);

                } else {
                    String resultMessage = compareValue > 0 ? "Podałeś zbyt małą liczbę" : "Podałeś zbyt dużą liczbę";
                    this.currencyTextField.appendText(resultMessage + "\n");
                }

            } catch (NumberFormatException e) {
                this.currencyTextField.appendText("Nie ma mowy że to aktualny kurs\n");
            }
        }
    }

    private void showWinningWindow(Double answer) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wygrałeś");
        alert.setHeaderText("Gratulacje wygrałeś! odpowiedź to: " + answer);
        alert.showAndWait();
        this.gameStarted = false;
        this.currencyTextField.setText("");
        this.startGameLabel.setText("Naciśnij start by rozpocząć");
        this.answerTextField.setText("");
    }

}
