package StockTradingSystem.controller.fund;


import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.controller.utils.ControllerUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import java.sql.SQLException;

public class finMainUIController extends AdminUIController {
    @FXML
    private StackPane NewAccountBtn;

    @FXML
    private StackPane FinWorkBtn;

    @FXML
    private StackPane InterestBtn;
    

    /* 按钮特效 */
    public void pressNewAccount() { ControllerUtils.btnPress(NewAccountBtn); }
    public void moveNewAccount() { ControllerUtils.btnMove(NewAccountBtn); }
    public void exitNewAccount() { ControllerUtils.btnRelease(NewAccountBtn); }
    public void releaseNewAccount() throws Exception {
    	getApp().gotofinCreateActUI();
        // TODO
    	ControllerUtils.btnRelease(NewAccountBtn);
        
    }
    
    public void pressFinWork() { ControllerUtils.btnPress(FinWorkBtn); }
    public void moveFinWork() { ControllerUtils.btnMove(FinWorkBtn);}
    public void exitFinWork() { ControllerUtils.btnRelease(FinWorkBtn); }
    public void releaseFinWork() throws Exception {
    	getApp().gotofinLoginUI();
    	ControllerUtils.btnRelease(FinWorkBtn);
        
        // TODO
    }
    
    
    
    public void pressInterest() { ControllerUtils.btnPress(InterestBtn); }
    public void moveInterest() { ControllerUtils.btnMove(InterestBtn);}
    public void exitInterest() { ControllerUtils.btnRelease(InterestBtn);}
    public void releaseInterest() throws SQLException {
        ControllerUtils.btnRelease(InterestBtn);
        System.out.println("Adding Interests, this may take a few minutes....");
        FinsysToServer.calcInterests();
        System.out.println("Finish Adding Interest");
    }
    
    @FXML
    void exitFinsys() throws Exception {
    	getApp().gotoAdminMainUI();
    }




}
