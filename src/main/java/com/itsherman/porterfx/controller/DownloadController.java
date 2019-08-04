package com.itsherman.porterfx.controller;

import com.itsherman.porterfx.applicationService.DownloadApplicationService;
import com.itsherman.porterfx.domain.DownloadFile;
import com.itsherman.porterfx.domain.DownloadItem;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ObservableValueBase;
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

    @Autowired
    private IndexController indexController;

    @Autowired
    private DownloadApplicationService downloadApplicationService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileNameColumn.setCellValueFactory(cellData -> cellData.getValue().fileNamePropertyProperty());
        fileNameColumn.setResizable(true);
        fileSizeColumn.setCellValueFactory(cellData -> cellData.getValue().fileSizePropertyProperty());
        fileSizeColumn.setResizable(true);
        operationColumn.setResizable(true);
        operationColumn.setCellValueFactory((cellData) -> {
            return new ObservableValueBase<Button>() {
                @Override
                public Button getValue() {
                    Button button = new Button();
                    DownloadFile.DownStatus downStatus = cellData.getValue().getDownStatusProperty();
                    switch (downStatus) {
                        case DOWNLOAD_PAUSE:
                        case DOWNLOADING:
                            button.setText("下载中");
                            break;
                        case DOWNLOAD_FINISHED:
                            button.setText("重新下载");
                            break;
                        default:
                            button.setText("下 载");
                    }

                    button.setDefaultButton(true);
                    button.setOnMouseClicked((event) -> {
                        downloadApplicationService.createDownLoadTask(cellData.getValue().getItemNO());
                    });
                    return button;
                }
            };
        });

        this.downloadTable.setItems(indexController.getDownloadData());
    }
}
