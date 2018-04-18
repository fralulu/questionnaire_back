package com.infore.service.impl;

import com.infore.mapper.TestInfoMapper;
import com.infore.model.TestInfo;
import com.infore.service.TestInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuyao on 2017/6/26.
 */
@Service
public class TestInfoServiceImpl implements TestInfoService{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestInfoMapper testInfoMapper;

    public void insertInfo(TestInfo testInfo) {
        testInfoMapper.insert(testInfo);
    }

    @Override
    public List<TestInfo> queryList() {
        log.info("查询所有,分页查询开始");
        log.info("query page end");
        return testInfoMapper.queryByParams();
    }


}
