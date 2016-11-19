package com.john.imageloaderlibrary.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by John on 2016/11/5.
 */

public enum ECloseUtil {
    CLOSE_UTIL;
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
