package com.itsherman.porterfx.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author yumiaoxia 创建时间：2019/8/1
 * 审核人： 未审核    审核日期: /
 */
@FXMLController
public class IndexController implements Initializable {

    @FXML
    private BorderPane indexRootPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu directoryMenu;

    @FXML
    private Menu mailMenu;

    @FXML
    private Menu uploadMenu;

    @FXML
    private Menu exitMenu;

    @FXML
    private AnchorPane downloadContent;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
