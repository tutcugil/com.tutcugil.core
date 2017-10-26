package com.tutcugil.core.base;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tutcugil.core.interfaces.ITaskInterface;
import com.tutcugil.core.io.Logger;
import com.tutcugil.core.network.HttpRequest;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class BaseHttpTask extends AsyncTask<String, String, Object> {
    private ITaskInterface mTaskInterface;
    private Request mRequest;

    public BaseHttpTask(Request request, ITaskInterface taskInterface) {
        mRequest = request;
        mTaskInterface = taskInterface;
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
    protected Object doInBackground(String... params) {
        try{
            Response response = HttpRequest.make(mRequest);

            if (response == null)
                return null;

            String resp = response.body().string();

            if (TextUtils.isEmpty(resp))
                return null;

            return resp;
        } catch (Exception ex) {
            Logger.error(ex);

            mTaskInterface.onError(ex.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object response) {
        super.onPostExecute(response);

        mTaskInterface.onFinished(response);
    }
}
