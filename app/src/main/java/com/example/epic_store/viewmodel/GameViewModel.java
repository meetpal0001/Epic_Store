package com.example.epic_store.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.epic_store.model.GameSaved;
import com.example.epic_store.model.GamesResponse;
import com.example.epic_store.repository.GameRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameViewModel extends AndroidViewModel {
    private GameRepository gameRepository;
    private LiveData<List<GameSaved>> allgames;

    private LiveData<List<GamesResponse>> apiResponseLiveData;


    public GameViewModel(@NonNull Application application) {
        super(application);
        gameRepository =new GameRepository(application);
        allgames= gameRepository.getAllGames();


    }

    public void insert(GameSaved gameSaved){
        gameRepository.insert(gameSaved);
    }

    public void update(GameSaved gameSaved){
        gameRepository.update(gameSaved);
    }

    public void delete(GameSaved gameSaved){
        gameRepository.delete(gameSaved);
    }

    public LiveData<List<GameSaved>> getAllgames(){
        return allgames;
    }

    public LiveData<List<GameSaved>> getGame(String chars){
        LiveData<List<GameSaved>> game = gameRepository.getGame(chars);
        return game;
    }

    public LiveData<List<GamesResponse>> getApiResponseLiveData(String query)
    {
        Map<String,String> parameters=new HashMap<>();
        parameters.put("searchWords",query);
        parameters.put("country","ca");

        this.apiResponseLiveData = gameRepository.getGames(parameters);
        return apiResponseLiveData;
    }

    public LiveData<List<GamesResponse>> getComingSoonLiveData(String query)
    {
        Map<String,String> parameters=new HashMap<>();
        parameters.put("searchWords",query);
        parameters.put("country","ca");

        this.apiResponseLiveData = gameRepository.getComingSoon(parameters);
        return apiResponseLiveData;
    }


}
