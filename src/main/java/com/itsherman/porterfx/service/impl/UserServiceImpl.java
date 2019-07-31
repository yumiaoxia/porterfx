package com.itsherman.porterfx.service.impl;

import com.itsherman.porterfx.dao.entity.User;
import com.itsherman.porterfx.dao.repository.UserRepository;
import com.itsherman.porterfx.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author yumiaoxia 创建时间：2019/7/31
 * 审核人： 未审核    审核日期: /
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getByAccount(String account) {
        User user = new User();
        user.setAccount(account);
        return userRepository.findByAccount(account);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
