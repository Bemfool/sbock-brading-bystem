package StockTradingSystem.controller;


import StockTradingSystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static StockTradingSystem.GlobalInfo.*;

public class PersonalInfoUIController implements Initializable {
    private Main application;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        managerId.setText("用户ID: "+MANAGER_ID);
        managerName.setText("用户姓名: "+MANAGER_NAME);
        managerPriv.setText("用户权限: "+MANAGER_PRIV);
    }

    @FXML
    private Label managerName;
    @FXML
    private Label managerId;
    @FXML
    private Label managerPriv;

    public void setApp(Main app) {
        this.application = app;
    }
    public void close(ActionEvent actionEvent) {
        application.floatStage.close();
    }
}



