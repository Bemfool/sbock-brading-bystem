package StockTradingSystem.controller.fund;

import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.domain.entity.TransactionLog;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.List;


public class finLogUIController extends AdminUIController {


	    @FXML
	    private JFXButton ConfirmBtn;

	    @FXML
	    private Label LogLabel;
	    
	    

	    @FXML
	    void Confirm() throws Exception {
	    	getApp().gotofinworkUI();
	    }
	
	    @FXML
	    void gotoFinWork() throws Exception {
	    	getApp().gotofinworkUI();
	    }

	    @FXML
	    void gotofinMainUI() throws Exception {
	    	getApp().gotofinMainUI();
	    }
	
	    public void initialize() {
	    	List<TransactionLog> logs= FinsysToServer.GetTranscationLog();
	    	if(logs==null){
				LogLabel.setText("ERROR: cannot get transcation log");
			}
	    	else{
				StringBuilder logstring= new StringBuilder("ActionID  ChangeAmount  ActionTime  Comments\n");
				for(TransactionLog i:logs){
					logstring.append(i.getActionId()+"        "+i.getChangeAmount()+"        "
							+i.getActionTime()+"      "+i.getComment()+"\n");
				}

				LogLabel.setText(logstring.toString());
			}

	    }
	    

}
		

