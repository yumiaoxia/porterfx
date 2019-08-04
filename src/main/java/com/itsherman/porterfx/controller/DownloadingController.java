package com.itsherman.porterfx.controller;

import com.itsherman.porterfx.domain.DownLoadingItem;
import com.itsherman.porterfx.domain.DownloadFile;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author yumiaoxia 创建时间：2019/8/4
 * 审核人： 未审核    审核日期: /
 */
@FXMLController
public class DownloadingController implements Initializable {

    @FXML
    private TableView<DownLoadingItem> downloadingTable;

    @FXML
    private TableColumn<DownLoadingItem, String> fileNameColumn;

    @FXML
    private TableColumn<DownLoadingItem, HBox> progressColumn;

    @FXML
    private TableColumn<DownLoadingItem, Button> operationColumn;

    @Autowired
    private IndexController indexController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initVender();
    }


    private void initVender() {
        this.fileNameColumn.setCellValueFactory(cellData -> cellData.getValue().fileNamePropertyProperty());
        this.progressColumn.setCellValueFactory(cellData -> {
            return new ObservableValueBase<HBox>() {
                private DownLoadingItem.Progress progress = cellData.getValue().getProgressProperty();

                @Override
                public HBox getValue() {
                    ProgressBar progressBar = new ProgressBar();
                    progressBar.setProgress(100D);

                    Label sizeLabel = new Label(progress.getDownloadingSize());
                    sizeLabel.setTextAlignment(TextAlignment.CENTER);

                    Label rateLabel = new Label(progress.getDownloadingRate());
                    rateLabel.setTextAlignment(TextAlignment.CENTER);

                    HBox progressBox = new HBox();
                    progressBox.getChildren().add(progressBar);
                    progressBox.getChildren().add(sizeLabel);
                    progressBox.getChildren().add(rateLabel);
                    return progressBox;
                }
            };
        });
        operationColumn.setCellValueFactory(cellData -> {
            return new ObservableValueBase<Button>() {
                private DownloadFile.DownStatus status = cellData.getValue().getDownStatusProperty();

                @Override
                public Button getValue() {
                    Button button = new Button();
                    switch (status) {
                        case DOWNLOADING:
                            button.setText("暂    停");
                            break;
                        case DOWNLOAD_PAUSE:
                            button.setText("继续下载");
                            break;
                        case DOWNLOAD_FINISHED:
                            button.setText("下载完成");
                            break;
                        default:
                            button.setDisable(true);

                    }
                    button.setDefaultButton(true);
                    button.setOnMouseClicked(event -> {

                    });
                    return button;
                }
            };
        });
    }

    @Scheduled(cron = "*/1 * * * * ?")
    public void initItems() {
        ObservableList<DownLoadingItem> downLoadingItems = FXCollections.observableArrayList();
        downLoadingItems.addAll(indexController.getDownLoadingData().values());
        this.downloadingTable.setItems(downLoadingItems);
    }
}
