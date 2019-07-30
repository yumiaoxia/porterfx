package com.itsherman.porterfx;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yumiaoxia
 */
@SpringBootApplication
public class PorterfxApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(PorterfxApplication.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }
}
