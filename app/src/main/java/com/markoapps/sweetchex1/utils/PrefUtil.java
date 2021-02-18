package com.markoapps.sweetchex1.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtil {
    SharedPreferences preferenceManager;

    static final String IMAGE_INDEX_KEY = "imageIndex";

    public PrefUtil(Context context){
        preferenceManager = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getImageIndex(){
        return preferenceManager.getInt(IMAGE_INDEX_KEY, 0);
    }

    public void setImageIndex(int index){
        preferenceManager.edit().putInt(IMAGE_INDEX_KEY, index).apply();
    }

}
