package com.example.lucas.codapp;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginUserFragment extends Fragment {

    private EditText usuario, senha;
    private Button btnLogin;
    private String nick;

    public LoginUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_user, container, false);

        final TextView info = (TextView) view.findViewById(R.id.txtInfo);
        usuario = (EditText) view.findViewById(R.id.edtLoginUsuario);
        senha = (EditText) view.findViewById(R.id.edtLoginSenha);

        nick = ((MainActivity) getActivity()).getNickUser();

        btnLogin = (Button) view.findViewById(R.id.btnLoginEntrar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usuario.getText().toString().length() > 0) {
                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference(usuario.getText().toString());
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.hasChildren())
                                Toast.makeText(getContext(), "USUÁRIO NÃO CADASTRADO!", Toast.LENGTH_SHORT).show();
                            else {
                                if (dataSnapshot.child("senha").getValue().toString().equals(senha.getText().toString().trim())) {
                                    Toast.makeText(getContext(), "SEJA BEM-VINDO!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), MenuActivity.class);
                                    intent.putExtra("usuario", usuario.getText().toString());
                                    senha.setText("");
                                    startActivity(intent);
                                    info.setText("");

                                    if (dataSnapshot.child("cep").getValue().toString().length() == 0) {
                                        Intent novaIntent = new Intent(getActivity(), CadastroActivity.class);
                                        novaIntent.putExtra("usuario", usuario.getText().toString());
                                        novaIntent.putExtra("REQUEST_CODE", 1);
                                        startActivity(novaIntent);
                                    }
                                    usuario.setText("");
                                } else
                                    Toast.makeText(getContext(), "SENHA INCORRETA!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError firebaseError) {
                            Toast.makeText(getContext(), "ERRO!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return view;
    }
}