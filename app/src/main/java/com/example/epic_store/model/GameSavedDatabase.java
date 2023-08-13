package com.example.epic_store.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GameSaved.class},version = 1)
public abstract class GameSavedDatabase extends RoomDatabase {

    private static GameSavedDatabase instance;
    public abstract GameSavedDao gameSavedDao();


    public static synchronized GameSavedDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),GameSavedDatabase.class,"GameSaved").build();
        }
        return instance;
    }


}
