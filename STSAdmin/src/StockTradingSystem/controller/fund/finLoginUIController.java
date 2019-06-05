package StockTradingSystem.controller.fund;


import StockTradingSystem.controller.utils.AdminUIController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import java.io.IOException;


public class finLoginUIController extends AdminUIController {


    @FXML
    private JFXTextField AccountFeild;

    @FXML
    private JFXButton ConfirmBtn;

    @FXML
    private JFXPasswordField finsysPwdFeild;

    @FXML
    void Confirm() throws Exception, IOException {
    	String finAccount=AccountFeild.getText();
    	String password=finsysPwdFeild.getText();
    	
    	if(FinsysToServer.FinsysLogin(Long.valueOf(finAccount), password))
    		getApp().gotofinworkUI();
    	else
    		//System.out.println("无法登录");
    		getApp().FinSysWarningUI("账户名或密码错误");
    }

    @FXML
    void gotofinMainUI() throws Exception {
    	getApp().gotofinMainUI();
    }



}

