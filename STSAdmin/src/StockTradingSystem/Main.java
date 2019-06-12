package StockTradingSystem;

import StockTradingSystem.controller.*;
import StockTradingSystem.controller.fund.*;
import StockTradingSystem.controller.inter_manage.InterManageUIController;
import StockTradingSystem.controller.inter_manage.StockDetailUIController;
import StockTradingSystem.controller.securities.SecuritiesUIController;
import StockTradingSystem.http_utils.CustomResp;
import StockTradingSystem.http_utils.HttpCommon;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
    public Stage stage;
    public Stage floatStage;

    /**
     *  用于结束股票/指数信息的刷新
     */
    public static boolean flashStockFlag = true;

    /**
     * 用于界面拖拽
     */
    private double x0, y0, x_stage, y_stage;

    /**
     * Application初始化
     *
     * @param primaryStage 初始Stage
     */
    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        try {
            gotoAdminLoginUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JavaFX应用结束的时候调用，用于关闭刷新线程。
     */
    @Override
    public void stop() {
        System.out.println(" - Close - ");
        flashStockFlag = false;
    }

    /* ******************************* 原窗口跳转 *************************************** */

    public void gotoAdminLoginUI() throws Exception {
        stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        AdminLoginUIController loginUI = (AdminLoginUIController)replaceSceneContent("fxml/AdminLoginUI.fxml");
        loginUI.setApp(this);
        stage.getScene().setOnMouseDragged(event -> {
            stage.setX(x_stage + event.getScreenX() - x0);
            stage.setY(y_stage + event.getScreenY() - y0);
        });
        stage.getScene().setOnDragEntered(null);
        stage.getScene().setOnMousePressed(event -> {
            x0 = event.getScreenX();
            y0 = event.getScreenY();
            x_stage = stage.getX();
            y_stage = stage.getY();
        });
        stage.show();
    }

    public void gotoAdminMainUI() throws Exception {
        stage.close();
        stage = new Stage();
        stage.setTitle("股票交易系统(管理员) Stock Trading System(Admin) - B");
        AdminMainUIController adminMainUI = (AdminMainUIController)replaceSceneContent("fxml/AdminMainUI.fxml");
        adminMainUI.setApp(this);
        stage.show();
    }

    public void gotoSecuritiesUI() throws Exception {
        stage.setResizable(true);
        SecuritiesUIController securitiesUI = (SecuritiesUIController)replaceSceneContent("fxml/securities/SecuritiesUI.fxml");
        securitiesUI.setApp(this);
    }

    public void FinSysWarningUI(String warning) throws Exception {
        floatStage = new Stage();
        floatStage.setTitle("Warning");
        floatStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream("fxml/fund/FinSysWarningUI.fxml");
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("fxml/fund/FinSysWarningUI.fxml"));
        AnchorPane page;
        try {
            page = loader.load();
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);

        Text text=new Text(80,80,warning);
        page.getChildren().add(text);

        floatStage.setScene(scene);
        floatStage.show();
        FinSysWarningUIController FinSysWarningUI= loader.getController();
        FinSysWarningUI.setApp(this);
    }



    public void gotofinMainUI() throws Exception {
        stage.close();
        stage = new Stage();
        stage.setTitle("资金账户系统(管理员) Finance System(Admin) - B");
        finMainUIController finMainUI = (finMainUIController)replaceSceneContent("fxml/fund/finMainUI.fxml");
        finMainUI.setApp(this);
        stage.show();
    }

    public void gotofinLogUI() throws Exception {
        stage.close();
        stage = new Stage();
        stage.setTitle("资金账户系统(管理员) Finance System(Admin) - B");
        finLogUIController finLogUI = (finLogUIController)replaceSceneContent("fxml/fund/finSearchLogUI.fxml");
        finLogUI.initialize();
        finLogUI.setApp(this);
        stage.show();
    }


    public void gotofinworkUI() throws Exception {
        stage.close();
        stage = new Stage();
        stage.setTitle("资金账户系统(管理员) Finance System(Admin) - B");
        finworkUIController finworkUI = (finworkUIController)replaceSceneContent("fxml/fund/finWorkUI.fxml");
        finworkUI.setApp(this);
        stage.show();
    }

    public void gotofinRepoDrawUI() throws Exception {
        stage.close();
        stage = new Stage();
        stage.setTitle("资金账户系统(管理员) Finance System(Admin) - B");
        finRepoDrawUIController finRepDrawUI = (finRepoDrawUIController)replaceSceneContent("fxml/fund/finRepoDrawUI.fxml");
        finRepDrawUI.setApp(this);
        stage.show();
    }

    public void gotofinCreateActUI() throws Exception {
        stage.close();
        stage = new Stage();
        stage.setTitle("资金账户系统(管理员) Finance System(Admin) - B");
        finCreateActUIController finCreateActUI = (finCreateActUIController)replaceSceneContent("fxml/fund/finCreateActUI.fxml");
        finCreateActUI.setApp(this);
        stage.show();
    }
    public void gotofinChangePwdUI() throws Exception {
        stage.close();
        stage = new Stage();
        stage.setTitle("资金账户系统(管理员) Finance System(Admin) - B");
        finChangePwdUIController finCreateActUI = (finChangePwdUIController)replaceSceneContent("fxml/fund/finChangePwdUI.fxml");
        finCreateActUI.setApp(this);
        stage.show();
    }

    public void gotofinLoginUI() throws Exception {
        stage.close();
        stage = new Stage();
        stage.setTitle("资金账户系统(管理员) Finance System(Admin) - B");
        finLoginUIController finLoginUI = (finLoginUIController)replaceSceneContent("fxml/fund/finLoginUI.fxml");
        finLoginUI.setApp(this);
        stage.show();
    }

    public void gotoInternalManageUI() throws Exception {
        stage.setResizable(true);
        InterManageUIController interManageUI = (InterManageUIController)replaceSceneContent("fxml/inter_manage/InterManageUI.fxml");
        interManageUI.setApp(this);
    }

    /* ******************************* 新建浮窗 *************************************** */

    /**
     *  创建一个新窗口用于显示股票的详细信息。
     */
    public void createStockDetailUI() {
        AnchorPane page = new AnchorPane();
        floatStage = new Stage();
        floatStage.setTitle("股票详细界面 - Detailed Stock Information");
        floatStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader();
        try (InputStream ignored = Main.class.getResourceAsStream("fxml/inter_manage/StockDetailUI.fxml")) {
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("fxml/inter_manage/StockDetailUI.fxml"));
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(page);
        floatStage.setScene(scene);
        floatStage.show();
        StockDetailUIController StockDetailUI = loader.getController();
        StockDetailUI.setApp(this);
    }

    /**
     * 创建一个新窗口用于显示个人的详细信息。
     */
    public void createPersonalInfoUI() {
        AnchorPane page = new AnchorPane();
        floatStage = new Stage();
        floatStage.setAlwaysOnTop(true);
        floatStage.initModality(Modality.APPLICATION_MODAL);
        floatStage.setTitle("管理员 - 个人信息 Administrator Personal Information");
        floatStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader();
        try (InputStream ignored = Main.class.getResourceAsStream("fxml/PersonalInfoUI.fxml")) {
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("fxml/PersonalInfoUI.fxml"));
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(page);

        floatStage.setScene(scene);
        floatStage.show();
        PersonalInfoUIController personalInfoUI= loader.getController();
        personalInfoUI.setApp(this);
    }

    /**
     * 新建一个修改密码的浮窗。
     */
    public void createChangePasswordUI() {
        AnchorPane page = new AnchorPane();
        floatStage = new Stage();
        floatStage.setAlwaysOnTop(true);
        floatStage.initModality(Modality.APPLICATION_MODAL);
        floatStage.setTitle("管理员 - 更改密码 Administrator - Change Password");
        floatStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader();
        try (InputStream ignored = Main.class.getResourceAsStream("fxml/ChangePasswordUI.fxml")) {
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("fxml/ChangePasswordUI.fxml"));
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(page);
        floatStage.setScene(scene);
        floatStage.show();
        ChangePasswordUIController changePasswordUI= loader.getController();
        changePasswordUI.setApp(this);
    }

    /* ******************************* 工具类 *************************************** */

    /**
     * 封装好的界面切换函数
     *
     * @param fxml 要跳转的页面前端文件
     * @return 对应的控制类
     */
    private Initializable replaceSceneContent(String fxml) {
        AnchorPane page = new AnchorPane();
        FXMLLoader loader = new FXMLLoader();
        try (InputStream in = Main.class.getResourceAsStream(fxml)) {
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource(fxml));
            page = loader.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    /* ******************************* Main函数 *************************************** */

    /**
     * Main函数
     *
     * @param args Main函数参数
     */
    public static void main(String[] args) {
        launch(args);
    }
}
