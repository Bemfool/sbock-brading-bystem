package bgroup.stocktradingsystem.stsadmin;

import bgroup.stocktradingsystem.stsadmin.view.AdminLoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StsAdminApplication extends AbstractJavaFxApplicationSupport {


    public static void main(String[] args) {
        launch(StsAdminApplication.class, AdminLoginView.class, args);
    }


}
