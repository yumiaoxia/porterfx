package com.itsherman.porterfx.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author yumiaoxia 创建时间：2019/7/31
 * 审核人： 未审核    审核日期: /
 */
public class BehaviourUtils {

    public static void alert(Alert.AlertType alertType, String title, String header, String conctent) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(conctent);
        alert.showAndWait();
    }

    public static void venderIcon(Labeled labeled, String iconPath) {
        labeled.setText(null);
        Image image = new Image(iconPath);
        ImageView imageView = new ImageView(image);
        labeled.setGraphic(imageView);
    }
}
