package com.infore.mapper;

import com.infore.model.UserInfo;
import com.infore.model.dto.UserInfoDto;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper extends BaseMapper{

    /**
     * 获取用户信息
     * @param loginName
     * @param pwd
     * @return
     */
     UserInfo getUser(@Param("loginName")String loginName, @Param("pwd")String pwd);
     
     /**
      * 查询所有的人员信息
      * @return List<UserInfo>
      */
     List<UserInfo> selectAllUsers();
     
     /**
      * 查询通讯录中的展示信息
      * @return List<UserInfo>
      */
     List<UserInfo> selectContacts();
     
     /**
      * 新增人员前先检测用户名是否可用
      * @return UserInfo
      */
     UserInfo selectByUserNo(String user_no);
     
     /**
      * 根据用户信息id修改密码
      * @param id
      * @param password
      * @return
      */
     int modifyPwdById(UserInfoDto userInfoDto);
     
     /**
      * 根据用户信息id查询用户的所有信息
      * @param id
      * @return UserInfoDto
      */
     UserInfoDto selectById(int id);

    List<UserInfo> queryByParam(UserInfo userInfo);
}