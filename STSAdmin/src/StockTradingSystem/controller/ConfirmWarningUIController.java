package StockTradingSystem.controller;

import StockTradingSystem.controller.utils.AdminUIController;

public class ConfirmWarningUIController extends AdminUIController {
    public void confirm() {
        // TODO 确认警告，返回设定涨跌幅限制的界面
        getApp().floatStage.close();
    }
}
