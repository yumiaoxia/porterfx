package com.itsherman.porterfx.pool;

import com.itsherman.porterfx.controller.IndexController;
import com.itsherman.porterfx.domain.DownloadFile;
import com.itsherman.porterfx.domain.DownloadItem;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yumiaoxia 创建时间：2019/8/3
 * 审核人： 未审核    审核日期: /
 */
@Data
public class DownLoadObserver implements Observer {

    private static final Logger log = LoggerFactory.getLogger(DownLoadObserver.class);

    private IndexController indexController;

    private Map<String, DownloadItem> downloadItemMap = new HashMap<>();


    @Override
    public void update(DownloadFile downloadFile) {
        indexController.getDownloadData()
                .add(new DownloadItem(downloadFile.getSnCode(), downloadFile.getFileName(), downloadFile.getDisplaySize(), downloadFile.getDownStatus()));
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }
}
