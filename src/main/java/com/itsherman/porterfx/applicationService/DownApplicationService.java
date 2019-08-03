package com.itsherman.porterfx.applicationService;

import com.itsherman.porterfx.domain.DownloadItem;
import com.itsherman.porterfx.pool.DownLoadPool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p> </p>
 *
 * @author 俞淼霞
 * @since 2019-08-03
 */
@Service
public class DownApplicationService {

    private static final Logger log = LoggerFactory.getLogger(DownApplicationService.class);

    @Autowired
    private DownLoadPool downLoadPool;

    public ObservableList<DownloadItem> getDownLoadPage(Pageable pageable) {
        ObservableList<DownloadItem> downloadItems = FXCollections.observableArrayList();
        Map<String, DownloadItem> downloadItemMap = downLoadPool.getDownloadItemMap();
        int pageSize = pageable.getPageSize();
        int pageNo = pageable.getPageNumber();
        pageNo = pageNo == 0 ? pageNo = 1 : pageNo;
        int start = 0;
        for (Map.Entry<String, DownloadItem> entry : downloadItemMap.entrySet()) {
            if (start > (pageNo - 1) * pageSize && start <= pageNo * pageSize) {
                downloadItems.add(entry.getValue());
            }
            start++;
        }
        return downloadItems;
    }
}
