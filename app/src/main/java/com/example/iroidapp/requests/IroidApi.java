package com.example.iroidapp.requests;

import com.example.iroidapp.requests.response.IroidResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IroidApi {

    //Get Recipe
    @GET("friday/index.php")
    Call<IroidResponse> getRecipe(
            @Query("route") String route
    );
}
