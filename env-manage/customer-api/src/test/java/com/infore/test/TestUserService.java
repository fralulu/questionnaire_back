package com.infore.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.infore.mapper.UserInfoMapper;
import com.infore.model.UserInfo;
import com.infore.service.UserInfoService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xuyao on 2017/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testCheck (){
        UserInfo userInfo=userInfoService.checkUser("admin", "123");
        System.out.println("----userInfo---"+userInfo.toString());
    }

    @Test
    public void testQuery() {
        PageHelper.startPage(2,5);
        UserInfo param = new UserInfo();
//        param.setPassword("12%");
        param.setUserName("李");
        List<UserInfo> userInfos = userInfoMapper.queryByParam(param);
        System.out.println("------"+userInfos);
        PageInfo pageInfo=new PageInfo(userInfos);
        System.out.println("==="+pageInfo);
    }
}


