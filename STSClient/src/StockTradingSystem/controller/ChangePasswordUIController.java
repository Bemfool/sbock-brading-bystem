package StockTradingSystem.controller;

import StockTradingSystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordUIController implements Initializable {
    private Main application;
    public void setApp(Main app) {
        this.application = app;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    public void confirm(ActionEvent actionEvent) {
        // TODO
        application.floatStage.close();
    }
}
