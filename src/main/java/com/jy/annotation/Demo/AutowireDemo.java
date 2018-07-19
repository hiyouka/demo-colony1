package com.jy.annotation.Demo;

import com.jy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author jianglei
 * @create 2018/6/7
 * @since 1.0.0
 */
@Component
public class AutowireDemo {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}