package com.itsherman.porterfx.controller;

import com.itsherman.porterfx.domain.DownloadItem;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author yumiaoxia 创建时间：2019/8/2
 * 审核人： 未审核    审核日期: /
 */
@FXMLController
public class DownloadController implements Initializable {

    @FXML
    private TableView<DownloadItem> downloadTable;

    @FXML
    private TableColumn<DownloadItem, String> fileNameColumn;

    @FXML
    private TableColumn<DownloadItem, String> fileSizeColumn;

    @FXML
    private TableColumn<DownloadItem, Button> operationColumn;

    private DownloadItem selectedDownloadItem;

    @Autowired
    private IndexController indexController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileNameColumn.setCellValueFactory(cellData -> cellData.getValue().fileNamePropertyProperty());
        fileSizeColumn.setCellValueFactory(cellData -> cellData.getValue().fileSizePropertyProperty());

        downloadTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedDownloadItem = newValue;
        });
    }
}
