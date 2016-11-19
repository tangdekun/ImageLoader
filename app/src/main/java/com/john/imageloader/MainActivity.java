package com.john.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.john.imageloaderlibrary.ImageLoader;
import com.john.imageloaderlibrary.cache.inter.imple.DoubleCache;
import com.john.imageloaderlibrary.cache.inter.imple.MemoryCache;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setImageCache(new MemoryCache());
        imageLoader.displayImage("http://f.hiphotos.baidu.com/image/pic/item/5ab5c9ea15ce36d358d27ee43ef33a87e850b114.jpg",imageView);
    }
}
