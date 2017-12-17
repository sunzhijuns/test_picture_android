package com.szh.testpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        String apiUrl = "/action/apiv2/banner";
        Map<String,Object> map = new HashMap<>();
        map.put("catalog",1);
        Request request = ItheimaHttp.newGetRequest(apiUrl);//apiUrl格式："xxx/xxxxx"
        request.putParamsMap(map);
        Call call = ItheimaHttp.send(request, new HttpResponseListener<Banner>() {
            @Override
            public void onResponse(Banner bean) {
                Log.i("服务器返回的数据","---");
                Log.i("服务器返回的数据","---" + bean);

            }
            /**
             * 可以不重写失败回调
             * @param call
             * @param e
             */
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
            }
        });
    }
}
