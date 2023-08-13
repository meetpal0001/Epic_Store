package com.example.epic_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.epic_store.R;
import com.example.epic_store.model.GameSaved;

import java.util.List;

public class SavedGamesAdapter extends RecyclerView.Adapter<SavedGamesAdapter.SavedGamesHolder>{

    private List<GameSaved> games;

    private Context context;

    private SavedGamesAdapter.OnItemClickListener listener;


    public SavedGamesAdapter(Context context){
        this.context=context;
    }


    @NonNull
    @Override
    public SavedGamesAdapter.SavedGamesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item,parent,false);
        return new SavedGamesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedGamesAdapter.SavedGamesHolder holder, int position) {
        GameSaved currentGame=games.get(position);

        holder.title.setText(currentGame.getTitle());
        holder.price.setText(currentGame.getDiscPrice());
        Glide.with(context).load(currentGame.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (games==null){
            return 0;
        }
        return games.size();
    }

    public void setSavedGames(List<GameSaved> games){
        this.games=games;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(SavedGamesAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(GameSaved game);
    }

    public class SavedGamesHolder extends RecyclerView.ViewHolder {

        TextView price;
        TextView title;
        ImageView imageView;


        public SavedGamesHolder(@NonNull View itemView) {
            super(itemView);

            price=itemView.findViewById(R.id.price);
            title=itemView.findViewById(R.id.titlegame_);
            imageView=itemView.findViewById(R.id.game);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        GameSaved game = games.get(position);
                        listener.onItemClick(game);
                    }
                }
            });
        }
    }
}
