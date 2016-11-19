package com.john.imageloaderlibrary.cache.inter.imple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.john.imageloaderlibrary.cache.inter.ImageCache;
import com.john.imageloaderlibrary.utils.CloseUtils;
import com.john.imageloaderlibrary.utils.ECloseUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by John on 2016/11/3.
 */

public class SdcardCache implements ImageCache {
    private static final String CACHEDIR = "sdcard/cache/";

    /**
     * @param url    图片的地址
     * @param bitmap 图片的bitmap对象
     */
    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(CACHEDIR+url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            CloseUtils.getInstance().close(fileOutputStream);
            ECloseUtil.CLOSE_UTIL.close(fileOutputStream);
        }
    }

    /**
     * @param url 缓存的图片的URL Key值
     * @return
     */
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(CACHEDIR+url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
