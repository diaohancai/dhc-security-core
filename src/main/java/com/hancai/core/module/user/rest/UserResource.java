package com.hancai.core.module.user.rest;

import com.hancai.core.module.user.dto.User;
import com.hancai.core.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户资源 rest api
 *
 * @author diaohancai
 */
@RestController
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsersAll();
    }

    /**
     * 查询用户详细信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable String id) {
        //@PathVariable 将一个url路径占位符填充到函数参数中
        //throw new MyException("查询用户失败");
        return userService.getUser(id);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public User create(@RequestBody User user) {
        user = userService.create(user);
        return user;
    }

    /**
     * 修改用户密码
     *
     * @param id
     * @param password
     * @return
     */
    @PutMapping(value = "/{id}")
    public User changePassword(@PathVariable String id, @RequestParam String password) {
        User user = userService.changePassword(id, password);
        return user;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }

}
