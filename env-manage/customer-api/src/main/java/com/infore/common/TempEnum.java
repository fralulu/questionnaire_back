package com.infore.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingguanghui on 2017/5/26.
 */
public class TempEnum {
   public enum TaskStatus {
        PENDING, PAID;

       private static Map<String, TaskStatus> values = new HashMap<>();

       static {
           for (TaskStatus value : values()) {
               values.put(value.name(), value);
           }
       }

       public static TaskStatus from(String strValue) {
           TaskStatus value = values.get(strValue);
           if (value != null) {
               return value;
           } else {
               return null;
           }
       }
    }

    public enum Area{
        AREA1, AREA2;

        private static Map<String, Area> values = new HashMap<>();

        static {
            for (Area value : values()) {
                values.put(value.name(), value);
            }
        }

        public static Area from(String strValue) {
            Area value = values.get(strValue);
            if (value != null) {
                return value;
            } else {
                return null;
            }
        }
    }

    public enum IsDefault{
        YES, NO;

        private static Map<String, IsDefault> values = new HashMap<>();

        static {
            for (IsDefault value : values()) {
                values.put(value.name(), value);
            }
        }

        public static IsDefault from(String strValue) {
            IsDefault value = values.get(strValue);
            if (value != null) {
                return value;
            } else {
                return null;
            }
        }
    }
}
