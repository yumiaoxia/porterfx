package com.itsherman.porterfx.pool;

import com.itsherman.porterfx.domain.DownloadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yumiaoxia 创建时间：2019/8/4
 * 审核人： 未审核    审核日期: /
 */
@Component
public class DownloadFilePool {

    @Autowired
    private DownloadSubject downloadSubject;

    private Map<String, DownloadFile> downloadFileMap = new HashMap<>();


    public void registerDownloadFile(DownloadFile downloadFile) {
        downloadFileMap.put(downloadFile.getSnCode(), downloadFile);
        downloadSubject.setDownloadFile(downloadFile);
    }

    public DownloadFile takeOne(String fileName) {
        return downloadFileMap.get(fileName);
    }
}
