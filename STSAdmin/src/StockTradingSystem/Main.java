package StockTradingSystem;

import StockTradingSystem.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;

public class Main extends Application {
    public Stage stage;
    public Stage floatStage;

    /* 用于界面拖拽 */
    private double x0, y0, x_stage, y_stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        gotoAdminLoginUI();
    }

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

    public void gotoInternalManageUI() throws Exception {
        stage.setResizable(true);
        InterManageUIController interManageUI = (InterManageUIController)replaceSceneContent("fxml/InterManageUI.fxml");
        interManageUI.setApp(this);
    }

    public void createConfirmWarningUI() throws Exception {
        floatStage = new Stage();
        floatStage.setTitle("Warning");
        floatStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream("fxml/ConfirmWarningUI.fxml");
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("fxml/ConfirmWarningUI.fxml"));
        AnchorPane page;
        try {
            page = loader.load();
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        floatStage.setScene(scene);
        floatStage.show();
        ConfirmWarningUIController confirmWarningUI= loader.getController();
        confirmWarningUI.setApp(this);
    }

    // TODO 到详细界面
    public void gotoStockDetailUI() throws Exception {
        stage.setResizable(true);
        StockDetailUIController stockDetailUI = (StockDetailUIController)replaceSceneContent("fxml/StockDetailUI.fxml");
        stockDetailUI.setApp(this);
    }


    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
