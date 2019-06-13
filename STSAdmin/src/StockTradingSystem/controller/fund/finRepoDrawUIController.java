package StockTradingSystem.controller.fund;


import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.domain.entity.FundAccount;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class finRepoDrawUIController extends AdminUIController {

   
    @FXML
    private Text welcome;
    @FXML
    private JFXTextField MoneyFeild;
    
    @FXML
    public void gotoFinWork() throws Exception {
    	getApp().gotofinworkUI();
    }

    @FXML
    public void gotofinMainUI() throws Exception {
    	getApp().gotofinMainUI();
    }
    
  
	public void Confirm() throws Exception {
		
    	String Amount=MoneyFeild.getText();
        if(!FinsysToServer.getState()){
            getApp().FinSysWarningUI("用户已被冻结");
            return;
        }

    	FinsysToServer.Reposit_Withdraw(Double.valueOf(Amount));
    	FundAccount user= FinsysToServer.GetUserInfo();
		System.out.println("转账金额: "+Amount);

		getApp().FinSysWarningUI("转账金额: "+Amount+"\n 当前余额: "+user.getBalance());
    	getApp().gotofinworkUI();
    }

}
