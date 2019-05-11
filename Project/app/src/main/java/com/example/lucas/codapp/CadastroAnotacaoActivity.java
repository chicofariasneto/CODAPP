package com.example.lucas.codapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastroAnotacaoActivity extends AppCompatActivity {
    private Button btn;
    private TextView txt;
    private Calendar dataHora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anotacao);

        txt = (TextView) findViewById(R.id.edtAnotacaoCadas);
        // Aqui será preciso pegar o toString do PlainText, salvar no firebase,
        // e o button ta progamado para voltar quando clicado.
        btn = (Button) findViewById(R.id.btnCadasAddAnot);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anotacao = txt.getText().toString();

                DatabaseReference ref = FirebaseDatabase.getInstance()
                        .getReference(getIntent().getStringExtra("usuario"));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SSS");

                dataHora = Calendar.getInstance();
                String idAnotacao = format.format(dataHora.getTime());
                ref.child("Anotacoes").child(idAnotacao).setValue(anotacao);
                finish();
            }
        });
    }
}
