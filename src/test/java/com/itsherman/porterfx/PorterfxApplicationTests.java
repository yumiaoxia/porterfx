package com.itsherman.porterfx;

import com.itsherman.porterfx.dao.entity.User;
import com.itsherman.porterfx.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PorterfxApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setAccount("admin");
        user.setPassword(passwordEncoder.encode("123456"));
        user = userService.createUser(user);
        System.out.println(user);
    }

    @Test
    public void testEncode() {

        String pass1 = passwordEncoder.encode("123456");
        String pass2 = passwordEncoder.encode("123456");
        boolean mach1 = passwordEncoder.matches("123457", pass1);
        boolean mach2 = passwordEncoder.matches("123456", pass2);
        boolean upg = passwordEncoder.upgradeEncoding(pass1);
        System.out.println(pass1);
        System.out.println(pass2);
        System.out.println(pass1.equals(pass2));
        System.out.println(mach1);
        System.out.println(mach2);
        System.out.println(upg);
    }

    @Test
    public void testSystem() {
        Properties properties = System.getProperties();
        Set<String> strings = System.getenv().keySet();
        for (String string : strings) {
            System.out.println(string);
        }

    }

}
