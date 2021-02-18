package com.markoapps.sweetchex1.viewmodels;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.markoapps.sweetchex1.di.Provider;
import com.markoapps.sweetchex1.network.Api;
import com.markoapps.sweetchex1.utils.FileUtil;
import com.markoapps.sweetchex1.utils.PrefUtil;

public class MainViewModel extends ViewModel {

    Context appContext = Provider.get().context;
    Api api = Provider.get().api;
    PrefUtil prefUtil = Provider.get().prefUtil;
    ExecutorService backgroundExecutor = Provider.get().backgroundExecutor;

    static final String TAG = MainViewModel.class.getSimpleName();
    static final String ZIP_File_PATH = "zip1.zip";
    static final String IMAGES_PATH = "images";

    public MutableLiveData<String> imageUrlLiveData = new MutableLiveData<>();

    public void updateImage(){

        String imageUrl = getNextImage();
        api.download(imageUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody>  response) {
                backgroundExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String imageUrlString = handleFile(response.body().byteStream());
                        imageUrlLiveData.postValue(imageUrlString);
                    }
                });
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    String handleFile(InputStream inputStream){
        String zipFilePath = appContext.getFilesDir() + File.separator + ZIP_File_PATH;
        String imagesFilePath = appContext.getFilesDir() + File.separator + IMAGES_PATH;

        try {
            FileUtil.saveFileToDisk(inputStream, zipFilePath);
            FileUtil.deleteFilesFromDirectory(imagesFilePath);
            FileUtil.extractZipFile(zipFilePath, imagesFilePath);
            String imageFilePath =  FileUtil.getFirstFile(imagesFilePath);
            String fileUri = Uri.fromFile(new File(imageFilePath)).toString();
            return fileUri;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    String getNextImage(){
        String images [] = {
                "https://test-assets-mobile.s3-us-west-2.amazonaws.com/125%402.zip",
                "https://test-assets-mobile.s3-us-west-2.amazonaws.com/127%402.zip"
        };

        int imageIndex = prefUtil.getImageIndex();
        prefUtil.setImageIndex((imageIndex + 1) % images.length);

        return images[imageIndex];
    }

}
