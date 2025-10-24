package com.example.catapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private FavoriteManager favoriteManager;
    private ArrayList<Cat> favList = new ArrayList<>();
    private Button btnVoltar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorites);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        favoriteManager = new FavoriteManager(this);
        Set<String> favoritos = favoriteManager.getFavorites();

        recyclerView = findViewById(R.id.recyclerFavorites);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));


        int i = 1;
        for (String url : favoritos) {
            favList.add(new Cat(url, "Favorito " + i));
            i++;
        }

        adapter = new Adapter(favList, favoritos);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onItemLongClick(int position) {

            }

            @Override
            public void onFavoriteClick(int position) {
                Cat cat = favList.get(position);
                favoriteManager.removeFavorite(cat.getUrl());
                Toast.makeText(FavoritesActivity.this, "Removido dos favoritos", Toast.LENGTH_SHORT).show();


                adapter.removeItem(position);
                adapter.updateFavorites(favoriteManager.getFavorites());
            }
        });

        Button btnVoltar2 = findViewById(R.id.btnVoltar2);
        btnVoltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}