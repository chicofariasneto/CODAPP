package com.example.lucas.codapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UsuariosCadastradosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_cadastrados);

        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frame_menu, new UsuariosCadFragment()).
                commit();
    }
}
