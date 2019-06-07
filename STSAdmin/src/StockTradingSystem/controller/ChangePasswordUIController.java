package StockTradingSystem.controller;

import StockTradingSystem.Main;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static StockTradingSystem.GlobalInfo.MANAGER_PASSWORD;
import static StockTradingSystem.controller.utils.ControllerUtils.md5;
import static StockTradingSystem.controller.utils.ControllerUtils.showAlert;


public class ChangePasswordUIController implements Initializable {
    private Main application;
    public void setApp(Main app) {
        this.application = app;
    }
    public Main getApp() {return this.application; }

    @Override
    public void initialize(URL url, ResourceBundle rb){ }

    @FXML private PasswordField oldPassword1;
    @FXML private PasswordField newPassword1;
    @FXML private PasswordField newPassword2;
    @FXML private Button confirmBtn;
    private String oldPassword;
    private String path;

    public void confirm(ActionEvent actionEvent) {
        oldPassword= md5(oldPassword1.getText());
        System.out.println(oldPassword);
        System.out.println(MANAGER_PASSWORD);
        if(oldPassword.equals(MANAGER_PASSWORD)){
            if(newPassword1.getText().equals(newPassword2.getText()))
            {
                String reg ="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(newPassword1.getText());
                if (newPassword1.getText().equals(oldPassword1.getText())){
                    showAlert("新密码必须与旧密码不同");
                }
                else if (matcher.matches()) {
                    path="/admin/update/password/"+md5(newPassword1.getText());
                    CustomResp cr = new HttpCommon().doHttp(path, "POST");
                    System.out.println(cr.getResultJSON());
                    System.out.println(cr.getObjectJSON());
                    showAlert("修改成功");
                    getApp().floatStage.close();
                }
                else{
                    if (newPassword1.getText().length()<6){
                        showAlert("新密码必须大于6位");
                    }else if (newPassword1.getText().length()>16){
                        showAlert("新密码必须小于16位");
                    }else{
                        showAlert("新密码必须是数字加字母的组合");
                    }
                }


            }
            else{
                showAlert("两次密码输入不同");
            }
        }
        else{
            showAlert("旧密码输入错误");
        }
    }
}
