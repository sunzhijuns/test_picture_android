package com.szh.testpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn;
    private String[] cities = new String[]{"beijing", "shenzhen", "shanghai"};
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;

    private static final String LOG_TAG = "测试MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv1 = findViewById(R.id.tv_szj);
        mTv2 = findViewById(R.id.tv_szj1);
        mTv3 = findViewById(R.id.tv_szj2);
        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
        mTv3.setOnClickListener(this);

        mBtn = findViewById(R.id.btn_all);
        mBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_szj:
                getCityWeather(cities[0]);
                break;
            case R.id.tv_szj1:
                getCityWeather(cities[1]);
                break;
            case R.id.tv_szj2:
                getCityWeather(cities[2]);
                break;
            case R.id.btn_all:
                getAllWeather();
                break;
        }

    }

    private void getAllWeather() {
        Observable.from(cities).flatMap(new Func1<String, Observable<WeatherData>>() {
            @Override
            public Observable<WeatherData> call(String city) {
                return RxJavaUtil.getWeatherData(city);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(/*onNext*/new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        String cityName = weatherData.getName().toLowerCase();
                        switch (cityName) {
                            case "beijing":
                                mTv1.setText(weatherData.toString());
                                break;
                            case "shenzhen":
                                mTv2.setText(weatherData.toString());
                                break;
                            case "shanghai":
                                mTv3.setText(weatherData.toString());
                                break;
                        }
                    }
                }, /*onError*/new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void getCityWeather(final String cityName) {
        RxJavaUtil.getWeatherData(cityName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        Log.d(LOG_TAG, weatherData.toString());
                        switch (cityName) {
                            case "beijing":
                                mTv1.setText(weatherData.toString());
                                break;
                            case "shenzhen":
                                mTv2.setText(weatherData.toString());
                                break;
                            case "shanghai":
                                mTv3.setText(weatherData.toString());
                                break;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(LOG_TAG, throwable.getMessage(), throwable);
                    }
                });
    }
}
