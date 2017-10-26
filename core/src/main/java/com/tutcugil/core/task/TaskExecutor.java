package com.tutcugil.core.task;

import android.os.AsyncTask;

import com.tutcugil.core.base.BaseHttpTask;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class TaskExecutor{
    private static TaskExecutor mTaskExecutor;
    private static boolean mCleanInProgress = false;
    private Map<String, BaseHttpTask> mTasks = new HashMap<>();

    private TaskExecutor(){

    }

    public static TaskExecutor getInstance(){
        if (mTaskExecutor == null)
            mTaskExecutor = new TaskExecutor();

        return mTaskExecutor;
    }

    public synchronized void execute(String key, BaseHttpTask task){
        execute(key, task, false);
    }

    public synchronized void execute(String key, BaseHttpTask task, boolean abort){
        if (!mCleanInProgress)
            clean();

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

    private void clean(){
        mCleanInProgress = true;

        Iterator<Map.Entry<String, BaseHttpTask>> entryIter = mTasks.entrySet().iterator();

        while (entryIter.hasNext()) {
            Map.Entry<String, BaseHttpTask> entry = entryIter.next();

            if (entry.getValue() != null
                    && entry.getValue().getStatus() != AsyncTask.Status.FINISHED)
                continue;

            entryIter.remove();
        }

        mCleanInProgress = false;
    }
}
