package StockTradingSystem.controller.fund;

import StockTradingSystem.Main;
import StockTradingSystem.controller.utils.AdminUIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static StockTradingSystem.GlobalInfo.MANAGER_NAME;

public class FinSysWarningUIController implements Initializable {
    private Main application;
    public void setApp(Main app) {
        this.application = app;
    }
    public Main getApp() {return this.application; }

    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    public void confirm() {
        // TODO 确认警告，返回设定涨跌幅限制的界面
        getApp().floatStage.close();
    }
}
