package com.tutcugil.core.base;

import android.os.AsyncTask;

import com.tutcugil.core.interfaces.ITaskInterface;
import com.tutcugil.core.io.Logger;
import com.tutcugil.core.network.HttpRequest;

import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class BaseHttpTask extends AsyncTask<String, String, Response> {
    private ITaskInterface mTaskInterface;
    private RequestBody mRequestBody;
    private String mUrl;

    public BaseHttpTask(String url, RequestBody requestBody, ITaskInterface taskInterface) {
        mUrl = url;
        mTaskInterface = taskInterface;
        mRequestBody   = requestBody;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        try {
            if (mTaskInterface != null)
                mTaskInterface.onStart();
        } catch (Exception ex){
            Logger.error(ex);
        }
    }

    @Override
    protected Response doInBackground(String... params) {
        try{
            if ("GET".equals(params[0]))
                return HttpRequest.get(params[1]);

            return HttpRequest.post(params[1], mRequestBody);
        } catch (Exception ex) {
            Logger.error(ex);

            mTaskInterface.onError(ex.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);

        mTaskInterface.onFinished(response);
    }
}
