package com.infore.model.fact;

/**
 * Created by xuyao on 2017/9/23.
 */
public class AddressCheckResult {

    private boolean postCodeResult = false; // true:通过校验；false：未通过校验

    public boolean isPostCodeResult() {
        return postCodeResult;
    }

    public void setPostCodeResult(boolean postCodeResult) {
        this.postCodeResult = postCodeResult;
    }
}
