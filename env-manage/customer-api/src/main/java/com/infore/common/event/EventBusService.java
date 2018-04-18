package com.infore.common.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by xuyao on 13/12/2017.
 */
@Service
public class EventBusService implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(EventBusService.class);

    private EventBus eventBus;

    @Autowired
    private ApplicationContext appContext;

    public void unRegister(Object eventListener){
        eventBus.unregister(eventListener);
    }

    public void postEvent(GuavaEvent event){
        log.info("post event:{}",event.toString());
        eventBus.post(event);
    }

    public void register(Object eventListener){
        eventBus.register(eventListener);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        eventBus = new AsyncEventBus("guava-event-bus", new ThreadPoolExecutor(3, 5, 1,
            TimeUnit.MINUTES,new LinkedBlockingQueue<>(5),new CallerRunsPolicy()));
        appContext.getBeansWithAnnotation(EventSubscribe.class).forEach((name, bean) -> {
            log.info("event subscribe bean name:{}", name);
            eventBus.register(bean);
        });
    }
}
