package com.infore.common.thread;

import com.infore.common.Properties;
import com.infore.common.constant.Const;
import com.infore.common.reqVO.PushStreamVO;
//import com.infore.common.rspDTO.PushStreamIpcDTO;
import com.infore.common.util.CodeEncrypt;
//import com.infore.model.VideoServer;
//import com.infore.service.VideoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 查询需要推流的ipc,调用流媒体服务器
 * Created by xuyao on 2017/12/2.
 */
@Component
public class VideoAutoPushRunnable implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(VideoAutoPushRunnable.class);

    @Autowired
    private Properties properties;

//    @Autowired
//    private VideoService videoService;

    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadPoolExecutor.CallerRunsPolicy());
    private static ExecutorService threadExecutors = new ThreadPoolExecutor(2, 3, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(5),
        new ThreadPoolExecutor.CallerRunsPolicy());

    @PostConstruct
    public void initExecutor() {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(this, 60, 60, TimeUnit.SECONDS);
        log.info("video auto push thread start");
    }

    @PreDestroy
    public void destroy() {
        scheduledThreadPoolExecutor.shutdown();
    }

    @Override
    public void run() {
//        List<PushStreamIpcDTO> pushStreamIpcDTOS = videoService.queryPushVideoIpcList();
//        log.info("video auto push pushStreamIpc size:{}", pushStreamIpcDTOS.size());
//        for (PushStreamIpcDTO dto: pushStreamIpcDTOS
//        ) {
//            log.info("video auto push thread running streamerMap:{}",VideoStreamZKWatchProcess.streamerInfoMap);
//            String url = properties.STREAM_DEFAULTURL;
//            if (VideoStreamZKWatchProcess.streamerInfoMap.size() >= 1) {
//                String regId= getMinLoadValueKey(VideoStreamZKWatchProcess.streamerInfoMap);
//                VideoServer videoServer=videoService.getVideoServerByRegid(regId);
//                url = videoServer != null ? videoServer.getServiceUrl() : properties.STREAM_DEFAULTURL;
//            }
//
//            PushStreamVO pushStreamVO = new PushStreamVO();
//            pushStreamVO.setCmd(Const.VIDEO_CMD_STREAM);
//            pushStreamVO.setIpc_id(dto.getId().toString());
//            pushStreamVO.setRtsp_url(dto.getRtspUrl());
//            pushStreamVO.setTasks(CodeEncrypt.encryptBASE64(dto.getTask()));
//            pushStreamVO.setSign(getMD5(pushStreamVO));
//            threadExecutors.execute(new PushStreamRequestRunnable(url, pushStreamVO));
//        }


    }

    private String getMD5(PushStreamVO pushStreamVO) {
        return CodeEncrypt.encryptMD5(pushStreamVO.getCmd()+pushStreamVO.getIpc_id()+pushStreamVO.getRtsp_url()+properties.STREAM_SRVKEY);
    }

    /**对于负载streamMap，取最小负载值对于的key
     * @param streamMap
     * @return
     */
    private static String getMinLoadValueKey(Map<String, Integer> streamMap) {
        AtomicReference<String> key = new AtomicReference();
        List<Integer> integers = streamMap.values().stream().sorted().collect(Collectors.toList());
        int min = integers.get(0);
        streamMap.forEach((k, v) -> {
            if (v.intValue() == min) {
                key.set(k);
            }
        });
        return key.get();
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap(){{
//            put("192.168.31.112:8080",32);
//            put("192.168.31.151:8080",25);
//            put("192.168.31.111:8080",10);
//            put("192.168.31.115:8080",5);
        }};
        String key = getMinLoadValueKey(map);
        System.out.println("---"+key);
    }

}
