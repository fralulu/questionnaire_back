package com.infore.service;

import com.infore.model.User;
import java.util.List;
import java.util.Map;

/**
 * Created by xuyao on 2017/9/14.
 */
public interface UserService {

   public int add(User user);

    /**
     * 修改密码
     * @param userId
     * @param loginFlag
     * @return
     */
   public int modifyPwd(Integer userId,String loginFlag);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
   public int update(User user);

   public int del(Integer id);

   public List<User> queryList(User user);

    /**
     * @param loginName
     * @param pwd
     * @return
     */
   public User checkUser(String loginName,String pwd);

   public User getUserByParams(User user);

    /**
     * @param loginName
     * @return
     */
   public User getUserByLoginName(String loginName);

    /**
     * 禁用启用
     * @param loginFlag
     * @return
     */
   public int enableOrDisableUser(Integer userId,String loginFlag);

    /**
     * 检查用户密码是否过期
     * @param userId
     * @param validityDays
     * @return
     */
   public boolean checkPwdExpire(Integer userId, int validityDays);

   public User getUserByid(Integer uid);

   public boolean checkOldPwd(String oldPwd,Integer uid);
    
}
