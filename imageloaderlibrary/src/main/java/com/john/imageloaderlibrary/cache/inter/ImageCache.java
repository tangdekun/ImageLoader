package com.john.imageloaderlibrary.cache.inter;

import android.graphics.Bitmap;

/**
 * Created by John on 2016/11/3.
 */

public interface ImageCache {

    /** 缓存图片
     * @param url  图片的地址
     * @param bitmap 图片的bitmap对象
     */
    public  void put(String url,Bitmap bitmap);

    /**  获取缓存图片
     * @param url 缓存的图片的URL Key值
     * @return  位图
     */
    public Bitmap get(String url);

}
