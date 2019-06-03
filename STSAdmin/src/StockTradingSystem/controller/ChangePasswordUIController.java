package StockTradingSystem.controller;

import StockTradingSystem.Main;
import StockTradingSystem.controller.utils.ControllerUtils;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

import static StockTradingSystem.GlobalInfo.MANAGER_PASSWORD;


public class ChangePasswordUIController implements Initializable {
    private Main application;
    public void setApp(Main app) {
        this.application = app;
    }
    public Main getApp() {return this.application; }

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    @FXML private PasswordField oldPassword1;
    @FXML private PasswordField newPassword1;
    @FXML private PasswordField newPassword2;
    @FXML private Button confirmBtn;
    private String oldPassword;
    private String path;

    public void confirm(ActionEvent actionEvent) {
        oldPassword= ControllerUtils.md5(oldPassword1.getText());
        System.out.println(oldPassword);
        System.out.println(MANAGER_PASSWORD);
        if(oldPassword.equals(MANAGER_PASSWORD)){
            if(newPassword1.getText().equals(newPassword2.getText()))
            {
                path="/admin/update/password/"+ControllerUtils.md5(newPassword1.getText());
                CustomResp cr = new HttpCommon().doHttp(path, "POST");
                System.out.println(cr.getResultJSON());
                System.out.println(cr.getObjectJSON());
                ControllerUtils.showAlert("修改成功");
                getApp().floatStage.close();
            }
            else{
                ControllerUtils.showAlert("两次密码输入不同");
            }
        }
        else{
            ControllerUtils.showAlert("旧密码输入错误");
        }
    }
}
