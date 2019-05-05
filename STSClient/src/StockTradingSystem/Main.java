package StockTradingSystem;

import StockTradingSystem.controller.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;

public class Main extends Application {
    public Stage stage;
    public Stage floatStage;

    private double x0, y0, x_stage, y_stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        gotoClientLoginUI();
        stage.show();
    }

    public void gotoClientLoginUI() throws Exception {
        /* 设置不可拉伸 */
        stage.setResizable(false);

        /* 去掉标题 */
        stage.initStyle(StageStyle.TRANSPARENT);

        ClientLoginUIController clientLoginUI = (ClientLoginUIController)replaceSceneContent("fxml/ClientLoginUI.fxml");
        clientLoginUI.setApp(this);

        /* 设置可拖拽 */
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
