package StockTradingSystem.controller.fund;


import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.controller.utils.ControllerUtils;
import StockTradingSystem.database.to_be_replaced.finSysDB;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import java.sql.SQLException;


public class fundMainUIController extends AdminUIController {
    @FXML
    private StackPane NewAccountBtn;

    @FXML
    private StackPane FinWorkBtn;

    @FXML
    private StackPane InterestBtn;
    
    private finSysDB myDB=finSysDB.getInstence();
    

    /* 按钮特效 */
    public void pressNewAccount() { ControllerUtils.btnPress(NewAccountBtn); }
    public void moveNewAccount() { ControllerUtils.btnMove(NewAccountBtn); }
    public void exitNewAccount() { ControllerUtils.btnRelease(NewAccountBtn); }
    public void releaseNewAccount() throws Exception {
        ControllerUtils.btnRelease(NewAccountBtn);
        getApp().gotofinCreateAccountUI();
        // TODO
    }
    
    public void pressFinWork() { ControllerUtils.btnPress(FinWorkBtn); }
    public void moveFinWork() { ControllerUtils.btnMove(FinWorkBtn);}
    public void exitFinWork() { ControllerUtils.btnRelease(FinWorkBtn); }
    public void releaseFinWork() throws Exception {
        ControllerUtils.btnRelease(FinWorkBtn);
        getApp().gotofinLoginUI();
        // TODO
    }
    
    
    public void pressInterest() { ControllerUtils.btnPress(InterestBtn); }
    public void moveInterest() { ControllerUtils.btnMove(InterestBtn);}
    public void exitInterest() { ControllerUtils.btnRelease(InterestBtn);}
    public void releaseInterest() throws SQLException {
        ControllerUtils.btnRelease(InterestBtn);
        System.out.println("Adding Interests, this may take a few minutes....");
        myDB.getDB().calcInterest();
        System.out.println("Finished adding Interest");
    }


}
