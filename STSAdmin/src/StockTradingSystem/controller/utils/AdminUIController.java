package StockTradingSystem.controller.utils;

import StockTradingSystem.Main;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUIController implements Initializable {
    private Main application;
    public void setApp(Main app) {
        this.application = app;
    }
    public Main getApp() {return this.application; }

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void personalInfo() {
        // TODO: 增加个人信息跳出窗口
    }

    public void logout() throws Exception {
        // TODO: 跳转前还需要对用户信息进行清除
        application.stage.close();
        application.gotoAdminLoginUI();
    }

    public void quit() {
        application.stage.close();
    }

    public void modifyPassword() { }
}
