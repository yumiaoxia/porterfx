package com.itsherman.porterfx.pool;

import com.itsherman.porterfx.domain.DownloadFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yumiaoxia 创建时间：2019/8/4
 * 审核人： 未审核    审核日期: /
 */
public class DownloadFileSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private DownloadFile downloadFile;


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.size() > 0) {
            observers.remove(o);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    @Override
    public void notifyObservers() throws IOException, MessagingException {
        for (Observer observer : observers) {
            observer.update(this.downloadFile);
        }
    }

    private void partChanged() throws IOException, MessagingException {
        notifyObservers();
    }


    public void setDownloadFile(DownloadFile downloadFile) throws IOException, MessagingException {
        this.downloadFile = downloadFile;
        partChanged();
    }
}
