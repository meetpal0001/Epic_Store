package com.example.epic_store.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.epic_store.model.GameSaved;

import java.util.List;

@Dao
public interface GameSavedDao {

    @Insert
    void insert(GameSaved gameSaved);

    @Update
    void update(GameSaved gameSaved);

    @Delete
    void delete(GameSaved gameSaved);

    @Query("Select * from GameSaved")
    LiveData<List<GameSaved>> getAllGames();

    @Query("Select * from GameSaved where id=:chars")
    LiveData<List<GameSaved>> getGame(String chars);
}
