package com.itsherman.porterfx;

import com.itsherman.porterfx.view.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author yumiaoxia
 */
@SpringBootApplication
public class PorterfxApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {

        launch(PorterfxApplication.class, LoginView.class, args);
    }

    @Override
    public Collection<Image> loadDefaultIcons() {
        return Arrays.asList(new Image("/view/icon/youxiang.png"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setResizable(false);
        stage.setTitle("porter");
    }
}
