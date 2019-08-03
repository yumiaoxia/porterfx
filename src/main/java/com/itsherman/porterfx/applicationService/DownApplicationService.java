package com.itsherman.porterfx.applicationService;

import com.itsherman.porterfx.domain.DownloadItem;
import com.itsherman.porterfx.service.MailService;
import com.itsherman.porterfx.utils.MailUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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
    private MailService mailService;

    public void getDownLoadPage(Pageable pageable) {
        try {
            Message[] messages = mailService.receive();
            if (messages != null) {
                for (Message message : messages) {
                    String subject = message.getSubject();
                    Object content = message.getContent();
                    if (content instanceof Multipart) {
                        List<Part> parts = new ArrayList<>();
                        MailUtils.collectFilePart(parts, (Multipart) content);
                        ObservableList<DownloadItem> downloadData = FXCollections.observableArrayList();
                        Integer unitId = 1;
                        for (Part part : parts) {
                            String fileName = part.getFileName();
                            long fileSize = part.getInputStream().available();
                            BigDecimal newFileSize = BigDecimal.valueOf(fileSize).divide(BigDecimal.valueOf(1024), 2, RoundingMode.HALF_UP);
                            String displayFileSize = newFileSize.compareTo(BigDecimal.valueOf(1024)) > 0 ? newFileSize.toPlainString() + "M" : newFileSize.divide(BigDecimal.valueOf(1024), 2, RoundingMode.HALF_UP).toPlainString() + "KB";
                            DownloadItem downloadItem = new DownloadItem(unitId, fileName, displayFileSize);
                            downloadData.add(downloadItem);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
