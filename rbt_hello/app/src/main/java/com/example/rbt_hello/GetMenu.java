package com.example.rbt_hello;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMenu {

    @GET("v2/5d2edf4d340000610064d358/")
    Call<MenuEncapsulated> getMenu();

}
