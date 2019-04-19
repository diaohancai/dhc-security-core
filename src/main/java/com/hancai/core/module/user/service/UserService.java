package com.hancai.core.module.user.service;

import com.hancai.core.module.user.dto.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Service -- @Service 需要事务支撑
public class UserService {

    /**
     * 用户内存表
     */
    private List<User> users = new ArrayList<>();

    public UserService() {
        // 初始化内存表
        users.add(new User("1", "diaohancai", "$2a$10$hlmqsb1OCKT1I0osevlw9ui9c1KbT5wMdhH8EcY8o4hcPD1ZwQKKe", "admin", new Date())); // 222
        users.add(new User("2", "zhagnqiao", "$2a$10$DiqaHBJG4eAlZGmmxUmuH.KwCBQcrw139b60JupSeksqxu7IYhZeO", "admin", new Date())); // 222
    }

    public List<User> getUsersAll() {
        return users;
    }

    public User getUser(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public User create(User user) {
        if (StringUtils.isBlank(user.getId())) {
            user.setId("3");
        }
        users.add(user);
        return user;
    }

    public User changePassword(String id, String password) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setPassword(password);
                return user;
            }
        }
        return null;
    }

    public void delete(String id) {
        System.out.println("-- delete user " + id);
    }
}
