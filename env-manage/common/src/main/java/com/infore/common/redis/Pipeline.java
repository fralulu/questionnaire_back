package com.infore.common.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pipeline {
    private final List<PipelineMethod> methods;
    private final List<List<Object>> params;
    private int size;

    private Pipeline() {
        this.methods = new ArrayList<>();
        this.params = new ArrayList<>();
        this.size = 0;
    }

    public static Pipeline build() {
        return new Pipeline();
    }

    public Pipeline append(PipelineMethod method, Object... params) {
        if (method == null) {
            throw new NullPointerException("Method is null.");
        }

        if (params == null || params.length <= 0) {
            throw new NullPointerException("Params is null or empty.");
        }

        return append(method, Stream.of(params).collect(Collectors.toList()));
    }

    public Pipeline append(PipelineMethod method, List<Object> params) {
        if (method == null) {
            throw new NullPointerException("Method is null.");
        }

        if (params == null || params.size() <= 0) {
            throw new NullPointerException("Params is null or empty.");
        }

        params = filterParam(params);

        /* Check required params. */
        List<Class<?>> types = method.getParamTypes();
        int size = types.size();
        int len = params.size();
        if (len < size) {
            throw new IllegalArgumentException("Size of params is not match.");
        }

        for (int i = 0; i < size; i++) {
            if (params.get(i) == null || params.get(i).getClass() != types.get(i)) {
                throw new IllegalArgumentException("Param[" + i + "] is not match: " + params.get(i) + ".");
            }
        }

        /* Check optional params. */
        switch (method) {
            case HDEL:
            case HMGET:
            case DEL:
            case EXISTS:
            case LPUSH:
            case RPUSH:
            case SADD:
            case SREM:
            case ZREM:
                for (int i = size; i < len; i++) {
                    if (params.get(i) == null || !(params.get(i) instanceof String)) {
                        throw new IllegalArgumentException("Param[" + i + "] is not match: " + params.get(i) + ".");
                    }
                }
                break;
            case HMSET:
                if (len == 2) {
                    if (!(params.get(1) instanceof Map)) {
                        throw new IllegalArgumentException("Size of params is not match.");
                    }
                } else {
                    if (len % 2 != 1) {
                        throw new IllegalArgumentException("Size of params is not match.");
                    }

                    for (int i = size; i < len; i++) {
                        if (params.get(i) == null || !(params.get(i) instanceof String)) {
                            throw new IllegalArgumentException("Param[" + i + "] is not match: " + params.get(i) + ".");
                        }
                    }
                }
                break;
            default:
                break;
        }

        this.methods.add(method);
        this.params.add(params);
        this.size++;
        return this;
    }

    int size() {
        return this.size;
    }

    PipelineMethod getPipelineMethod(int pos) {
        if (pos < 0 || pos >= this.size) {
            throw new ArrayIndexOutOfBoundsException(pos);
        }

        return this.methods.get(pos);
    }

    List<Object> getPipelineParams(int pos) {
        if (pos < 0 || pos >= this.size) {
            throw new ArrayIndexOutOfBoundsException(pos);
        }

        return this.params.get(pos);
    }

    @SuppressWarnings("unchecked")
    private List<Object> filterParam(List<Object> params) {
        List<Object> trueParam = new ArrayList<>();
        for (Object param : params) {
            if (param instanceof List) {
                trueParam.addAll(filterParam((List<Object>) param));
            } else if (param instanceof Object[]) {
                for (Object p : (Object[]) param) {
                    trueParam.addAll(filterParam(Stream.of(p).collect(Collectors.toList())));
                }
            } else {
                trueParam.add(param);
            }
        }

        return trueParam;
    }

}
