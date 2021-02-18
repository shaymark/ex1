package com.markoapps.sweetchex1.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.markoapps.sweetchex1.MainApplication;
import com.markoapps.sweetchex1.network.Api;
import com.markoapps.sweetchex1.utils.PrefUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Provider {

    private static Provider  instance = new Provider();

    public static Provider get() {
        return instance;
    }

    public Context context;
    public Api api;
    public PrefUtil prefUtil;
    public ExecutorService backgroundExecutor;

    private Provider(){
        context = MainApplication.context;

        Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://example.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        api = retrofit.create(Api.class);

        prefUtil = new PrefUtil(context);

        // for background work
        backgroundExecutor = Executors.newFixedThreadPool(4);
    }


}
