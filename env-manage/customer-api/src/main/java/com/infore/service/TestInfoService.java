package com.infore.service;

import com.infore.model.TestInfo;
import java.util.List;

/**
 * Created by xuyao on 2017/6/26.
 * 例子
 */
public interface TestInfoService {

    /**
     *
     * @param testInfo
     */
    void insertInfo(TestInfo testInfo);

    /**
     * 分页查询
     */
    List<TestInfo> queryList();
}
