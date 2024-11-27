package com.handson.chatbot.service;

import okhttp3.*;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class AmazonService {

    public static final Pattern PRODUCT_PATTERN = Pattern.compile("<span class=\\\"a-size-medium a-color-base a-text-normal\\\">([^<]+)<\\/span>.*<span class=\\\"a-icon-alt\\\">([^<]+)<\\/span>.*<span class=\\\"a-offscreen\\\">([^<]+)<\\/span>");

    public String searchProducts(String keyword) throws IOException {
        return parseProductHtml(getProductHtml(keyword));
    }

    private String parseProductHtml(String html) {
        String res = "";
        Matcher matcher = PRODUCT_PATTERN.matcher(html);
        while (matcher.find()) {
            res += matcher.group(1) + " - " + matcher.group(2) + ", price:" + matcher.group(3) + "<br>\n";
        }
        return res;
    }

    private String getProductHtml(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://www.amazon.com/s?i=aps&k=" + keyword + "&ref=nb_sb_noss&url=search-alias%3Daps")
                .get()
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .addHeader("accept-language", "en-US,en;q=0.9")
                .addHeader("cookie", "aws-ubid-main=366-7345117-0533442; aws-account-alias=995553441267; remember-account=false; regStatus=registered; noflush_awsccs_sid=82c4a897d35f0a3af8d303160a857d05d1835b77680b4fd15a8ac5209b6f5652; aws-userInfo=%7B%22arn%22%3A%22arn%3Aaws%3Aiam%3A%3A995553441267%3Auser%2Fnirsh%22%2C%22alias%22%3A%22995553441267%22%2C%22username%22%3A%22nirsh%22%2C%22keybase%22%3A%22%22%2C%22issuer%22%3A%22http%3A%2F%2Fsignin.aws.amazon.com%2Fsignin%22%2C%22signinType%22%3A%22PUBLIC%22%7D; session-id=139-4014178-8661840; session-id-time=2082787201l; i18n-prefs=USD; skin=noskin; ubid-main=135-0555791-4956662; session-token=QmJavS9O7w0QCFu+6COElvWVftgi477MgszeRNDxjx7OwI6HXdpDVq++7XUW2y/MubieFYQx3Max/CnlI91C+E4O+WTLLDB79v9jCcBoDX/URRsMgBrKmSmGxx5FdzhCZCm2yYqO57z2s5x7hkLRuFgnrtGdo7cip2DQcLeEMOd7Gq0I0R16Zaa66N83nDOrX55VyzQuW/1rDvUrpq0X0I1Qo0ApPc/nUTvrGOjKOdkh/b8HJ7Oq9faOUV6i96Hr27G4YRT9IU33Ebh2Yz5XLREecK7fb10FAerfb1L55N51AHLrt48SJT7GUG0SemBB/OdNH209QuXCUBFh9+CVmGWivMQiovPT; csm-hit=tb:WWASJ5TCQJT711H6ZNDN+s-D8TB09SG9GP62AHCV0W2|1732526351268&t:1732526351268&adb:adblk_no; session-token=cNK5KEFK8gaB5T4K0LyiO6iaoI/iLJ3tyF8shVy2nB70x8uZbBAGsecfh/sv/1Klag8bU6om2tn6Vdf4CEB8G9BnOZvgfSiy2V/RErqPz42/n0ZvaI42MDaYRZrsmWR8lLRTJKnrVbvbXbIMzJuCVUbpw3PuNg8TZIgr/aLFd3MMHs71TB3BSIpTWS5c+L2ai1wOo5gm+2g1KEc4iEiqH3j+ZA0uM6YDC257qVoVUTB1yKvwleupstjfsjzepPLmuY0bibiZ21XwHrAPwFDuzjpVMX5MOuJa425Cyb1i8VgZx5FVRmgVGnDkgjSkWYuA3EwIXi+pUCTSJRlwCfFl9xoGq+eM3MeY")
                .addHeader("device-memory", "8")
                .addHeader("downlink", "10")
                .addHeader("dpr", "1")
                .addHeader("ect", "4g")
                .addHeader("priority", "u=0, i")
                .addHeader("referer", "https://www.amazon.com/s?k=ipod&crid=6CFJC9M8GOCV&sprefix=ipod%2Caps%2C257&ref=nb_sb_noss_1")
                .addHeader("rtt", "50")
                .addHeader("sec-ch-device-memory", "8")
                .addHeader("sec-ch-dpr", "1")
                .addHeader("sec-ch-ua", "\"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Linux\"")
                .addHeader("sec-ch-viewport-width", "1151")
                .addHeader("sec-fetch-dest", "document")
                .addHeader("sec-fetch-mode", "navigate")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-user", "?1")
                .addHeader("upgrade-insecure-requests", "1")
                .addHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36")
                .addHeader("viewport-width", "1151")
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}