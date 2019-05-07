package StockTradingSystem.controller;

import StockTradingSystem.Main;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUIController implements Initializable {
    private Main application;
    public void setApp(Main app) {
        this.application = app;
    }
    public Main getApp() {return this.application; }

    @Override
    public void initialize(URL url, ResourceBundle rb){}
}
