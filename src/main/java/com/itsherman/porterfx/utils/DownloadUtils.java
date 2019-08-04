package com.itsherman.porterfx.utils;

import java.time.Duration;
import java.time.Instant;

/**
 * @author yumiaoxia 创建时间：2019/8/5
 * 审核人： 未审核    审核日期: /
 */
public class DownloadUtils {

    public static String convertToProgressRate(Instant lastInstant, Instant newInstant, Long lastSize, long newSize) {
        String result = "0 B/s";
        if (lastInstant != null && lastSize != null && lastSize != 0L) {
            long millis = Duration.between(lastInstant, newInstant).toMillis();
            long progressSize = newSize - lastSize;
            double progressRate = progressSize * 1000d / millis;
            if (progressRate < 1024) {
                result = Math.scalb(progressRate, 1) + " B/s";
            } else {
                progressRate = progressRate / 1024;
                if (progressRate < 1024) {
                    result = Math.scalb(progressRate, 1) + " KB/s";
                } else {
                    result = Math.scalb(progressRate / 1024, 1) + " M/s";
                }
            }
        }
        return result;
    }
}
