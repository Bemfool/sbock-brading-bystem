package StockTradingSystem.controller;

import StockTradingSystem.Main;
import StockTradingSystem.controller.utils.ControllerUtils;
import StockTradingSystem.domain.entity.AdminAccount;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import static StockTradingSystem.GlobalInfo.*;

public class AdminLoginUIController implements Initializable {
    private Main application;

    public AdminLoginUIController() {
    }

    public void setApp(Main app) {
        this.application = app;
    }
    public Main getApp() {return this.application; }

    @FXML
    private JFXPasswordField managerPassword;
    @FXML
    private JFXTextField managerId;
    @FXML
    private JFXButton loginBtn;
    private String password;
    private String id;

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void close() {
        getApp().stage.close();
    }

    /**
     * @throws Exception
     */
    public void login() throws Exception {
        // 登陆时先获得文本框中用户输入的信息
        id=managerId.getText();
        password=managerPassword.getText();

        AdminAccount adminAccount = new AdminAccount(id, ControllerUtils.md5(password));
        String json = new Gson().toJson(adminAccount);
        System.out.println(json);
        CustomResp cr = new HttpCommon().doHttp("/admin/login", "POST", json);
        System.out.println(cr.getResultJSON());
        System.out.println(cr.getObjectJSON());
        if(cr.getResultJSON().equals("{\"status\":true,\"cause\":\"\"}")){
            AdminAccount getAccount =  new Gson().fromJson(cr.getObjectJSON(), AdminAccount.class);
            MANAGER_NAME = getAccount.getName();
            MANAGER_ID = getAccount.getId();
            MANAGER_PRIV = getAccount.getPriv();
            MANAGER_PASSWORD =getAccount.getPassword();
            getApp().gotoAdminMainUI();
        }
        else {
            loginBtn.setText("账号或密码错误,请重试");
        }


    }
}
