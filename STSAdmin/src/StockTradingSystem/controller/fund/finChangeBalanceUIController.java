package StockTradingSystem.controller.fund;


import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.database.to_be_replaced.finSysDB;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class finChangeBalanceUIController extends AdminUIController {

    @FXML
    private JFXTextField ChangeAmount;
    
	private finSysDB myDB=finSysDB.getInstence();
	
    public void close() {
        getApp().stage.close();
    }

    public void confirm() throws Exception {
        
    	String amountstring = ChangeAmount.getText();
    	double amount = Double.valueOf(amountstring);
    	System.out.println("the amount to be changed: "+amountstring);
    	myDB.getDB().changeBal(myDB.getfinID(), amount, "Deposit / Withdraw");
    	
    	
        getApp().gotofinworkUI();
    }
}
