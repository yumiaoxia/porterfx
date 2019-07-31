package com.itsherman.porterfx.applicationService;

import com.itsherman.porterfx.dao.entity.User;
import com.itsherman.porterfx.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author yumiaoxia 创建时间：2019/7/31
 * 审核人： 未审核    审核日期: /
 */
@Service
public class LoginApplicationService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private User currentUser;


    public LoginApplicationService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean existUser(String account) {
        boolean flag = false;
        User user = userService.getByAccount(account);
        if (user != null) {
            this.currentUser = user;
            flag = true;
        }
        return flag;
    }


    public boolean login(String account, String password) {
        if (currentUser != null) {
            if (currentUser.getAccount().equals(account) && passwordEncoder.matches(password, currentUser.getPassword())) {
                return true;
            }
            return false;
        } else {
            existUser(account);
            return login(account, password);
        }
    }
}
