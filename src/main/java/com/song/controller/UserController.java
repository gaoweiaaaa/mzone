package com.song.controller;

import com.song.Exception.StandardRuntimeException;
import com.song.entity.User;
import com.song.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index",produces = "text/html;charset=utf-8")
    public String index() {
        return "user/index";
    }
    @RequestMapping(value = "/login",produces = "text/html;charset=utf-8")
    public String login() {
        return "user/login";
    }
    @RequestMapping(value = "/show")
    @ResponseBody
    public String show(@RequestParam(value = "name", required = false) String name) {
        User user = userService.findUserByName(name);
        if (null != user) {
            return user.getId() + "/" + user.getName() + "/" + user.getPassword();
        } else return "null";
    }

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<User> findAll() {
        List<User> list = userService.findAll();
        return list;
    }

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {

        return "hello";
    }

    @RequestMapping(value = "/regist", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String regist(@RequestBody User user) {
        Boolean flag = userService.regist(user);
        if (flag) {
            return user.getName() + "123";
        } else {
            return "昵称重复";
        }

    }

    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(@RequestBody User user) {
        String strReturn = "";
        String name = user.getName();
        User userRes = userService.findUserByName(name);
        if (ObjectUtils.isEmpty(userRes)) {
            strReturn = "用户不存在";
        } else {
            String password = user.getPassword();
            if (StringUtils.isBlank(password)) {
                throw new StandardRuntimeException("密码错误");
            }
            if (password.equals(userRes.getPassword())) {
                strReturn = "欢迎登陆";
            }
        }

        return strReturn;
    }
}