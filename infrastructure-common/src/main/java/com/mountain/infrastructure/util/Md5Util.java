package com.mountain.infrastructure.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ProjectName: infrastructure-parent
 * @Package: com.mountain.infrastructure.util
 * @ClassName: Md5Util
 * @Author: liujinshan
 * @Description:
 * @Date: 2020/3/23 12:50
 * @Version: 1.0
 */
public class Md5Util {


    /**
     * @Method getMd5
     * @Author liujinshan
     * @Version  1.0
     * @Description MD5 加密
     * @Return
     * @Date 2020/3/23 13:59
     */
    public static String getMd5(String str){
            String algorithm = "MD5";
        try {
            MessageDigest md =  MessageDigest.getInstance(algorithm);;//获取MD5实例
            md.update(str.getBytes());//此处传入要加密的byte类型值
            byte[] digest = md.digest();//此处得到的是md5加密后的byte类型值
            BASE64Encoder base64en = new BASE64Encoder();
            return base64en.encode(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Method checkpassword
     * @Author liujinshan
     * @Version  1.0
     * @Description 判断用户密码是否正确
     * @Return true/false
     * @Date 2020/3/23 14:05
     * newpasswd 用户输入的密码
     * oldpasswd 正确密码
     * */
    public static boolean checkpassword(String newpasswd,String oldpasswd){
        if(getMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        String x = "admin";
        System.out.println(getMd5(x));
    }
}
