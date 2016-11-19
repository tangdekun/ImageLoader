package com.john.imageloaderlibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.john.imageloaderlibrary.cache.inter.ImageCache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by John on 2016/11/3.
 */

public class ImageLoader {
    //缓存图片 内存缓存 SD缓存 双缓存
    ImageCache imageCache;
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    //从缓存中加载图片，如果图片没有缓存，下载图片展示并缓存；如果图片已经缓存，取出图片并展示
     public void displayImage(final String url, final ImageView imageView){
         Bitmap bitmap = imageCache.get(url);
           if(bitmap!=null){
              imageView.setImageBitmap(bitmap);
               return;
             }

         submitLoadRequest(url,imageView);
     }

    /**当本地没有缓存的时候，提交到线程池下载图片
     * @param url   图片地址
     * @param imageView 图片的待加载控件ImageView
     */
    private void submitLoadRequest(final String url,final  ImageView imageView) {
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if(bitmap == null){
                   return;
                }
                if(imageView.getTag().equals(url)){
                   imageView.setImageBitmap(bitmap);
                }
               imageCache.put(url,bitmap);
            }
        });

    }

    /**
     * @param url
     */
    //向网络端请求图片
    public Bitmap downloadImage(String url){
        Bitmap bitmap = null;
        InputStream result = null;
        try {
            URL imageUrl  =  new URL(url);
            final HttpsURLConnection conn = (HttpsURLConnection) imageUrl.openConnection();
//        bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.connect();
            result = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result == null) {
            throw new RuntimeException("stream is null");
        } else {
            try {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[10240];
                int len = 0;
                while ((len = result.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                outStream.close();
                result.close();
                byte[] data=outStream.toByteArray();

                if (data != null) {
                  bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

//                    mImageViewShow.setImageBitmap(bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//       Bitmap bitmap = null;
//        try {
//            URL imageUrl  =  new URL(url);
//            final HttpsURLConnection conn = (HttpsURLConnection) imageUrl.openConnection();
//            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
//            conn.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return bitmap;
    }



}
