package com.szh.testpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);

        Picasso.with(this) //设置context
                .load("http://d.hiphotos.baidu.com/image/pic/item/ca1349540923dd54bdb23fb8db09b3de9d824819.jpg") //图片url地址
                .placeholder(R.drawable.ic_launcher_background) //加载时显示的图片
                .error(R.drawable.ic_launcher_background) //加载错误显示的图片
                .fit() //自动按照图片尺寸进行压缩
                .tag("image") //图片tag，便于控制图片加载和暂停加载
                .into(iv);//图片显示的imageview
    }
}
