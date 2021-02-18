package com.markoapps.sweetchex1.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {
    @GET
    Call<ResponseBody> download(@Url String filePath);
}
