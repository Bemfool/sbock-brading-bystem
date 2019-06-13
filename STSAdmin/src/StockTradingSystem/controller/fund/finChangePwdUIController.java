package StockTradingSystem.controller.fund;

import StockTradingSystem.controller.utils.AdminUIController;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;

public class finChangePwdUIController extends AdminUIController {

    @FXML
    private JFXPasswordField newPassword1;
    @FXML
    private JFXPasswordField newPassword2;

	
    @FXML
    public void gotofinMainUI() throws Exception {
    	getApp().gotofinMainUI();
    }

    @FXML
    public void confirm() throws Exception {
    	String password1=newPassword1.getText();
    	String password2=newPassword2.getText();

    	if(!FinsysToServer.getState()){
    		getApp().FinSysWarningUI("用户已被冻结");
    		return;
		}

    	if(password1.equals(password2)) {

			int numberflag = 0, letterflag=0;
			for(int i=0;i<password1.length();i++){
				if(password1.charAt(i) >= '0' && password1.charAt(i) <= '9') numberflag++;
				if((password1.charAt(i) >= 'a' && password1.charAt(i) <= 'z') ||  (password1.charAt(i) >= 'A' && password1.charAt(i) <= 'Z')) letterflag++;
			}


			if(password1.length()<6 || numberflag<=0 || letterflag<=0){
				getApp().FinSysWarningUI("密码不符合要求！");
				return;
			}

			if(FinsysToServer.changePassword(password1))
    			getApp().gotofinworkUI();
    			
    		else
    			//System.out.println("failed to change password");
    			getApp().FinSysWarningUI("failed to change password");
    	}
    	else {
    		//System.out.println("Please Enter Again");
    		getApp().FinSysWarningUI("Please Enter Again");
    	}
    }
   
    	

}
