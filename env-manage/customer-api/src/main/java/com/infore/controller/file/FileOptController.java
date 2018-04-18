package com.infore.controller.file;

import com.infore.common.Properties;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.common.util.FileClient;
import com.infore.common.util.FileUtil;
import com.infore.model.ResponseDto;
//import com.infore.seaweedfs.RequestResult;
//import com.infore.seaweedfs.WeedfsClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

/**
 * Created by xuyao on 2017/7/13.
 */
@RestController
@RequestMapping("/file")
@Api(value = "file", description = "文件操作", produces = "application/json")
public class FileOptController {

   /* private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Properties properties;
    *//**
     * 文件上传具体实现方法（单文件上传）
     *//*
//    @ApiOperation(value = "分布式文件系统单个文件上传")
//    @PostMapping(value = "/seaUpload", consumes = {"multipart/form-data"})
//    @ResponseBody
    public ResponseDto seaUpload(@RequestPart(value = "file") MultipartFile file) {
        if (!file.isEmpty()) {
            OutputStream os = null;
            String fileName = file.getOriginalFilename();
            String tmpUrl = properties.SEAWEEDFS_TEMP
                + fileName;
            try {
                WeedfsClient client = new WeedfsClient();
                client.setAssign(properties.SEAWEEDFS_ASSIGN);
                client.setMasterHost(properties.SEAWEEDFS_HOST);
                client.setMasterPort(Integer.valueOf(properties.SEAWEEDFS_PORT));

                File tmpFile = new File(tmpUrl);
                FileUtil.mkDir(properties.SEAWEEDFS_TEMP);
                InputStream in = file.getInputStream();
                byte[] bs = new byte[1024];
                int len;
                os = new FileOutputStream(tmpUrl);
                while ((len = in.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                RequestResult result = client.cache(tmpFile);
                log.info("seaweedfs response:{}", result.toString());
                return new ResponseDto(result);
            } catch (IOException e) {
                log.error("upload 文件异常:{}", e);
                throw new ServerRuntimeException("上传文件异常,请联系管理员!");
            }finally {
                FileUtil.delDir(tmpUrl,true);
            }
        } else {
            throw new ServerRuntimeException("文件为空");
        }
    }

    @ApiOperation(value = "传统单机文件系统,单个文件-上传")
    @PostMapping(value = "/upload1")
    @ResponseBody
    public ResponseDto upload1(@RequestParam(value = "file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            OutputStream os = null;
            String fileName = file.getOriginalFilename();
            String timePath = DateTimeUtils.currentTimeMillis() + "/";
            String filePath = timePath+fileName;
            String destPath = properties.COMMON_FILE_ROOT + filePath;
            String tempPath=properties.COMMON_FILE_TEMP+filePath;

            try {
                File tmpFile = new File(tempPath);
                FileUtil.mkDir(properties.COMMON_FILE_TEMP+timePath);
                InputStream in = file.getInputStream();
                byte[] bs = new byte[1024];
                int len;
                os = new FileOutputStream(tempPath);
                while ((len = in.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                final boolean[] flag = {false};
                //异步上传,回调接口
                FileClient.uploadFile(properties.COMMON_FILE_HOST,destPath,tmpFile.getPath(),(rsp,e)-> {
                    log.info("common file system upload response:{}",rsp.toString());
                    if (rsp.getStatusCode() == 200) {
                        flag[0] = true;
                        //清除临时目录
                        try {
                            FileUtil.delFile(tempPath);
                            FileUtils.deleteDirectory(new File(properties.COMMON_FILE_TEMP+timePath));
                        } catch (IOException e1) {
                            log.error("清除临时目录异常:{}", e1);
                        }
                    }
                });

                //异步上传接口,睡眠3毫秒等回调
                java.lang.Thread.sleep(3000);
                if (flag[0]) {
                    Map<String, Object> respMap = new HashMap();
                    respMap.put("filePath", filePath);
                    respMap.put("fileUrl", properties.COMMON_FILE_HOST + destPath);
                    return new ResponseDto(respMap);
                } else {
                    return new ResponseDto(false,"文件上传失败,联系管理员!");
                }

            } catch (IOException e) {
                log.error("upload 文件异常:{}", e);
                throw new ServerRuntimeException("上传文件异常,请联系管理员!");
            }
        } else {
            throw new ServerRuntimeException("文件为空");
        }
    }

    @ApiOperation(value = "签名-上传,单个文件上传")
    @PostMapping(value = "/upload/signature")
    @ResponseBody
    public ResponseDto uploadSignature(@RequestParam(value = "signature") String signature) throws Exception {
        try {
            String imgType=null;
            String base64Img = null;
            if (signature.contains("image/")&&signature.contains(";base64,")) {
                imgType = StringUtils.substringBetween(signature, "image/", ";base64,");
                base64Img = StringUtils.substringAfter(signature, ";base64,");
            }else {
                return new ResponseDto(false,"签名格式不对,联系管理员!");
            }
            String fileName =String.valueOf(System.currentTimeMillis())+"."+imgType;
            String timePath = System.currentTimeMillis() + "/";
            String filePath = timePath+fileName;
            String destPath = properties.COMMON_FILE_ROOT + filePath;
            InputStream in = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(base64Img));//base64解码后转成输入流
            int statusCodes =FileClient.uploadFile(properties.COMMON_FILE_HOST,destPath,in);
            if (statusCodes==200) {
                Map<String, Object> respMap = new HashMap();
                respMap.put("filePath", filePath);
                respMap.put("fileUrl", properties.COMMON_FILE_HOST + destPath);
                return new ResponseDto(respMap);
            } else {
                return new ResponseDto(false,"签名上传失败,联系管理员!");
            }

        } catch (Exception e) {
            log.error("签名上传异常:{}", e);
            throw new ServerRuntimeException("签名上传异常,请联系管理员!");
        }
    }
    @ApiOperation(value = "传统单机文件系统,单个文件-上传")
    @PostMapping(value = "/upload")
    @ResponseBody
    public ResponseDto upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String timePath = System.currentTimeMillis() + "/";
            String filePath = timePath+fileName;
            String destPath = properties.COMMON_FILE_ROOT + filePath;

            try {
                InputStream in = file.getInputStream();
                int statusCodes =FileClient.uploadFile(properties.COMMON_FILE_HOST,destPath,in);
                if (statusCodes==200) {
                    Map<String, Object> respMap = new HashMap();
                    respMap.put("filePath", filePath);
                    respMap.put("fileUrl", properties.COMMON_FILE_HOST + destPath);
                    return new ResponseDto(respMap);
                } else {
                    return new ResponseDto(false,"文件上传失败,联系管理员!");
                }

            } catch (Exception e) {
                log.error("upload 文件异常:{}", e);
                throw new ServerRuntimeException("上传文件异常,请联系管理员!");
            }
        } else {
            throw new ServerRuntimeException("文件为空");
        }
    }


    @ApiOperation(value = "传统单机文件系统,文件删除")
    @PostMapping(value = "/del")
    @ResponseBody
    public ResponseDto delFile(@RequestParam(value = "filePath") String filePath) throws Exception {
        String file = StringUtils.substring(filePath,0,13);//截取13位的毫秒数文件目录名,上传文件的以此
        String url = properties.COMMON_FILE_HOST + properties.COMMON_FILE_ROOT+file;
        final boolean[] flag = {false};
        int statusCode=FileClient.deleteFile(url);//传文件夹路径
        if (statusCode==200) {
            return new ResponseDto("删除成功");
        }else {
            return new ResponseDto(false,"删除失败");
        }
    }*/
}
