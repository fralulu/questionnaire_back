package com.infore.service;

import java.util.List;

import com.infore.model.UserInfo;
import com.infore.model.dto.UserInfoDto;

/**
 * @desc .
 * @author Created by Cai.xu
 * @date 2017/6/30-上午9:44
 */
public interface UserInfoService extends BaseService<UserInfoDto> {

    /**
     * 检查登录信息
     * @param loginName
     * @param pwd
     * @return
     */
	public UserInfo checkUser(String loginName, String pwd);
    
    /**
     * 查询所有的人员信息
     * @return List<UserInfo>
     */
	public List<UserInfo> selectAllUsers();
    
    /**
     * 查询通讯录中的展示信息
     * @return List<UserInfo>
     */
    List<UserInfo> selectContacts();
    
    
    /**
     * 新增人员前先检测用户名是否可用
     * @return UserInfo
     */
    public  UserInfo selectByUserNo(String user_no);
    
    /**
     * 根据用户信息id修改密码
     * @param id
     * @param password
     * @return
     */
    public int modifyPwdById(UserInfoDto userInfoDto);
    
    /**
     * 根据用户信息id查询用户的所有信息
     * @param id
     * @return UserInfoDto
     */
    public UserInfoDto selectById(int id);
}
