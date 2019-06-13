package StockTradingSystem.controller.fund;

import StockTradingSystem.controller.utils.AdminUIController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;


public class finReclaimActUIController extends AdminUIController {

    @FXML
    private JFXPasswordField newPassword1;

    @FXML
    private JFXPasswordField newPassword2;

    @FXML
    private JFXTextField account;

    @FXML
    void confirm() throws Exception {
        String accountnumber=account.getText();
        String pwd1=newPassword1.getText();
        String pwd2=newPassword2.getText();
        if(pwd1.equals(pwd2)){
            int numberflag = 0, letterflag=0;
            for(int i=0;i<pwd1.length();i++){
                if(pwd1.charAt(i) >= '0' && pwd1.charAt(i) <= '9') numberflag++;
                if((pwd1.charAt(i) >= 'a' && pwd1.charAt(i) <= 'z') ||  (pwd1.charAt(i) >= 'A' && pwd1.charAt(i) <= 'Z')) letterflag++;
            }


            if(pwd1.length()<6 || numberflag<=0 || letterflag<=0){
                getApp().FinSysWarningUI("密码不符合要求！");
                return;
            }
            FinsysToServer.setCustomer(Integer.valueOf(accountnumber));
            if(FinsysToServer.changePassword(pwd1)){
                getApp().gotofinMainUI();
                getApp().FinSysWarningUI("reclaim fund account success!" );
            }
            else{
                getApp().FinSysWarningUI("ERROR: Failed reclaim account");
            }

        }
        else{
            getApp().FinSysWarningUI("密码输入错误！");
        }
    }

    @FXML
    void gotofinMainUI() throws Exception{
        getApp().gotofinMainUI();
    }


}
