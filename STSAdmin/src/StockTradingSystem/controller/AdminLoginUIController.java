package StockTradingSystem.controller;

import StockTradingSystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminLoginUIController extends AdminUIController {

    public void close() {
        getApp().stage.close();
    }

    public void login() throws Exception {
        // TODO: 登陆判断
        getApp().gotoAdminMainUI();
    }
}
