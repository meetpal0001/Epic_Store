package com.example.epic_store.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.epic_store.model.GamesResponse;
import com.example.epic_store.R;
import com.example.epic_store.model.GameSaved;
import com.example.epic_store.viewmodel.GameViewModel;

import java.util.List;

public class Game extends AppCompatActivity{


    ImageView gamPic;

    TextView title;
    TextView description;
    TextView disPrice;
    TextView orgPrice;
    TextView publisher;
    TextView date;
    TextView url;

    Button back;
    Button save;

    private GameViewModel gameViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        title=findViewById(R.id.game_title);
        gamPic=findViewById(R.id.imageView);
        description=findViewById(R.id.description);
        disPrice=findViewById(R.id.discPrice);
        orgPrice=findViewById(R.id.orgPrice);
        publisher=findViewById(R.id.pub);
        date=findViewById(R.id.relDate);
        url=findViewById(R.id.url);

        GamesResponse game= OnSaleActivity.game_Selected;

        Glide.with(this).load(game.getImageURL().get(0).getUrl()).into(gamPic);

        title.setText(game.getTitle());
        description.setText(game.getDescription());
        disPrice.setText("Discounted Price: "+game.getPrice().getTotalPrice().getFmtPrice().getDiscountPrice());
        orgPrice.setText("Original Price: "+game.getPrice().getTotalPrice().getFmtPrice().getOriginalPrice());
        publisher.setText("Publisher: "+game.getPublisherName());
        date.setText("ReleaseDate: "+game.getReleaseDate());

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl(game.getUrl());
            }
        });

        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save=findViewById(R.id.save);

        gameViewModel =new ViewModelProvider(this).get(GameViewModel.class);

        LiveData<List<GameSaved>> viewModelData = gameViewModel.getGame(game.getId());
        viewModelData.observe(this, new Observer<List<GameSaved>>() {
            @Override
            public void onChanged(List<GameSaved> gamesData) {
                //Handle the Games List here.
                if (gamesData.isEmpty()){
                    save.setEnabled(true);
                    save.setText("Save");
                }
                else{
                    save.setEnabled(false);
                    save.setText("Saved");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameSaved data=new GameSaved(game.getId(), game.getTitle(), game.getDescription(), game.getImageURL().get(0).getUrl(), game.getPrice().getTotalPrice().getFmtPrice().getDiscountPrice(), game.getPrice().getTotalPrice().getFmtPrice().getOriginalPrice(), game.getPublisherName(), game.getReleaseDate(), game.getUrl());
                gameViewModel.insert(data);
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
                save.setEnabled(false);
                save.setText("Saved");
            }
        });

    }

    private void gotoUrl(String url) {
        Uri uri=Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}