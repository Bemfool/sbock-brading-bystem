package bgroup.stocktradingsystem.stsadmin.control;

import bgroup.stocktradingsystem.stsadmin.StsAdminApplication;
import bgroup.stocktradingsystem.stsadmin.control.utils.AdminBasicController;
import bgroup.stocktradingsystem.stsadmin.control.utils.ControllerUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class AdminMainController extends AdminBasicController {
    Stage stage = StsAdminApplication.getStage();

    @FXML private StackPane securitiesBusinessBtn;
    @FXML private StackPane fundBusinessBtn;
    @FXML private StackPane interManageBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        super.initialize(url,rb );
        stage.setResizable(true);
        stage.setTitle("股票交易系统(管理员) Stock Trading System(Admin) - B");
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
    public void releaseSecuritiesBusiness() throws Exception {
        ControllerUtils.btnRelease(securitiesBusinessBtn);
//        getApp().gotoSecuritiesUI();
    }
    public void releaseFundBusiness() throws Exception {
        ControllerUtils.btnRelease(fundBusinessBtn);
//        getApp().gotoFundMainUI();
    }
    public void releaseInterManage() throws Exception {
        ControllerUtils.btnRelease(interManageBtn);
//        getApp().gotoInternalManageUI();
    }


}
