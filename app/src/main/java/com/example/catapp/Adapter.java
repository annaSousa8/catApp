package com.example.catapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Set;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<Cat> list;
    private OnItemClickListener listener;
    private Set<String> favorites;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
        void onFavoriteClick(int position);
    }

    public Adapter(ArrayList<Cat> list, Set<String> favorites) {
        this.list = list;
        this.favorites = favorites;
    }

    public void updateFavorites(Set<String> newFavorites) {
        this.favorites = newFavorites;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cat_model, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cat cat = list.get(position);
        holder.text2.setText(cat.getText2());

        Glide.with(holder.itemView.getContext())
                .load(cat.getUrl())
                .placeholder(R.drawable.kitty)
                .into(holder.imgCat);

        if (favorites != null && favorites.contains(cat.getUrl())) {
            holder.imgFavorite.setImageResource(R.drawable.starfilled);
        } else {
            holder.imgFavorite.setImageResource(R.drawable.star);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFavorite = itemView.findViewById(R.id.imgFavorite);
        TextView text2;
        ImageView imgCat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCat = itemView.findViewById(R.id.imgCat);
            text2 = itemView.findViewById(R.id.text2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemLongClick(position);
                            return true;
                        }
                    }
                    return false;
                }
            });

            imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onFavoriteClick(position);
                        }
                    }
                }
            });
        }
    }
}
