package com.gabrielfeo.backintheday.util.logging;

import android.util.Log;

import com.gabrielfeo.backintheday.BuildConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class Logger {

    private static final String TAG = Logger.class.getSimpleName();
    private static final Map<Object, String> OBJECT_TAG_CACHE = new WeakHashMap<Object, String>();
    private static final Map<Class, String> CLASS_TAG_CACHE = new HashMap<>();
    // TODO Threading issues?

    public static void info(Object author, String message) {
        Log.i(getTagFor(author), message);
    }

    public static void debug(Object author, String message) {
        if (BuildConfig.DEBUG) Log.d(getTagFor(author), message);
    }

    public static void error(Object author, String message) {
        Log.e(getTagFor(author), message);
    }

    public static void error(Object author, String message, Throwable throwable) {
        Log.e(getTagFor(author), message, throwable);
    }

    private static String getTagFor(Object author) {
        try {
            return getCachedOrNewTagFor(author);
        } catch (Exception exception) {
            Class authorClass = author.getClass();
            Log.e(TAG, "Logger error. Couldn't get tag for " + authorClass.getCanonicalName(), exception);
            exception.printStackTrace();
            return getNewTagFor(author, authorClass);
        }
    }

    private static String getCachedOrNewTagFor(Object author) {
        if (OBJECT_TAG_CACHE.containsKey(author)) {
            return OBJECT_TAG_CACHE.get(author);
        }

        Class authorClass = author.getClass();
        if (CLASS_TAG_CACHE.containsKey(authorClass)) {
            return CLASS_TAG_CACHE.get(authorClass);
        }

        return getNewTagFor(author, authorClass);
    }

    private static String getNewTagFor(Object author, Class authorClass) {
        final String tag = authorClass.getSimpleName();
        OBJECT_TAG_CACHE.put(author, tag);
        CLASS_TAG_CACHE.put(authorClass, tag);
        return tag;
    }

}
