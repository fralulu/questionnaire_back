package com.infore.test;

import com.infore.common.util.HttpClientUtil;

/**
 * Created by xuyao on 2017/12/8.
 */
public class VideoHttpTest {

    public static void main(String[] args) {
        String url = "http://192.168.31.131:8080/api/v1/servers";
        String json = "{\n"
            + "    \"cmd\": \"stream\",\n"
            + "    \"ipc_id\": \"101\",\n"
            + "    \"rtsp_url\": \"rtsp://192.168.31.210/h264/main/av_stream\",\n"
            + "    \"tasks\": [\n"
            + "    {\"type\": \"record\",\n"
            + "            \"tm\": [\n"
            + "                {\n"
            + "                    \"beg\": \"14:00\",\n"
            + "                    \"end\": \"16:00\"\n"
            + "                }\n"
            + "            ]\n"
            + "        },\n"
            + "        {\n"
            + "            \"type\": \"patrol\",\n"
            + "            \"tm\": [\n"
            + "                {\n"
            + "                    \"beg\": \"14:00\",\n"
            + "                    \"end\": \"16:00\"\n"
            + "                }\n"
            + "            ]\n"
            + "        }\n"
            + "    ],\n"
            + "    \"sign\": \"11DDBAF3386AEA1F2974EEE984542152\"\n"
            + "}";

        String ret1 = HttpClientUtil.doPost(url, json);
        System.out.println("==ret1=="+ret1);

    }

}
