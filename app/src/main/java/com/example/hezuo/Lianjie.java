package com.example.hezuo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Lianjie {

    static String IP = "192.168.1.100:8890";
    final String CZ = "SetCarAccountRecharge";//充值
    final String CX = "GetCarAccountBalance";//查询
    static String s;
    public static void chongZhi(final String id, final String money) {
        /*冲值*/
        new Thread(new Runnable() {
            String responseData1;
            @Override
            public void run() {
                try {
                    JSONObject json = new JSONObject();
                    json.put("CarId",id);
                    json.put("Money",money);
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(null, String.valueOf(json));
                    Request request = new Request.Builder()
                            .url("http://"+IP+"/transportservice/type/jason/action/SetCarAccountRecharge")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    responseData1 = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //查询系统
    public static String chaXun(final String id) {
        String responseData;
        try {
            JSONObject json = new JSONObject();
            json.put("CarId",id);
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(null, String.valueOf(json));
            Request request = new Request.Builder()
                    .url("http://"+IP+"/transportservice/type/jason/action/GetCarAccountBalance")
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            responseData = response.body().string();
            JSONObject jsonObject = new JSONObject(responseData);
            s = jsonObject.optString("Balance");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }
   static void kongzhi(final String id, final String action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = new JSONObject();
                    json.put("CarId",id);
                    json.put("CarAction",action);
                    //申明给服务端传递一个json串
                    //创建一个OkHttpClient对象
                    OkHttpClient client = new OkHttpClient();
                    //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
                    //json为String类型的json数据
                    RequestBody requestBody = RequestBody.create(null, String.valueOf(json));
                    //创建一个请求对象
                    Request request = new Request.Builder()
                            .url("http://"+IP+"/transportservice/type/jason/action/SetCarMove")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
