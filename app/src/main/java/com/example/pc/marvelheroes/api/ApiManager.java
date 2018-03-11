package com.example.pc.marvelheroes.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Pc on 3/11/2018.
 */

public class ApiManager {
    private OkHttpClient client;

    public ApiManager() {
        client = new OkHttpClient();
    }
    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public okhttp3.Call getHeroInfo(double heroId) {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        String publickey = "ce84cd0f696ef1be780895bc2eaf3725";
        String privatekey = "4e2bbd7bae9479a861ab409ee494cce622ee4e48";

        String stringmd5 = ts+privatekey+publickey;
        String MD5 = md5(stringmd5);


        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("gateway.marvel.com:80")
                .addPathSegment("v1")
                .addPathSegment("public")
                .addPathSegment("characters")
                .addQueryParameter("id", String.valueOf(heroId))
                .addQueryParameter("apikey", publickey)
                .addQueryParameter("hash", MD5)
                .addQueryParameter("ts", ts)
                .build();


        Request request = new Request.Builder()
                .url(url)
                .build();

        return client.newCall(request);
    }


}
