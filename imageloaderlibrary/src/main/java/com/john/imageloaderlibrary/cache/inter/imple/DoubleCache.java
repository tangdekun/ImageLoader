package com.john.imageloaderlibrary.cache.inter.imple;

import android.graphics.Bitmap;

import com.john.imageloaderlibrary.cache.inter.ImageCache;

/**
 * Created by John on 2016/11/3.
 */

public class DoubleCache implements ImageCache {
    ImageCache memoryCache,sdcardCache;
    public DoubleCache(){
        memoryCache = new MemoryCache();
        sdcardCache = new SdcardCache();
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url, bitmap);
        sdcardCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap==null)
            bitmap = sdcardCache.get(url);
        return bitmap;
    }
}
