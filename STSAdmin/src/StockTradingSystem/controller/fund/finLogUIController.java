package StockTradingSystem.controller.fund;

import StockTradingSystem.controller.utils.AdminUIController;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


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
	
	    private void initialize(String log) {
	    	LogLabel.setText(log);
	    }
	    
	    public void setText(String log) {
	    	initialize(log);
	    }
}
		

