package com.infore.service.impl;

import com.infore.mapper.BaseMapper;
import com.infore.mapper.UserInfoMapper;
import com.infore.model.UserInfo;
import com.infore.model.dto.UserInfoDto;
import com.infore.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by Cai.xu
 * @desc .
 * @date 2017/6/30-上午9:45
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoDto> implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    protected BaseMapper getMapper() {
        return userInfoMapper;
    }


    @Override
    public UserInfo checkUser(String loginName, String pwd) {
        return  userInfoMapper.getUser(loginName, pwd);
    }

    
    /**
     * 查询通讯录中的展示信息
     * @return List<UserInfo>
     */
	@Override
	public List<UserInfo> selectContacts() {
		List<UserInfo> userList = userInfoMapper.selectContacts();
		//姓名拼音的首位字符是字母的对象集合
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		//姓名拼音的首位字符非字母的对象集合
		List<UserInfo> notAlphabetList = new ArrayList<UserInfo>();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String group = "";
		UserInfo user;
		boolean bool = true;
		
		for (UserInfo ui : userList) {
			//获取姓名拼音
			String spellName = ui.getSpellName();
			//获取姓名拼音的首位字符
			String startWith = spellName.substring(0,1).toUpperCase();
			
			//如果姓名拼音的首位字符是字母
			if(alphabet.contains(startWith)) {
				if(!group.equals(startWith)) {
					group = startWith;
					user = new UserInfo();
					user.setSpellName(startWith);
					userInfoList.add(user);
				}
				userInfoList.add(ui);
			}else {
				//当第一次检测到姓名拼音的首位字符不是字母添加添加#号作为分隔符
				if(bool) {
					user = new UserInfo();
					user.setSpellName("#");
					notAlphabetList.add(user);
					bool = false;
				}
				notAlphabetList.add(ui);
			}
		}
		//将姓名拼音的首位字符是字母和不是字母的进行合并
		userInfoList.addAll(notAlphabetList);
		return userInfoList;
	}


	@Override
	public UserInfo selectByUserNo(String user_no) {
		return userInfoMapper.selectByUserNo(user_no);
	}

	@Override
	public List<UserInfo> selectAllUsers() {
		return userInfoMapper.selectAllUsers();
	}


	@Override
	public int modifyPwdById(UserInfoDto userInfoDto) {
		return userInfoMapper.modifyPwdById(userInfoDto);
	}


	@Override
	public UserInfoDto selectById(int id) {
		return userInfoMapper.selectById(id);
	}
}
