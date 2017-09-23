package com.tutcugil.core.base;

import android.os.AsyncTask;

import com.tutcugil.core.interfaces.ITaskInterface;
import com.tutcugil.core.io.Logger;
import com.tutcugil.core.network.HttpRequest;
import com.tutcugil.core.task.TaskExecutor;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class BaseHttpTask extends AsyncTask<String, String, Response> {
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
    protected Response doInBackground(String... params) {
        try{
            return HttpRequest.make(mRequest);
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

        TaskExecutor.getInstance().clean();
    }
}
