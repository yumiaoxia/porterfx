package com.itsherman.porterfx.controller;

import com.itsherman.porterfx.domain.DownloadItem;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        fileNameColumn.setResizable(true);
        fileSizeColumn.setCellValueFactory(cellData -> cellData.getValue().fileSizePropertyProperty());
        fileSizeColumn.setResizable(true);
        operationColumn.setResizable(true);
        operationColumn.setCellValueFactory((cellData) -> {
            return new ObservableValue<Button>() {
                @Override
                public void addListener(ChangeListener<? super Button> listener) {
                }

                @Override
                public void removeListener(ChangeListener<? super Button> listener) {
                }

                @Override
                public Button getValue() {
                    Button button = new Button();
                    button.setText("下 载");
                    return button;
                }

                @Override
                public void addListener(InvalidationListener listener) {
                }

                @Override
                public void removeListener(InvalidationListener listener) {
                }
            };
        });

        this.downloadTable.setItems(indexController.getDownloadData());
        downloadTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedDownloadItem = newValue;
        });
    }
}
