package com.example.lucas.codapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        usuario = getIntent().getStringExtra("usuario");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(usuario);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    TextView nome = (TextView) findViewById(R.id.txtNome);
                    TextView usuario = (TextView) findViewById(R.id.txtNomeUsuar);
                    TextView cpf = (TextView) findViewById(R.id.txtCPF);
                    TextView cep = (TextView) findViewById(R.id.txtCEP);
                    TextView nascimento = (TextView) findViewById(R.id.txtNascimento);
                    TextView sangue = (TextView) findViewById(R.id.txtSangue);
                    TextView sexo = (TextView) findViewById(R.id.txtSexo);

                    nome.setText(dataSnapshot.child("nome").getValue().toString());
                    usuario.setText(dataSnapshot.child("usuario").getValue().toString());
                    cpf.setText(dataSnapshot.child("cpf").getValue().toString());
                    cep.setText(dataSnapshot.child("cep").getValue().toString());
                    nascimento.setText(dataSnapshot.child("nascimento").getValue().toString());
                    sangue.setText(dataSnapshot.child("sangue").getValue().toString());
                    sexo.setText(dataSnapshot.child("sexo").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}