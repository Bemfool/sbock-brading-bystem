package StockTradingSystem.controller.utils;

import StockTradingSystem.Main;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import StockTradingSystem.http_utils.Result;
import com.google.gson.Gson;
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
    @FXML Text welcome;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        welcome.setText(MANAGER_NAME);
    }

    public void personalInfo() {
        getApp().createPersonalInfoUI();
    }

    public void logout() throws Exception {
        MANAGER_ID = "";
        MANAGER_NAME = "";
        MANAGER_PRIV = -1;
        MANAGER_PASSWORD = "";
        CustomResp cr = new HttpCommon().doHttp("/admin/logout", "POST");
        Result result = new Gson().fromJson(cr.getResultJSON(), Result.class);
        if(result.isStatus()) {
            application.stage.close();
            application.gotoAdminLoginUI();
        } else {
            ControllerUtils.showAlert("注销用户错误: " + result.getReasons());
        }
    }

    public void quit() {
        application.stage.close();
    }

    public void modifyPassword() {
        getApp().createChangePasswordUI();
    }

    public void back2AdminMainUI() {
        try {
            getApp().gotoAdminMainUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
