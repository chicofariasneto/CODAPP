package com.example.lucas.codapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class AnotacaoAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> lista;

    public AnotacaoAdapter(Context context, ArrayList<String> lista) {
        super(context, android.R.layout.simple_list_item_1, lista);
        this.context = context;
        this.lista = lista;
    }
}
