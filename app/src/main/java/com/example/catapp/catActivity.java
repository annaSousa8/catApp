package com.example.catapp;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class catActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<Cat> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int quantidade = intent.getIntExtra("quantidade", 0);
        recyclerView = findViewById(R.id.recyclerViweCat);
        Adapter adapter = new Adapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheCatApiService api = retrofit.create(TheCatApiService.class);

        for (int i = 0; i < quantidade; i++) {
            Call<List<Cat>> call = api.getRandomCatImage();
            int finalI = i;
            call.enqueue(new Callback<List<Cat>>() {
                @Override
                public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                    if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                        Cat cat = response.body().get(0);
                        cat.setText2("Gatinho " + (finalI+1));
                        list.add(cat);
                        adapter.notifyItemInserted(list.size() - 1);
                    }
                }

                @Override
                public void onFailure(Call<List<Cat>> call, Throwable t) {
                    Toast.makeText(catActivity.this, "Erro ao carregar imagem", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }



}