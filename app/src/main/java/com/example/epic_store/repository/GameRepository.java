package com.example.epic_store.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.epic_store.model.GameSaved;
import com.example.epic_store.model.GameSavedDao;
import com.example.epic_store.model.GameSavedDatabase;
import com.example.epic_store.model.GamesResponse;
import com.example.epic_store.retrofit.GamesResponseAPI;
import com.example.epic_store.retrofit.RetrofitRequest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository {

    private GamesResponseAPI apiRequest;
    private GameSavedDao gameSavedDao;
    private LiveData<List<GameSaved>> allGames;
    private ExecutorService executorService;

    public GameRepository(Application application){
        GameSavedDatabase gameSavedDatabase=GameSavedDatabase.getInstance(application);
        gameSavedDao=gameSavedDatabase.gameSavedDao();
        allGames=gameSavedDao.getAllGames();
        executorService= Executors.newSingleThreadExecutor();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(GamesResponseAPI.class);
    }

    public void insert(GameSaved gameSaved){
        executorService.execute(()->gameSavedDao.insert(gameSaved));
    }

    public void delete(GameSaved gameSaved){
        executorService.execute(()->gameSavedDao.delete(gameSaved));
    }

    public void update(GameSaved gameSaved){
        executorService.execute(()->gameSavedDao.update(gameSaved));
    }

    public LiveData<List<GameSaved>> getAllGames(){
        return allGames;
    }

    public LiveData<List<GameSaved>> getGame(String chars){
        LiveData<List<GameSaved>> game = gameSavedDao.getGame(chars);
        return game;
    }

    public LiveData<List<GamesResponse>> getGames(Map<String, String> parameters)
    {
        final MutableLiveData<List<GamesResponse>> data = new MutableLiveData<>();
        apiRequest.getGames(parameters).enqueue(new Callback<List<GamesResponse>>() {
            @Override
            public void onResponse(Call<List<GamesResponse>> call, Response<List<GamesResponse>> response) {

                if(response.body() != null)
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GamesResponse>> call, Throwable t) {

                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<List<GamesResponse>> getComingSoon(Map<String, String> parameters)
    {
        final MutableLiveData<List<GamesResponse>> data = new MutableLiveData<>();
        apiRequest.getComingSoon(parameters).enqueue(new Callback<List<GamesResponse>>() {
            @Override
            public void onResponse(Call<List<GamesResponse>> call, Response<List<GamesResponse>> response) {

                if(response.body() != null)
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GamesResponse>> call, Throwable t) {

                data.setValue(null);
            }
        });
        return data;
    }
}
