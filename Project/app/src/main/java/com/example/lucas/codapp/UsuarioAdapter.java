package com.example.lucas.codapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private final Context context;
    private final ArrayList<Usuario> users;

    public UsuarioAdapter (Context context, ArrayList<Usuario> users) {
        super(context, R.layout.linha, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);

        TextView nomeUser = (TextView) rowView.findViewById(R.id.txtNomeUsuario);
        TextView nickUser = (TextView) rowView.findViewById(R.id.txtNickUsuario);

        nomeUser.setText(users.get(position).getNome());
        nickUser.setText(users.get(position).getUsuario());

        return rowView;
    }
}
