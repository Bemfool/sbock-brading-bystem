package StockTradingSystem.controller;

import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.controller.utils.ControllerUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import static StockTradingSystem.GlobalInfo.MANAGER_PRIV;
import static StockTradingSystem.controller.utils.ControllerUtils.showAlert;

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
        getApp().gotofinMainUI();
    }
    public void releaseInterManage() throws Exception {
        if (MANAGER_PRIV!=0){
            ControllerUtils.btnRelease(interManageBtn);
            getApp().gotoInternalManageUI();
        }else{
            showAlert("[警告]您没有进入内部管理的权限！");
        }

    }


}
