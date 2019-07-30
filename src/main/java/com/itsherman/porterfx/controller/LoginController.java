package com.itsherman.porterfx.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.Initializable;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bundle = resources;
    }


}
