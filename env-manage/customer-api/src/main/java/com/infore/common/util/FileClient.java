package com.infore.common.util;

import com.infore.common.exception.ServerRuntimeException;
//import com.ning.http.client.multipart.ByteArrayPart;
//import com.ning.http.client.multipart.FilePart;
//import gbap.core.Gbap;
//import gbap.driver.http.HttpClientDriver;
//import gbap.driver.http.HttpRequest;
//import gbap.driver.http.HttpResponseHandler;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xuyao on 2017/7/17.
 * 单例
 */
public class FileClient {

//    private static final Logger log = LoggerFactory.getLogger(FileClient.class);
//    // 定义一个私有构造方法
//    private FileClient(){}
//
//    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
//    private static volatile HttpClientDriver instance;
//
//    public static HttpClientDriver getInstance(){
//        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
//        if (instance == null) {
//            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
//            synchronized (FileClient.class) {
//                //未初始化，则初始instance变量
//                if (instance == null) {
//                    instance = new HttpClientDriver();
//                    try {
//                        instance.init();
//                        instance.start();
//                    } catch (Exception e) {
//                        log.error("bap服务启动异常",e);
//                        throw new ServerRuntimeException("bap服务启动异常,请联系管理员");
//                    }
//                }
//            }
//        }
//        return instance;
//    }
//
//    /**
//     * 异步-上传文件到简单文件服务器
//     *
//     * @param url
//     *            服务器地址
//     * @param destPath
//     *            目标文件路径，如果路径不存在会自动创建
//     * @param srcPath
//     *            本地文件路径
//     * @param handler
//     *            上传文件结果回调
//     */
//    public static void uploadFile(String url, String destPath, String srcPath, HttpResponseHandler handler)
//        throws Exception {
//        HttpClientDriver httpClientDriver = FileClient.getInstance();
//        url += destPath;
//        HttpRequest httpRequest = httpClientDriver.post(url);
//        httpRequest.addPart(new FilePart("file", new File(srcPath)));
//        httpRequest.execute(handler);
//    }
//
//    /**
//     * 异步-上传文件到简单文件服务器
//     *
//     * @param url
//     *            服务器地址
//     * @param destPath
//     *            目标文件路径，如果路径不存在会自动创建
//     * @param inputStream
//     *            要上传的文件流
//     * @param handler
//     *            上传文件结果回调
//     */
//    public static void uploadFile(String url, String destPath, InputStream inputStream, HttpResponseHandler handler) {
//        HttpClientDriver httpClientDriver = FileClient.getInstance();
//        url += destPath;
//        HttpRequest httpRequest = httpClientDriver.post(url);
//
//        try {
//            if (inputStream != null) {
//                byte[] bytes;
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                byte[] buf = new byte[4096];
//                int len = 0;
//                while ((len = inputStream.read(buf)) > 0) {
//                    baos.write(buf, 0, len);
//                }
//                bytes = baos.toByteArray();
//                httpRequest.addPart(new ByteArrayPart("file", bytes, null, null, ""));
//                httpRequest.execute(handler);
//            }
//        } catch (Exception e) {
//            log.info("sync upload input stream error:{}",e);
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    log.info("sync upload input stream close error:{}",e);
//                }
//            }
//        }
//
//    }
//
//    /**
//     * 阻塞式-上传文件到简单文件服务器
//     *
//     * @param url
//     *            服务器地址
//     * @param destPath
//     *            目标文件路径，如果路径不存在会自动创建
//     * @param inputStream
//     *            要上传的文件流
//     * @throws IOException
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//    public static int uploadFile(String url, String destPath, InputStream inputStream)
//        throws IOException, InterruptedException, ExecutionException {
//        //HttpClientDriver httpClientDriver = Gbap.getDriver(HttpClientDriver.class);
//        HttpClientDriver httpClientDriver = FileClient.getInstance();
//        url += destPath;
//        HttpRequest httpRequest = httpClientDriver.post(url);
//
//        try {
//            byte[] bytes;
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            byte[] buf = new byte[4096];
//            int len = 0;
//            while ((len = inputStream.read(buf)) > 0) {
//                baos.write(buf, 0, len);
//            }
//            bytes = baos.toByteArray();
//            httpRequest.addPart(new ByteArrayPart("file", bytes, null, null, ""));
//            int status = httpRequest.execute().get().getStatusCode();
//            return status;
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    log.info("upload input stream close error:{}",e);
//                }
//            }
//        }
//    }
//
//    /**
//     * 异步-从简单文件服务器系统删除一个文件
//     *
//     * @param url
//     *            要删除的文件路径
//     * @param handler
//     *          回调结果
//     */
//    public static void deleteFile(String url, HttpResponseHandler handler) {
//        HttpClientDriver httpClientDriver = FileClient.getInstance();
//        HttpRequest httpRequest = httpClientDriver.delete(url);
//        httpRequest.execute(handler);
//    }
//
//    /**
//     * 阻塞式-从简单文件服务器系统删除一个文件
//     *
//     * @param url
//     *            要删除的文件路径
//     *  @throws IOException
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//    public static int deleteFile(String url) throws ExecutionException, InterruptedException {
//        HttpClientDriver httpClientDriver = FileClient.getInstance();
//        HttpRequest httpRequest = httpClientDriver.delete(url);
//        int statusCode = httpRequest.execute().get().getStatusCode();
//        return statusCode;
//    }
}
