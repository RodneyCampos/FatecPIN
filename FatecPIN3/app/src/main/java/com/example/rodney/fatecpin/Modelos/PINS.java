package com.example.rodney.fatecpin.Modelos;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rodney on 22/11/2017.
 */

public class PINS {
    private String id;
    private String descricao;
    private String data_postagem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {

        return data_postagem;
    }

    public void setData(String data_postagem) {

        this.data_postagem = data_postagem;
    }
}
