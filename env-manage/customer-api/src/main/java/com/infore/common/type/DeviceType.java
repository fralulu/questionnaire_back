package com.infore.common.type;

import java.util.HashMap;
import java.util.Map;

public enum DeviceType {
    ANDROID, IOS;

    private static Map<String, DeviceType> values = new HashMap<>();

    static {
        for (DeviceType value : values()) {
            values.put(value.name(), value);
        }
    }

    public static DeviceType from(String type) {
        DeviceType value = values.get(type);
        if (value != null) {
            return value;
        } else {
            return null;
        }
    }

    public static DeviceType from(String type, DeviceType defaultType) {
        DeviceType value = values.get(type);
        if (value != null) {
            return value;
        } else {
            return defaultType;
        }
    }


    @Override
    public String toString() {
        return this.name();
    }
}
