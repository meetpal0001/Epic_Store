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
import com.example.epic_store.model.GamesResponse;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    List<GamesResponse> games;
    private Context context;
    private OnItemClickListener listener;
    public GamesAdapter(Context context,List<GamesResponse> games){
        this.games=games;
        this.context=context;
    }
    @NonNull
    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.ViewHolder holder, int position) {
        GamesResponse game=games.get(position);
        holder.price.setText(game.getPrice().getTotalPrice().getFmtPrice().getDiscountPrice());
        holder.title.setText(game.getTitle());
        Glide.with(context).load(game.getImageURL().get(0).getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (games==null){
            return 0;
        }
        return games.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(GamesResponse game);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView price;
        TextView title;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.price);
            title=itemView.findViewById(R.id.titlegame_);
            imageView=itemView.findViewById(R.id.game);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        GamesResponse game = games.get(position);
                        listener.onItemClick(game);
                    }
                }
            });
        }
    }
}
