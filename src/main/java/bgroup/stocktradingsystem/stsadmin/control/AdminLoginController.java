package bgroup.stocktradingsystem.stsadmin.control;

import bgroup.stocktradingsystem.stsadmin.StsAdminApplication;
import bgroup.stocktradingsystem.stsadmin.control.utils.AdminBasicController;
import bgroup.stocktradingsystem.stsadmin.view.AdminMainView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;


@FXMLController
public class AdminLoginController extends AdminBasicController {
    private Stage stage = StsAdminApplication.getStage();
    /* 用于界面拖拽 */
    private double x0, y0, x_stage, y_stage;

    @FXML AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        super.initialize(url, rb);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        setMovable();
    }

    public void close() {
        stage.close();
    }

    public void login() {
        // TODO: 登陆判断
        stage.close();
        StsAdminApplication.showView(AdminMainView.class, Modality.WINDOW_MODAL);
    }

    private void setMovable() {
        pane.setOnMouseDragged(event -> {
            stage.setX(x_stage + event.getScreenX() - x0);
            stage.setY(y_stage + event.getScreenY() - y0);
        });
        pane.setOnDragEntered(null);
        pane.setOnMousePressed(event -> {
            x0 = event.getScreenX();
            y0 = event.getScreenY();
            x_stage = stage.getX();
            y_stage = stage.getY();
        });
    }
}
