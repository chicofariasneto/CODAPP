package com.example.lucas.codapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class CadastreFragment extends Fragment {
    private EditText nome, cpf, usuario, senha;

    interfaceConfirmaCadastro mListener;

    public CadastreFragment() {
        // Required empty public constructor
    }

    @Override
    public void  onAttach (Context activity) {
        super.onAttach(activity);
        if (!(activity instanceof interfaceConfirmaCadastro)) {
            throw new RuntimeException("A activity deve implementar a interface!");
        }
        mListener = (interfaceConfirmaCadastro) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cadastre, container, false);
        Button confima = (Button) view.findViewById(R.id.btnCadConfirma);
        nome = (EditText) view.findViewById(R.id.edtNome);
        cpf = (EditText) view.findViewById(R.id.edtCPF);
        usuario = (EditText) view.findViewById(R.id.edtCadUsuario);
        senha = (EditText) view.findViewById(R.id.edtCadSenha);

        confima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference(usuario.getText().toString());
                final Usuario user = new Usuario(
                        nome.getText().toString(),
                        usuario.getText().toString(),
                        cpf.getText().toString(),
                        senha.getText().toString());

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.hasChildren()) {
                            ref.setValue(user);
                            Toast.makeText(getContext(), "USUÁRIO CADASTRADO COM SUCESSO!", Toast.LENGTH_SHORT).show();

                            if (mListener != null) {
                                //Toast.makeText(getContext(), "SAINDO", Toast.LENGTH_SHORT).show();
                                mListener.setConfirmacaoDeCadastro(true, usuario.getText().toString());
                                //getActivity().getSupportFragmentManager().popBackStack();
                            }
                        } else
                            Toast.makeText(getContext(), "USUÁRIO JÁ ESTA CADASTRADO!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError firebaseError) {
                        Toast.makeText(getContext(), "PROBLEMA DE CONEXÃO!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        return view;
    }
}
