package com.example.yunxin_sendcode.untill;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 短信工具类
 * Created by LV on 2016/4/15 0015.
 * Email:LvLoveYuForever@gmail.com
 */
public class MobileMessageSend {
    private static final String SERVER_URL = "https://api.netease.im/sms/sendcode.action";//请求的URL
    private static final String APP_KEY = "190b2f251e85af8490dec195317afd94";//账号
    private static final String APP_SECRET = "4a16a039e627";//密码
    private static final String TEMPLATE_ID = "9284520";//模板ID
    private static final String NONCE = "123456";//随机数
    //验证码长度，范围4～10，默认为4
    private static final String CODELEN="6";

    public static Boolean sendMsg(String phone) throws IOException {
        System.out.println(phone);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(SERVER_URL);

        String curTime = String.valueOf((new Date().getTime() / 1000L));
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        //设置请求的header
        post.addHeader("AppKey", APP_KEY);
        post.addHeader("Nonce", NONCE);
        post.addHeader("CurTime", curTime);
        post.addHeader("CheckSum", checkSum);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        //设置请求参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("templateid", TEMPLATE_ID));
        //语音mobile，短信moblies
        nameValuePairs.add(new BasicNameValuePair("mobiles", "['" + phone + "']"));
        nameValuePairs.add(new BasicNameValuePair("codeLen", CODELEN));
        nameValuePairs.add(new BasicNameValuePair("params", "['" + 123456 + "']"));

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));

        //执行请求
        HttpResponse response = httpclient.execute(post);

        String responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(responseEntity);
        //判断是否发送成功，发送成功返回true
        String code = responseEntity.substring(8,11);
        if (code.equals("200")) {
            return true;
        }

        return false;
    }
}
