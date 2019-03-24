package com.song.service;

import com.song.entity.User;
import com.song.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByName(String name){
        User user = null;
        try{
            user = userRepository.findByUserName(name);
        }catch (Exception e){}
        return user;
    }
    public List<User> findAll(){
     List<User>list = null;
     try{
         list = userRepository.findAll();
     }catch (Exception e){}
     return list;
    }
    public Boolean regist(User user){
        Boolean userFlag = false;
        String userName = user.getName();
        User userRes = userRepository.findByUserName(userName);
        if(ObjectUtils.isEmpty(userRes)){
            userFlag = true;
        }
        return userFlag;
    }
}
