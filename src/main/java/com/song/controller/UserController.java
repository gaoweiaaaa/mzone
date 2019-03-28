package com.song.controller;

import com.song.entity.UserVO;
import com.song.entity.User;
import com.song.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    //测试类
    @RequestMapping(value = "/index", produces = "text/html;charset=utf-8")
    public String index() {
        return "user/index";
    }

    //显示登录页面
    @RequestMapping(value = "/login", produces = "text/html;charset=utf-8")
    public String login() {
        return "user/login";
    }

    //TODO
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
    @RequestMapping(value = "/login_handler", produces = "application/json;charset=utf-8")
    @ResponseBody
    public UserVO login(User user, HttpSession session) {
        String name = user.getName();
        UserVO vo = new UserVO();
        User userRes = userService.findUserByName(name);
        if (ObjectUtils.isEmpty(userRes)) {
            vo.setResponseCode("400");
            vo.setErrorMsg("用户名找不到");
        } else {
            String password = user.getPassword();
            if (password.equals(userRes.getPassword())) {
                session.setAttribute("username", name);
                vo.setResponseCode("200");
            } else {
                vo.setResponseCode("400");
                vo.setErrorMsg("密码错误");
            }
        }
        return vo;
    }
    @RequestMapping(value = "/mzone", produces = "text/html;charset=utf-8")
    public String mzone() {
        return "user/mzone";
    }
    @RequestMapping(value = "/reg", produces = "text/html;charset=utf-8")
    public String reg() {
        return "user/reg";
    }
}