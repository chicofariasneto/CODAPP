package com.example.lucas.codapp;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity implements interfaceConfirmaCadastro{

    private boolean userCadastrado = false;
    private String nomeDoUsuario;
    private int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Intent intent = getIntent();
        REQUEST_CODE = (int) intent.getSerializableExtra("REQUEST_CODE");

        if (savedInstanceState == null && REQUEST_CODE == 0) {
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.fragmentCadastre, new CadastreFragment()).
                    commit();
        }
        else if  (savedInstanceState == null && REQUEST_CODE >= 1){
            Bundle bundle = new Bundle();
            bundle.putString("usuario", getIntent().getStringExtra("usuario"));

            ContinuaCadastroFragment frag = new ContinuaCadastroFragment();
            frag.setArguments(bundle);
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.fragmentCadastre, frag).
                    commit();
        }
    }

    @Override
    public void setConfirmacaoDeCadastro(Boolean cadastro, String usuario) {
        userCadastrado = cadastro;
        nomeDoUsuario = usuario;
        finish();
    }

    @Override
    public void finish () {
        Intent dados = new Intent();
        dados.putExtra("retorno", userCadastrado);
        dados.putExtra("nickDoUsuario", nomeDoUsuario);
        setResult(RESULT_OK, dados);
        super.finish();
    }

    @Override
    public void onBackPressed() {
        if (REQUEST_CODE == 0)
            super.onBackPressed();
    }
}