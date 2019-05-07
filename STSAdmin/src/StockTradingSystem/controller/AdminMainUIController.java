package StockTradingSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class AdminMainUIController extends AdminUIController {
    @FXML private StackPane securitiesBusinessBtn;
    @FXML private StackPane fundBusinessBtn;
    @FXML private StackPane interManageBtn;

    public void modifyPassword(ActionEvent actionEvent) {
    }

    /* 按钮特效 */
    public void pressSecuritiesBusiness() { ControllerUtils.btnPress(securitiesBusinessBtn); }
    public void pressFundBusiness() { ControllerUtils.btnPress(fundBusinessBtn); }
    public void pressInterManage() { ControllerUtils.btnPress(interManageBtn); }
    public void moveSecuritiesBusiness() { ControllerUtils.btnMove(securitiesBusinessBtn); }
    public void moveFundBusiness() { ControllerUtils.btnMove(fundBusinessBtn);}
    public void moveInterManage() { ControllerUtils.btnMove(interManageBtn);}
    public void exitSecuritiesBusiness() { ControllerUtils.btnRelease(securitiesBusinessBtn); }
    public void exitFundBusiness() { ControllerUtils.btnRelease(fundBusinessBtn); }
    public void exitInterManage() { ControllerUtils.btnRelease(interManageBtn);}
    public void releaseSecuritiesBusiness() {
        ControllerUtils.btnRelease(securitiesBusinessBtn);
        // TODO
    }
    public void releaseFundBusiness() {
        ControllerUtils.btnRelease(fundBusinessBtn);
        // TODO
    }
    public void releaseInterManage() throws Exception {
        ControllerUtils.btnRelease(interManageBtn);
        getApp().gotoInternalManageUI();
    }


}
