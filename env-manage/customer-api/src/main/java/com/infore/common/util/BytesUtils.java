/**
 * 
 */
package com.infore.common.util;

import java.math.BigInteger;

/**
 * @desc   
 * @class  BytesUtils
 * @author  create author by deer
 * @date  2017年12月15日下午1:40:37
 */
public class BytesUtils {
	
	 /**
     * 
     * <pre>
     * 将一个16位的short转换为长度为2的8位byte数组.
     * </pre>
     * 
     * @param s
     * @return
     */
    public static byte[] shortToByte2(Short s) {
        byte[] arr = new byte[2];
        arr[0] = (byte) (s >> 8);
        arr[1] = (byte) (s & 0xff);
        return arr;
    }
	
    /** 
     * 2进制转16进制
     *  
     * @param bytes 
     * @return 
     */  
    public static String bytes2hex(byte[] bytes)  
    {  
        final String HEX = "0123456789abcdef";  
        StringBuilder sb = new StringBuilder(bytes.length * 2);  
        for (byte b : bytes)  
        {  
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt((b >> 4) & 0x0f));  
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt(b & 0x0f));  
        }  
  
        return sb.toString();  
    }  
}
