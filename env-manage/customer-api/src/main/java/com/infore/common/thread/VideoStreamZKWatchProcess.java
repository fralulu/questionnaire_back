package com.infore.common.thread;

import com.alibaba.fastjson.JSONObject;
import com.infore.common.Properties;
import com.infore.common.event.EventBusService;
import com.infore.common.event.StreamServerChangeEvent;
//import gbap.zkclient.ZKClient;
//import gbap.zkclient.ZKClientBuilder;
//import gbap.zkclient.listener.ZKChildDataListener;
//import gbap.zkclient.serializer.BytesSerializer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xuyao on 2017/12/12.
 */
@Component
public class VideoStreamZKWatchProcess implements InitializingBean
{
    private static final Logger log = LoggerFactory.getLogger(VideoStreamZKWatchProcess.class);

    @Autowired
    private EventBusService eventBusService;

    @Autowired
    private Properties properties;
    //todo 后续可用redis
    public static Map<String, Integer> streamerInfoMap = new ConcurrentHashMap<String, Integer>();

    @Override
    public void afterPropertiesSet() throws Exception {
//        String address =properties.GIOTP_ZOOKEEPER_ADDRESS;
//        ZKClient zkClient = ZKClientBuilder.newZKClient(address).sessionTimeout(3000)// 可选
//            .serializer(new BytesSerializer())// 可选
//            .eventThreadPoolSize(4)// 可选
//            .retryTimeout(1000 * 60)// 可选
//            .connectionTimeout(10000)// 可选
//            .build();
//        // 获取节点数据
//        List<String> children = zkClient.getChildren(properties.GIOTP_STREAMPATH);
//        for (String child : children) {
//            byte[] data = zkClient.getData(properties.GIOTP_STREAMPATH + "/" + child, false);
//            if (data != null && data.length != 0) {
//                JSONObject jsonObject = JSONObject.parseObject(new String(data));
//                int load = jsonObject.getIntValue("load");
//                streamerInfoMap.put(child, load);
//                log.info("节点:{},负载:{},streamerInfoMap:{}", child, load,streamerInfoMap);
//            }
//        }
//        // 子节点数量和子节点数据变化监听
//        zkClient.listenChildDataChanges(properties.GIOTP_STREAMPATH, new ZKChildDataListener() {
//            @Override
//            public void handleSessionExpired(String path, Object data) throws Exception {
//                // 会话过期
//                log.info("会话过期 path:{}, data:{}",path,data);
////                System.out.println("path:" + path + "   data:" + data);
//                // 在会话过期后，getData方法会阻塞直到会话重建，并且连接成功，获取数据后才会返回。
//                // 这里把返回的data与上次改变后的数据做对比如果改变了，则执行数据改变后的业务逻辑
//                // do someting
//            }
//
//            @Override
//            public void handleChildDataChanged(String path, Object data) throws Exception {
//                byte[] storeDataByte = (byte[]) data;
//                log.info("节点数据内容变化 path:{}, data:{}",path, new String(storeDataByte));
////                System.out.println("路径:" + path + " 数据:" + new String(storeDataByte));
//                // 子节点数据发生改变
//                JSONObject jsonObject = JSONObject.parseObject(new String(storeDataByte));
//                int load = jsonObject.getIntValue("load");
//                // 获取节点名称
//                String nodeName = path.substring(properties.GIOTP_STREAMPATH.length() + 1);
//                streamerInfoMap.put(nodeName, load);
//            }
//
//            @Override
//            public void handleChildCountChanged(String path, List<String> children) throws Exception {
//                // 子节点数量发生改变
//                log.info("节点个数变化 children:{}", children);
////                System.out.println("children:" + children);
//                for (String child : children) {
//                    if (!streamerInfoMap.containsKey(child)) {
//                        // 新增节点
//                        byte[] data = zkClient.getData(properties.GIOTP_STREAMPATH + "/" + child, false);
//                        if (data != null && data.length != 0) {
//                            JSONObject jsonObject = JSONObject.parseObject(new String(data));
//                            int load = jsonObject.getIntValue("load");
//                            log.info("节点path:{}, child path:{}, 负载：{}", path, child, load);
//                            streamerInfoMap.put(child, load);
//                        }
//                    }
//                }
//
//                Set entries = streamerInfoMap.entrySet();
//                Iterator iter = entries.iterator();
//                while (iter.hasNext()) {
//                    Entry entry = (Entry) iter.next();
//                    String key = (String) entry.getKey();
//                    // 节点删除
//                    if (children.size() < entries.size() && !children.contains(key)) {
//                        log.info("streamerInfoMap 删除节点 key:{}", key);
//                        eventBusService.postEvent(new StreamServerChangeEvent(key));
//                        iter.remove();
//
//                    }
//                }
//            }
//        });
    }
}
