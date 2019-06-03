package StockTradingSystem.controller.utils;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ControllerUtils {
    public static void btnPress(StackPane btn) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0.3,0,1, 0.8));
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(19.5);
        dropShadow.setSpread(0.03);
        btn.setEffect(dropShadow);
    }

    public static void btnRelease(StackPane btn) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0,0,0, 0.3));
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(19.5);
        dropShadow.setSpread(0.03);
        btn.setEffect(dropShadow);
    }

    public static void btnMove(StackPane btn) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0.3,0,1, 0.3));
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(19.5);
        dropShadow.setSpread(0.03);
        btn.setEffect(dropShadow);
    }

    public static void showAlert(String message) {
        Stage window = new Stage();
        window.setAlwaysOnTop(true);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setMinWidth(300);
        window.setMinHeight(150);
        JFXButton button = new JFXButton("确定");
        button.setOnAction(e -> window.close());
        Label label = new Label(message);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public static String md5(String plainText) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

}
