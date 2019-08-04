package com.itsherman.porterfx.pool;

import com.itsherman.porterfx.domain.DownloadFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yumiaoxia 创建时间：2019/8/4
 * 审核人： 未审核    审核日期: /
 */
public class DownLoadingSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private DownloadFile downloadFile;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(downloadFile);
        }
    }


    private void changed() {
        notifyObservers();
    }

    public void setDownloadFile(DownloadFile downloadFile) {
        this.downloadFile = downloadFile;
        changed();
    }
}
