package StockTradingSystem.controller.fund;

import StockTradingSystem.controller.utils.AdminUIController;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class finCreateActUIController extends AdminUIController {

	@FXML
	private JFXTextField StockID;
	@FXML
	private JFXTextField InitialMoney;
	@FXML
	private JFXTextField Password;

	
    @FXML
    public void gotofinMainUI() throws Exception {
    	getApp().gotofinMainUI();
    }

    @FXML
    public void confirm() throws Exception {
    	
    	String stockid=StockID.getText();
		String money= InitialMoney.getText();
		String password=Password.getText();
    	
    	System.out.println("creating new account....");
    	int numberflag = 0, letterflag=0;
    	for(int i=0;i<password.length();i++){
    		if(password.charAt(i) >= '0' && password.charAt(i) <= '9') numberflag++;
    		if((password.charAt(i) >= 'a' && password.charAt(i) <= 'z') ||  (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')) letterflag++;
		}


		if(password.length()<6 || numberflag<=0 || letterflag<=0){
			getApp().FinSysWarningUI("密码不符合要求！");
			return;
		}

    	long newFinID =FinsysToServer.CreateAccount(stockid, password, Double.valueOf(money));
    	
 
    	if(newFinID<0) {
    		//System.out.println("Error");
    		getApp().FinSysWarningUI("CANNOT CREATE ACCOUNT!");
    		getApp().gotofinMainUI();
    	}
    	else {
    		//System.out.println("Create new Accout at: " + newFinID + "\n associate stock account : "+ stockid); 
    		getApp().FinSysWarningUI("Create new Accout at: " + newFinID + "\n associate stock account : "+ stockid);
    		System.out.println(money+"  "+password);
            getApp().gotofinworkUI();
    	}
    
    	
    }
   
    	

}
