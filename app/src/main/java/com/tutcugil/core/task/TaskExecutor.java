package com.tutcugil.core.task;

import android.content.Context;
import android.os.AsyncTask;

import com.tutcugil.core.base.BaseClass;

import java.util.Queue;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class TaskExecutor extends BaseClass {
    private static TaskExecutor mTaskExecutor;
    private Queue<AsyncTask> mTasks;

    private TaskExecutor(){

    }

    public static TaskExecutor getInstance(Context context){
        if (mTaskExecutor == null) {
            mTaskExecutor = new TaskExecutor();
            mTaskExecutor.setContext(context);
        }

        return mTaskExecutor;
    }

    public void add(AsyncTask task){

    }

    @Override
    protected String getTAG() {
        return "TaskExecutor";
    }
}
