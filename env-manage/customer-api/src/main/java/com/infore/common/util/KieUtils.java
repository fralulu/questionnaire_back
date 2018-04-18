package com.infore.common.util;

import org.kie.api.runtime.KieContainer;

/**
 * Created by xuyao on 2017/9/23.
 */
public class KieUtils {

    private static KieContainer kieContainer;

    public static KieContainer getKieContainer() {
        return kieContainer;
    }

    public static void setKieContainer(KieContainer kieContainer) {
        KieUtils.kieContainer = kieContainer;
    }

}
