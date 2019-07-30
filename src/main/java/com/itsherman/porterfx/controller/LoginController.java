package com.itsherman.porterfx.controller;

import com.itsherman.porterfx.PorterfxApplication;
import com.itsherman.porterfx.view.LoginView;
import com.sun.javaws.Main;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
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

    @Autowired
    private LoginView loginView;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label loginTitle;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label registerLink;

    @FXML
    private Label forgetPwdLink;

    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bundle = resources;

        Image usernameImage = new Image("view/icon/login_user.png");
        ImageView userNameImageView = new ImageView(usernameImage);
        usernameLabel.setText(null);
        usernameLabel.setGraphic(userNameImageView);


        Image pwdImage = new Image("view/icon/login_password.png");
        ImageView pwdImageView = new ImageView(pwdImage);
        passwordLabel.setText(null);
        passwordLabel.setContentDisplay(ContentDisplay.RIGHT);
        passwordLabel.setGraphic(pwdImageView);

    }



}
