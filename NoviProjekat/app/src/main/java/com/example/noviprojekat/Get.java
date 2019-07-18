package com.example.noviprojekat;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Get {

    @GET("/measurement/latest")
    Call<Measurement> get();

}
