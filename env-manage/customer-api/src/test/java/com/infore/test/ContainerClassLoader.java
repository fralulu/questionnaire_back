package com.infore.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuyao on 2017/9/30.
 */
public class ContainerClassLoader extends ClassLoader {

    private Map<String, Class<?>> loadedClasses = new HashMap<String, Class<?>>();
    private static ContainerClassLoader INSTANCE;

    private ContainerClassLoader() {
        super(ContainerClassLoader.class.getClassLoader().getParent().getParent());
    }

    /**
     * 初始化
     */
    public static void init() {
        INSTANCE = new ContainerClassLoader();
        try {
            INSTANCE.addThisToParentClassLoader(
                ContainerClassLoader.class.getClassLoader().getParent());
        } catch (Exception e) {
            System.err.println("设置classloader到容器中时出现错误！");
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (loadedClasses.containsKey(name)) {
            return loadedClasses.get(name);
        }
        return super.loadClass(name, resolve);
    }

    /**
     * 将this替换为指定classLoader的parent ClassLoader     *      * @param classLoader
     */
    private void addThisToParentClassLoader(ClassLoader classLoader) throws Exception {
        URLClassLoader cl = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Field field = ClassLoader.class.getDeclaredField("parent");
        field.setAccessible(true);
        field.set(classLoader, this);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName =
            "D:/MyTest/classEncrypt/extendPro/" + name.replace(".", File.separator) + ".class";
        File f = new File(fileName);
        if (f.exists()) {
            FileInputStream fis = null;
            ByteArrayOutputStream bos = null;
            try {      //将class文件进行解密
                fis = new FileInputStream(fileName);
                bos = new ByteArrayOutputStream();
                encodeAndDecode(fis, bos);
                byte[] classByte = bos.toByteArray();
                //将字节流变成一个class
                return defineClass(name, classByte, 0, classByte.length);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 加密和解密算法
     * * @param is
     * * @param os
     * * @throws Exception
     */
    private static void encodeAndDecode(InputStream is, OutputStream os) throws Exception {
        int bytes = -1;
        while ((bytes = is.read()) != -1) {
            bytes = bytes ^ 0xff;
            //和0xff进行异或处理
            os.write(bytes);
        }
    }

    public static void encodeClass() throws Exception {
        String srcPath = "D:/sheungxin/workspaces/jeesite-master/src/main/webapp/WEB-INF/classes/com/thinkgem/jeesite/test/service/HelloWorldService.class";
        String desPath = "d:/";
//ClassLoaderAttachment.class输出的路径
        String desFileName = srcPath.substring(srcPath.lastIndexOf("/") + 1);
        String desPathFile = desPath + "/" + desFileName;
        FileInputStream fis = new FileInputStream(srcPath);
        FileOutputStream fos = new FileOutputStream(desPathFile);
//将class进行加密
        encodeAndDecode(fis, fos);
        fis.close();
        fos.close();
    }
}