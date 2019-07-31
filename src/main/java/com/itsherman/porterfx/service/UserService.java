package com.itsherman.porterfx.service;

import com.itsherman.porterfx.dao.entity.User;

/**
 * @author yumiaoxia 创建时间：2019/7/31
 * 审核人： 未审核    审核日期: /
 */
public interface UserService {

    User getByAccount(String account);


    User createUser(User user);
}
