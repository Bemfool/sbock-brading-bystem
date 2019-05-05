package StockTradingSystem.controller;

import StockTradingSystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class AdminUIControlller implements Initializable {
    public Text welcome;
    private Main application;
    @FXML private HBox menuBarParent;
    @FXML private MenuBar menuBar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcome.setText("你好, " + getUserName() + "!");
        // TODO
    }

    private String getUserName() {
        // TODO
        return "Jack";
    }

    public void setApp(Main app) {
        this.application = app;
    }

    public void modifyPassword() {
        System.out.println("To modify password");
        // TODO 修改密码
    }

    public void personalInfo(ActionEvent actionEvent) {
        // TODO 个人信息界面
    }

    public void logout(ActionEvent actionEvent) throws Exception {
        application.gotoLoginUI();
    }

    public void quit(ActionEvent actionEvent) throws Exception {
        // TODO(OPTIONAL) 关闭界面
        application.stage.close();
    }
}
