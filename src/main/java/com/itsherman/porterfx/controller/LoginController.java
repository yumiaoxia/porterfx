package com.itsherman.porterfx.controller;

import com.itsherman.porterfx.PorterfxApplication;
import com.itsherman.porterfx.applicationService.LoginApplicationService;
import com.itsherman.porterfx.utils.BehaviourUtils;
import com.itsherman.porterfx.view.IndexView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p> </p>
 *
 * @author 俞淼霞
 * @since 2019-07-30
 */
@FXMLController
public class LoginController implements Initializable {

    private ResourceBundle bundle;

    private URL location;

    @Autowired
    private IndexView indexView;

    @Autowired
    private LoginApplicationService loginApplicationService;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label loginTitle;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameTip;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordTip;

    @FXML
    private Label registerLink;

    @FXML
    private Label forgetPwdLink;

    @FXML
    private Button loginBtn;

    @Autowired
    private IndexController indexController;

    private boolean canLogin;
    private boolean validUsername = false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bundle = resources;
        this.location = location;

        Image usernameImage = new Image("view/icon/login_user.png");
        ImageView userNameImageView = new ImageView(usernameImage);
        usernameLabel.setText(null);
        usernameLabel.setGraphic(userNameImageView);


        Image pwdImage = new Image("view/icon/login_password.png");
        ImageView pwdImageView = new ImageView(pwdImage);
        passwordLabel.setText(null);
        passwordLabel.setContentDisplay(ContentDisplay.RIGHT);
        passwordLabel.setGraphic(pwdImageView);

        usernameField.setPromptText("请输入用户名");
        passwordField.setPromptText("请输入密码");


        registerLink.setUnderline(true);
        forgetPwdLink.setUnderline(true);

        canLogin = false;
        loginBtn.setDisable(!canLogin);
    }

    @FXML
    private void clearTips() {
        usernameTip.setText(null);
        usernameTip.setGraphic(null);
    }

    @FXML
    private void checkName() {
        String username = usernameField.getText().trim();
        passwordTip.setText(null);
        passwordTip.setGraphic(null);
        if (StringUtils.isEmpty(username)) {
            usernameTip.setText("Tips: 用户名不能为空");
        } else {
            boolean flag = loginApplicationService.existUser(username);
            if (flag) {
                validUsername = true;
                loginBtn.setDisable(false);
                BehaviourUtils.venderIcon(usernameTip, "view/icon/ok_16.png");

            } else {
                BehaviourUtils.venderIcon(usernameTip, "view/icon/error_16.png");
            }
        }
    }


    @FXML
    private void forgetPwd() {

    }

    @FXML
    private void doLogin() {
        String password = passwordField.getText().trim();
        String username = usernameField.getText().trim();
        if (!validUsername || !StringUtils.isEmpty(password)) {
            boolean result = loginApplicationService.login(username, password);
            if (!result) {
                BehaviourUtils.venderIcon(passwordTip, "view/icon/error_16.png");
                BehaviourUtils.alert(Alert.AlertType.ERROR, "登录失败提示", "登录失败", "用户名和密码不一致");
            } else {
                BehaviourUtils.venderIcon(passwordTip, "view/icon/ok_16.png");
                Stage stage = PorterfxApplication.getStage();
                stage.setResizable(true);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                stage.setWidth(900D);
                stage.setHeight(600D);
                stage.setX((screenSize.getWidth() - stage.getWidth()) / 4);
                stage.setY((screenSize.getHeight() - stage.getHeight()) / 8);
                PorterfxApplication.showView(IndexView.class);
                indexController.initialize(location, bundle);
            }
        } else {
            if (validUsername) {
                if (StringUtils.isEmpty(username)) {
                    usernameTip.setText("Tips: 用户名不能为空");
                }
                BehaviourUtils.alert(Alert.AlertType.ERROR, "登录失败提示", "登录失败", "请输入正确的用户名");
            } else {
                passwordTip.setTextFill(Color.RED);
                passwordTip.setText("密码不能为空");
            }
        }
        loginBtn.setDisable(true);
    }


}
