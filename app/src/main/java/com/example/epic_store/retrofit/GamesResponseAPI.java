package com.example.epic_store.retrofit;

import com.example.epic_store.model.GamesResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface GamesResponseAPI {

    @Headers({
            "X-RapidAPI-Key: 70e37e7619mshce0d2d9b400fa00p1f8eaajsn22fccdab7fe3",
            "X-RapidAPI-Host: epic-store-games.p.rapidapi.com"
    })
    @GET("onSale")
    Call<List<GamesResponse>> getGames(@QueryMap Map<String, String> parameters);

    @Headers({
            "X-RapidAPI-Key: 70e37e7619mshce0d2d9b400fa00p1f8eaajsn22fccdab7fe3",
            "X-RapidAPI-Host: epic-store-games.p.rapidapi.com"
    })
    @GET("comingSoon")
    Call<List<GamesResponse>> getComingSoon(@QueryMap Map<String, String> parameters);
}
