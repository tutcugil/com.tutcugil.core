package com.tutcugil.core.io;

import android.util.Log;

/**
 * Created by Muhammet TUTCUGIL on 23.09.2017.
 * http://www.tutcugil.com
 */

public class Logger {
    public static void trace(String message){
        StackTraceElement[]   stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = (stacktrace != null && stacktrace.length >= 2 ? stacktrace[2] : null);

        String caller = (e != null ? e.getClassName() + "." + e.getMethodName() : "");

        String spaces = "";
        for (int i = 0; i < (50 - caller.length()); i++)
            spaces += " ";

        Log.d("[APP]", caller + spaces + message);
    }

    public static void warning(String message){
        StackTraceElement[]   stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = (stacktrace != null && stacktrace.length >= 2 ? stacktrace[2] : null);

        String caller = (e != null ? e.getClassName() + "." + e.getMethodName() : "");

        String spaces = "";
        for (int i = 0; i < (50 - caller.length()); i++)
            spaces += " ";

        Log.w("[APP]", caller + spaces + " [WARNING] " +  message);
    }

    public static void error(Exception ex){
        StackTraceElement[]   stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = (stacktrace != null && stacktrace.length >= 2 ? stacktrace[2] : null);

        String caller = (e != null ? e.getClassName() + "." + e.getMethodName() : "");

        String spaces = "";
        for (int i = 0; i < (50 - caller.length()); i++)
            spaces += " ";

        Log.e("[APP]", caller + spaces + ex.getMessage());
    }
}
