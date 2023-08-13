package com.example.epic_store.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.epic_store.R;
import com.example.epic_store.model.GameSaved;

public class SavedView extends AppCompatActivity {

    ImageView gamPic;

    TextView title;
    TextView description;
    TextView disPrice;
    TextView orgPrice;
    TextView publisher;
    TextView date;
    TextView url;

    Button back;
    Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_view);

        title=findViewById(R.id.game_title2);
        gamPic=findViewById(R.id.imageView2);
        description=findViewById(R.id.description2);
        disPrice=findViewById(R.id.discPrice2);
        orgPrice=findViewById(R.id.orgPrice2);
        publisher=findViewById(R.id.pub2);
        date=findViewById(R.id.relDate2);
        url=findViewById(R.id.url2);

        GameSaved game= SavedGames.selected;

        Glide.with(this).load(game.getImageUrl()).into(gamPic);

        title.setText(game.getTitle());
        description.setText(game.getDescription());
        disPrice.setText("Discounted Price: "+game.getDiscPrice());
        orgPrice.setText("Original Price: "+game.getOrgPrice());
        publisher.setText("Publisher: "+game.getPublisher());
        date.setText("ReleaseDate: "+game.getRelDate());

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl(game.getUrl());
            }
        });

        back=findViewById(R.id.back2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        remove=findViewById(R.id.remove);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void gotoUrl(String url) {
        Uri uri=Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}