package StockTradingSystem.controller;

import javafx.event.ActionEvent;

public class InterManageUIController extends AdminUIController {

    public void modifyPassword() {
    }

    public void gotoMainUI() throws Exception {
        super.getApp().gotoAdminMainUI();
    }

    public void gotoInterManage() throws Exception {
        super.getApp().gotoInternalManageUI();
    }
}
