package com.infore.test;

import com.infore.mapper.UserMapper;
import com.infore.model.User;
import com.infore.service.UserService;
import com.infore.service.impl.UserServiceImpl;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xuyao on 2017/9/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTestService {

    //  @InjectMocks
    @Autowired
    private UserService userService;

    //  @Mock
    private UserMapper userMapper;

    //  @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() {
        Executor executor = new ThreadPoolExecutor(1, 2, 3, TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>());
        User user = new User();
        user.setUserName("test");
        user.setPassword("123");
        userService.add(user);

    }

}
