package com.example.rodney.fatecpin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rodney.fatecpin.Modelos.PINS;
import com.example.rodney.fatecpin.Modelos.PINresposta;
import com.example.rodney.fatecpin.PINapi.PINapiService;

import java.text.ParseException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PIN";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaPINadapter listaPINadapter;

    private int offset;

    private boolean aptoParaCarregar;

    private TextView mTextMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        try {
            listaPINadapter = new ListaPINadapter(this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(listaPINadapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCarregar){
                        if ((visibleItemCount + pastVisibleItems)>= totalItemCount){
                            Log.i(TAG, " Fim dos itens.");
                            aptoParaCarregar = false;
                            offset += 15;
                            obterDados(offset);
                        }

                    }
                }
            }
        });

        retrofit = new Retrofit.Builder().baseUrl("https://fatecpin.info/api/public/").addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCarregar = true;
        offset = 0;

        obterDados(offset);
    }
    private void obterDados(int offset){

        PINapiService service = retrofit.create(PINapiService.class);
        Call<PINresposta> PINrespostaCall = service.obterListaPIN(15, offset);

        PINrespostaCall.enqueue(new Callback<PINresposta>() {
            @Override
            public void onResponse(Call<PINresposta> call, Response<PINresposta> response) {
                aptoParaCarregar = true;
                if (response.isSuccessful()){

                    PINresposta PINresposta = response.body();
                    ArrayList<PINS> listaPin = PINresposta.getResults();

                    listaPINadapter.adicionarListaPIN(listaPin);

                }else{

                    Log.e(TAG, " onResponse: " + response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<PINresposta> call, Throwable t) {
                aptoParaCarregar = true;

                Log.e(TAG, " onFailure: " + t.getMessage());

            }
        });
    }
}
