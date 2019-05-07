package StockTradingSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class AdminMainUIController extends AdminUIController {
    @FXML private StackPane securitiesBusinessBtn;
    @FXML private StackPane fundBusinessBtn;
    @FXML private StackPane interManageBtn;

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
        getApp().gotoInternalManageUI();
    }
}
