package com.infore.test;

import com.infore.model.TestInfo;
import com.infore.service.impl.TestInfoServiceImpl;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xuyao on 2017/6/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(Application.class)
//@WebIntegrationTest(randomPort = true)
@SpringBootTest
public class TestInfoExam {

    @Resource
    private TestInfoServiceImpl testInfoServiceImpl;

    @Test
    public void testInsert(){
        System.out.println("----ret----");
        TestInfo testInfo = new TestInfo();
        testInfo.setUsername("test111");
        testInfo.setQq("33333");
        testInfo.setPassword("4433fff");
        testInfoServiceImpl.insertInfo(testInfo);

    }
}
