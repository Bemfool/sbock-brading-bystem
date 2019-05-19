package StockTradingSystem.controller;

import StockTradingSystem.controller.utils.AdminUIController;

public class AdminLoginUIController extends AdminUIController {

    public void close() {
        getApp().stage.close();
    }

    public void login() throws Exception {
        // TODO: 登陆判断
        getApp().gotoAdminMainUI();
    }
}
