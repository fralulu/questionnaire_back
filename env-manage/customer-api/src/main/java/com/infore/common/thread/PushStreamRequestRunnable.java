package com.infore.common.thread;

import com.alibaba.fastjson.JSONObject;
import com.infore.common.reqVO.PushStreamVO;
import com.infore.common.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xuyao on 2017/12/8.
 */
public class PushStreamRequestRunnable implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(PushStreamRequestRunnable.class);

    private String url;
    private PushStreamVO pushStreamVO;

    public PushStreamRequestRunnable(String url, PushStreamVO pushStreamVO) {
        this.url = url;
        this.pushStreamVO = pushStreamVO;
    }
    @Override
    public void run() {
        log.info("Thread execute push stream url:{}, ipc id:{} ",url,pushStreamVO.getIpc_id());
        String json = JSONObject.toJSONString(pushStreamVO);
        String ret = HttpClientUtil.doPost(url, json);
        log.info("Thread execute ipcID:{} ,response result:{}",pushStreamVO.getIpc_id(), ret);
    }
}
