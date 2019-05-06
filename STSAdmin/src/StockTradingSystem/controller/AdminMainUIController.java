package StockTradingSystem.controller;

import StockTradingSystem.ControllerUtils;
import StockTradingSystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainUIController implements Initializable {
    @FXML private StackPane securitiesBusinessBtn;
    @FXML private StackPane fundBusinessBtn;
    @FXML private StackPane interManageBtn;

    private Main application;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setApp(Main app) {
        this.application = app;
    }

    public void personalInfo(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void modifyPassword(ActionEvent actionEvent) {
    }

    public void pressSecuritiesBusiness() {
        ControllerUtils.btnPress(securitiesBusinessBtn);
    }

    public void releaseSecuritiesBusiness() {
        ControllerUtils.btnRelease(securitiesBusinessBtn);
    }

    public void pressFundBusiness() {
        ControllerUtils.btnPress(fundBusinessBtn);
    }

    public void releaseFundBusiness() {
        ControllerUtils.btnRelease(fundBusinessBtn);
    }

    public void pressInterManage() {
        ControllerUtils.btnPress(interManageBtn);
    }

    public void releaseInterManage() throws Exception {
        ControllerUtils.btnRelease(interManageBtn);
        application.gotoInternalManageUI();
    }
}
