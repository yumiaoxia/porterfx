package com.itsherman.porterfx.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * <p> </p>
 *
 * @author 俞淼霞
 * @since 2019-08-03
 */
public class MailUtils {

    public static Message[] sortByReceiveDate(Message[] messages) {
        Arrays.sort(messages, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                try {
                    if (o1.getReceivedDate().before(o2.getReceivedDate())) {
                        return -1;
                    } else {
                        return 1;
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        return messages;
    }


}
