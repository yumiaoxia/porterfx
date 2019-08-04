package com.itsherman.porterfx.pool;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author yumiaoxia 创建时间：2019/8/4
 * 审核人： 未审核    审核日期: /
 */
public interface Subject {

    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers() throws IOException, MessagingException;
}