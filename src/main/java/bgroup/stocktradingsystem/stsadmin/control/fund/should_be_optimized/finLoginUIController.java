package bgroup.stocktradingsystem.stsadmin.control.fund.should_be_optimized;


import bgroup.stocktradingsystem.stsadmin.control.utils.AdminBasicController;
import bgroup.stocktradingsystem.stsadmin.database.to_be_replaced.finSysDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class finLoginUIController extends AdminBasicController {
	
    @FXML
    private JFXPasswordField UserPwd;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXTextField UserId;
    
	private finSysDB myDB=finSysDB.getInstence();
  
	public void close() {
//	    getApp().stage.close();
    }

    public void login() throws Exception {
    	String userpwd=UserPwd.getText();
    	String userid=UserId.getText();
    	
    	
    	if(true/*myDB.getDB().checkPwd(userid, userpwd)*/) {
    		myDB.setfinID(userid);
//            getApp().gotofinworkUI();
    	}
    	else
    		System.out.println("Ivalid UserID or Password!");
    	
    }
}
