package StockTradingSystem.controller.fund;

import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.controller.utils.ControllerUtils;
import StockTradingSystem.database.to_be_replaced.finSysDB;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class finworkUIController extends AdminUIController {
    @FXML private StackPane SearchLogBtn;
    @FXML private StackPane ChangeBalance;
    @FXML private StackPane ChangeState;
    @FXML private StackPane ChangePwd;
    @FXML private StackPane DeleteAccount;
    
    private finSysDB myDB=finSysDB.getInstence();
    @FXML
    void gotofinMainUI() throws Exception {
    	getApp().gotoFundMainUI();
    }
    

    /* 按钮特效 */
    //=================SearchLog=============
   
    public void moveSearchLog( ) {   ControllerUtils.btnMove(SearchLogBtn);	 }
    public void pressSearchLog( ) {	ControllerUtils.btnPress(SearchLogBtn);  }
    public void exitSearchLog( ) { ControllerUtils.btnRelease(SearchLogBtn);    }
    public void releaseSearchLog( ) throws Exception {  
    	ControllerUtils.btnRelease(SearchLogBtn);
    	//myDB.getDB().logSearch(myDB.getfinID());

    }
    
    //================ChangBalance=============
    public  void exitChangeBalance( ) {	ControllerUtils.btnRelease(ChangeBalance);   }
    public void moveChangeBalance( ) { ControllerUtils.btnMove(ChangeBalance); }
    public void pressChangeBalance( ) {  ControllerUtils.btnPress(ChangeBalance);  }
    public void releaseChangeBalance( ) throws Exception {
    	ControllerUtils.btnRelease(ChangeBalance);
    	getApp().gotofinChangeBalanceUI();
    }

    
    
    
    //============changePwd============
   
    public void exitChangePwd( ) {ControllerUtils.btnRelease(ChangePwd);    }
    public void moveChangePwd( ) {ControllerUtils.btnMove(ChangePwd);    }
    public void pressChangePwd( ) {ControllerUtils.btnPress(ChangePwd);    }
    public  void releaseChangePwd( ) {
    	ControllerUtils.btnRelease(ChangePwd);
    	
    }
    
    
    //==================changeState===============
    public void exitChangeState( ) {ControllerUtils.btnRelease(ChangeState);    }
    public void moveChangeState( ) {ControllerUtils.btnMove(ChangeState);    }
    public void pressChangeState( ) {  ControllerUtils.btnPress(ChangeState);   }
    public void releaseChangeState( ) {
    	ControllerUtils.btnRelease(ChangeState);
    }
   
    
    
   //==============deleteAccount==============
    public void moveDeleteAccount( ) {ControllerUtils.btnMove(DeleteAccount);    }
    public void exitDeleteAccount( ) {ControllerUtils.btnRelease(DeleteAccount);    }
    public void pressDeleteAccount( ) {ControllerUtils.btnPress(DeleteAccount);    }
    public void releaseDeleteAccount( ) {
    	ControllerUtils.btnRelease(DeleteAccount);
    }


   /* public void gotofinMainUI() throws Exception {
    	super.getApp().gotoAdminMainUI();
    }*/

    

}
