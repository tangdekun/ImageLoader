package com.john.imageloaderlibrary.cache.inter.imple;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.john.imageloaderlibrary.cache.inter.ImageCache;

/**
 * Created by John on 2016/11/3.
 */

public class MemoryCache implements ImageCache {
    LruCache<String,Bitmap> memoeyCache;

    public MemoryCache(){
        initMemoryCache();
    }

    /**
     * 获取CPU的最大内存，并把最大内存的四分之一作为缓存
     */
    private void initMemoryCache() {
        final int  maxMemoey = (int) (Runtime.getRuntime().maxMemory() /1024);
        final int  memoryCacheSize = maxMemoey/4;
        memoeyCache   = new LruCache<String, Bitmap>(memoryCacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }
        };

    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoeyCache.put(url, bitmap);

    }

    @Override
    public Bitmap get(String url) {
        return memoeyCache.get(url);
    }
}
