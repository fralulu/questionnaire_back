package com.infore.common.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by xuyao on 2017/7/7.
 */
public class ServerRuntimeException extends NestedRuntimeException {

    public ServerRuntimeException(String code, String msg) {
        super(code+":"+msg);
    }

    public ServerRuntimeException(String msg) {
        super(msg);
    }
}
