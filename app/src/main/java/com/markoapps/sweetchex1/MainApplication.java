package com.markoapps.sweetchex1;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class MainApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
    }

}

