package StockTradingSystem.controller.utils;

import StockTradingSystem.Main;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static StockTradingSystem.GlobalInfo.*;

public class AdminUIController implements Initializable {
    private Main application;
    public void setApp(Main app) {
        this.application = app;
    }
    public Main getApp() {return this.application; }
    @FXML
    Text welcome;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        welcome.setText(MANAGER_NAME);
    }

    public void personalInfo() throws Exception {
        getApp().createPersonalInfoUI();
    }

    public void logout() throws Exception {
        MANAGER_ID = "";
        MANAGER_NAME = "";
        MANAGER_PRIV = -1;
        MANAGER_PASSWORD = "";
        CustomResp cr = new HttpCommon().doHttp("/admin/logout", "POST");
        application.stage.close();
        application.gotoAdminLoginUI();
    }

    public void quit() {
        application.stage.close();
    }

    public void modifyPassword() throws Exception {
        getApp().createChangePasswordUI();
    }
}
