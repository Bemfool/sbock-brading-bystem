package StockTradingSystem.controller;

import StockTradingSystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginUIController implements Initializable {
    private Main application;
    @FXML private ToggleGroup privCheck;
    @FXML private PasswordField passwordField;
    @FXML private TextField userField;

    public void loginButton(ActionEvent actionEvent) throws Exception {
        // 需要判断账号或者密码是否为空
        // TODO
        RadioButton selectedBtn = (RadioButton)privCheck.getSelectedToggle();
        if(isAuthorized(userField.getText(), passwordField.getText(), selectedBtn.getText())) {
            if(selectedBtn.getText().equals("证券账户")) {
                // 进入证券账户的首页
                // TODO
            } else {
                // 进入管理员账户的首页
                application.gotoAdminUI();
            }
        }

    }
    public void register(ActionEvent mouseEvent) {
        // 进入注册账户界面
        // TODO
        System.out.println("register");
    }

    public void forgetPassword(ActionEvent mouseEvent) {
        // 进入找回密码界面
        // TODO
        System.out.println("forget password.");
    }

    public boolean isAuthorized(String user, String password, String selectedBtn) {
        // 通过数据库判断是否账号密码吻合
        // TODO
        return true;
    }

    public void setApp(Main app) {
        this.application = app;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

}
