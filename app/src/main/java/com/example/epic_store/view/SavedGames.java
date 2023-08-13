package com.example.epic_store.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.example.epic_store.model.GameSaved;
import com.example.epic_store.R;
import com.example.epic_store.adapter.SavedGamesAdapter;
import com.example.epic_store.viewmodel.GameViewModel;

import java.util.List;



public class SavedGames extends AppCompatActivity {

    private GameViewModel gameViewModel;
    private RecyclerView recyclerView;

    static GameSaved selected;

    private SavedGamesAdapter savedGamesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar3);    //Generates the app bar
        setSupportActionBar(myToolbar);

        recyclerView=findViewById(R.id.savedView);

        savedGamesAdapter=new SavedGamesAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(savedGamesAdapter);

        gameViewModel =new ViewModelProvider(this).get(GameViewModel.class);

        savedGamesAdapter.setOnItemClickListener(new SavedGamesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GameSaved game) {
                selected=game;
                Intent intent = new Intent(SavedGames.this, SavedView.class);
                addGameLauncher.launch(intent);
            }
        });

        gameViewModel.getAllgames().observe(this, new Observer<List<GameSaved>>() {
            @Override
            public void onChanged(List<GameSaved> gameSaveds) {
                savedGamesAdapter.setSavedGames(gameSaveds);
            }
        });
    }

    private ActivityResultLauncher<Intent> addGameLauncher = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result->{
                if(result.getResultCode() == RESULT_OK)
                {

                    gameViewModel.delete(selected);
                    Toast.makeText(this, "Removed", Toast.LENGTH_LONG).show();

                }

            });
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Inflate the Menu
        getMenuInflater().inflate(R.menu.menu,menu);


        return true;
    }
}