package xyz.silkdog.messagequeue.config;


import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import xyz.silkdog.messagequeue.constant.Constant;

import java.io.IOException;

@Configuration
@Slf4j
public class OkhttpConfig {

    private boolean isResponse2xx(int code) {
        return code / 200 == 2;
    }

    // singleton
    public static OkHttpClient okHttp = new OkHttpClient().newBuilder().build();

    public String call(String body) {
        Request request = new Request.Builder().url(Constant.BRAZE_URL).post(RequestBody.create(body, okhttp3.MediaType.parse(MediaType.APPLICATION_JSON_VALUE))).build();
        try (Response response = okHttp.newCall(request).execute()) {
            if (isResponse2xx(response.code())) {
                String resp = response.body().string();
                return "do nothing." + resp;
            } else {
                return "error";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
