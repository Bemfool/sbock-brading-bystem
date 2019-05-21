package bgroup.stocktradingsystem.stsadmin.control.fund;

import bgroup.stocktradingsystem.stsadmin.control.utils.AdminBasicController;
import bgroup.stocktradingsystem.stsadmin.database.to_be_replaced.finSysDB;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class finCreateAccountUIController extends AdminBasicController {

	    @FXML
	    private JFXTextField InitialAmount;

	    @FXML
	    private JFXTextField Password;

	    @FXML
	    private JFXTextField StockAccount;

	private finSysDB myDB=finSysDB.getInstence();
	
    public void close() {
//        getApp().stage.close();
    }

    public void confirm() throws Exception {
        
    	String stockSysAccount=StockAccount.getText();
    	String password= Password.getText();
    	String balance= InitialAmount.getText();
    	
    	
    	System.out.println("creating new account....");
    	String newFinID = myDB.getDB().createNewFinAccount(stockSysAccount, password, balance);
    	System.out.println("Create new Accout at: " + newFinID + "/n associate stock account : "+ stockSysAccount);    	
    	
//        getApp().gotofinworkUI();
    }
}
