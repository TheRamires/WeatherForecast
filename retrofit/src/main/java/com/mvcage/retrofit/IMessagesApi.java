package com.mvcage.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IMessagesApi {

    @GET("messages1.json")
    Call<List<Message>> messages();

}
