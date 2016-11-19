package com.john.imageloaderlibrary.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by John on 2016/11/3.
 */

public class CloseUtils {
    private static CloseUtils singleInstance;
    private CloseUtils(){

    }
    public static CloseUtils getInstance(){
          if(singleInstance == null)
              singleInstance = new CloseUtils();
        return  singleInstance;

    }

    public void close(Closeable closeable){
        if(closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
