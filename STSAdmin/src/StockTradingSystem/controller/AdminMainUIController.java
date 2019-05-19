package StockTradingSystem.controller;

import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.controller.utils.ControllerUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class AdminMainUIController extends AdminUIController {
    @FXML private StackPane securitiesBusinessBtn;
    @FXML private StackPane fundBusinessBtn;
    @FXML private StackPane interManageBtn;

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
    public void releaseSecuritiesBusiness() throws Exception {
        ControllerUtils.btnRelease(securitiesBusinessBtn);
        getApp().gotoSecuritiesUI();
    }
    public void releaseFundBusiness() throws Exception {
        ControllerUtils.btnRelease(fundBusinessBtn);
        getApp().gotoFundMainUI();
    }
    public void releaseInterManage() throws Exception {
        ControllerUtils.btnRelease(interManageBtn);
        getApp().gotoInternalManageUI();
    }


}
