package com.tutcugil.core.interfaces;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public interface ITaskInterface {
    void onStart();
    void onFinished(Object response);
    void onProgress(Object obj);
    void onError(String error);
}
