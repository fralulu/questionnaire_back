package com.infore.common.reqVO;

/**
 * Created by xuyao on 23/01/2018.
 */
public class IpcSetVO {

    private Integer ipcId;

    // 视频源访问token
    private String videoSourceConfigurationToken;

    // 字体大小
    private int fontSize;

    // 字体颜色
    private int[] fontcolor;

    // OSD时间日期token
    private String osdDateAndTimeToken;

    // OSD文字token
    private String osdPlainTextToken;

    // 时间所在的位置
    private float[] position;

    // 文本信息
    private String plainText;

    public Integer getIpcId() {
        return ipcId;
    }

    public void setIpcId(Integer ipcId) {
        this.ipcId = ipcId;
    }

    public String getVideoSourceConfigurationToken() {
        return videoSourceConfigurationToken;
    }

    public void setVideoSourceConfigurationToken(String videoSourceConfigurationToken) {
        this.videoSourceConfigurationToken = videoSourceConfigurationToken;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int[] getFontcolor() {
        return fontcolor;
    }

    public void setFontcolor(int[] fontcolor) {
        this.fontcolor = fontcolor;
    }

    public String getOsdDateAndTimeToken() {
        return osdDateAndTimeToken;
    }

    public void setOsdDateAndTimeToken(String osdDateAndTimeToken) {
        this.osdDateAndTimeToken = osdDateAndTimeToken;
    }

    public float[] getPosition() {
        return position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getOsdPlainTextToken() {
        return osdPlainTextToken;
    }

    public void setOsdPlainTextToken(String osdPlainTextToken) {
        this.osdPlainTextToken = osdPlainTextToken;
    }
}
