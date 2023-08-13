package com.example.epic_store.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.epic_store.adapter.GamesAdapter;
import com.example.epic_store.model.GamesResponse;
import com.example.epic_store.retrofit.GamesResponseAPI;
import com.example.epic_store.R;
import com.example.epic_store.viewmodel.GameViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnSaleActivity extends AppCompatActivity{


    RecyclerView recyclerView;
    GamesAdapter gamesAdapter;

    private GameViewModel gameViewModel;
    static GamesResponse game_Selected;

    private  List<GamesResponse> gamesResponses=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_sale);

        recyclerView=findViewById(R.id.gamesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);    //Generates the app bar
        setSupportActionBar(myToolbar);

        gameViewModel =new ViewModelProvider(this).get(GameViewModel.class);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Inflate the Menu
        getMenuInflater().inflate(R.menu.menu2,menu);

        // Associate searchable configuration with the SearchView
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {



                gamesAdapter=new GamesAdapter(OnSaleActivity.this, gamesResponses);
                recyclerView.setAdapter(gamesAdapter);


                gameViewModel.getApiResponseLiveData(query).observe(OnSaleActivity.this, apiResponse -> {
                    if(apiResponse != null)
                    {
                        gamesResponses.clear();
                        gamesResponses.addAll(apiResponse);
                        gamesAdapter.notifyDataSetChanged();
                    }
                });

                gamesAdapter.setOnItemClickListener(new GamesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(GamesResponse game) {
                        game_Selected=game;
                        Intent intent = new Intent(OnSaleActivity.this, Game.class);
                        startActivity(intent);
                    }
                });


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId()==R.id.savedGames) {
            Intent intent = new Intent(OnSaleActivity.this, SavedGames.class);
            startActivity(intent);
            return true;
        }
        else{
        return super.onOptionsItemSelected(item);
        }

    }

}