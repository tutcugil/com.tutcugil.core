package com.tutcugil.core.task;

import android.content.Context;
import android.os.AsyncTask;

import com.tutcugil.core.base.BaseHttpTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class TaskExecutor{
    private static TaskExecutor mTaskExecutor;
    private Map<String, BaseHttpTask> mTasks = new HashMap<>();

    private TaskExecutor(){

    }

    public static TaskExecutor getInstance(){
        if (mTaskExecutor == null)
            mTaskExecutor = new TaskExecutor();

        return mTaskExecutor;
    }

    public void execute(String key, BaseHttpTask task){
        execute(key, task, false);
    }

    public void execute(String key, BaseHttpTask task, boolean abort){
        if (!mTasks.containsKey(key)) {
             mTasks.put(key, task);

             task.execute();
             return;
        }

        BaseHttpTask currentTask = mTasks.get(key);
        if (currentTask != null
                && currentTask.getStatus() != AsyncTask.Status.FINISHED) {

            if (!abort)
                return;

            mTasks.get(key).cancel(true);
            mTasks.remove(key);
            return;
        }

        mTasks.remove(key);
        mTasks.put(key, task);

        task.execute();
    }

    public void clean(){
        for (String key : mTasks.keySet()) {
            BaseHttpTask task = mTasks.get(key);

            if (task != null
                    && task.getStatus() != AsyncTask.Status.FINISHED)
                continue;

            mTasks.remove(key);
        }
    }
}
