package com.infore.service.impl;

import com.infore.mapper.UserMapper;
import com.infore.model.User;
import com.infore.service.UserService;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuyao on 2017/9/14.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.insertSelective(user);
    }

//    @Override
//    public int modifyPwd(User user) {
//        user.setPwdLastDate(new Date());
//        return userMapper.insertSelective(user);
//    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int del(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<User> queryList(User user) {
        return userMapper.queryUsersByParam(user);
    }

    @Override
    public User checkUser(String loginName,String pwd) {
        return userMapper.getUserByParams(loginName, new User(){{setPassword(pwd);}});
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return userMapper.getUserByParams(loginName, null);
    }

    @Override
    public User getUserByParams(User user) {
        return userMapper.getUserByParams(null, user);
    }

    @Override
    public int modifyPwd(Integer userId, String loginFlag) {
        return 0;
    }

    @Override
    public int enableOrDisableUser(Integer userId, String loginFlag) {
        return 0;
    }

    @Override
    public boolean checkPwdExpire(Integer userId, int validityDays) {
        return false;
    }

    @Override
    public User getUserByid(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public boolean checkOldPwd(String oldPwd,Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        return user.getPassword().equalsIgnoreCase(oldPwd);
    }
}
