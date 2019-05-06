package StockTradingSystem;

import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ControllerUtils {
    static public void btnPress(StackPane btn) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0.3,0,1, 0.8));
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(19.5);
        dropShadow.setSpread(0.03);
        btn.setEffect(dropShadow);
    }

    static public void btnRelase(StackPane btn) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0,0,0, 0.3));
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(19.5);
        dropShadow.setSpread(0.03);
        btn.setEffect(dropShadow);
    }
}