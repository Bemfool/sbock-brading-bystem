package StockTradingSystem.controller;


import StockTradingSystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static StockTradingSystem.GlobalInfo.*;

public class PersonalInfoUIController {
    private Main application;


    @FXML
    private Label managerName;
    @FXML
    private Label managerId;
    @FXML
    private Label managerPriv;

    public void setApp(Main app) {
        managerId.setText("用户ID: "+MANAGER_ID);
        managerName.setText("用户姓名: "+MANAGER_NAME);
        managerPriv.setText("用户权限: "+MANAGER_PRIV);
        this.application = app;
    }
    public void close(ActionEvent actionEvent) {
        application.floatStage.close();
    }
}



