package com.example.rodney.fatecpin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rodney.fatecpin.Modelos.PINS;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rodney on 22/11/2017.
 */

public class ListaPINadapter extends RecyclerView.Adapter<ListaPINadapter.ViewHolder>{

    private ArrayList<PINS> dataset;
    private Context context;

    public ListaPINadapter(Context context) throws ParseException {
        this.context = context;
        dataset = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pin, parent, false);

        return new ViewHolder(view);
    }





    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PINS p = dataset.get(position);
        String datacorreta = p.getData();
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-YYYY");


        Date result1 = null;
        try {
            result1 = df1.parse(datacorreta);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        df1.applyPattern("dd-MM-YYYY");
        String novaData = df1.format(result1);


        //tentando fazer a data sair correta, não sei onde estou errando, o getData está correto mas o novaData sempre sai errado, não sei o motivo
        holder.nomeTextView.setText("\n" + p.getDescricao() + "\n" + novaData + "\n");






    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPIN(ArrayList<PINS> listaPIN){
        dataset.addAll(listaPIN);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nomeTextView;


        public ViewHolder(View itemView){
            super(itemView);

            nomeTextView = (TextView) itemView.findViewById(R.id.nomeTextView);

        }
    }


}
