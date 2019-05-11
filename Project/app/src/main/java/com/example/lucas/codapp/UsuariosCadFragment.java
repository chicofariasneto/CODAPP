package com.example.lucas.codapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsuariosCadFragment extends Fragment {

    public UsuariosCadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_usuarios_cad, container, false);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Usuario> users1 = new ArrayList<>();
                Usuario user;
                for(DataSnapshot sp: dataSnapshot.getChildren()) {
                    String nome = sp.child("nome").getValue().toString();
                    String usuario = sp.child("usuario").getValue().toString();
                    String cpf = sp.child("cpf").getValue().toString();
                    String cep = sp.child("cep").getValue().toString();
                    String nascimento = sp.child("nascimento").getValue().toString();
                    String sangue = sp.child("sangue").getValue().toString();
                    String sexo = sp.child("sexo").getValue().toString();
                    String senha = sp.child("sexo").getValue().toString();
                    user = new Usuario(nome, usuario, cpf, senha);

                    user.setCEP(cep);
                    user.setNascimento(nascimento);
                    user.setSexo(sexo);
                    user.setSangue(sangue);

                    users1.add(user);

                }

                final ArrayList<Usuario> users = users1;
                ListView lista = (ListView) view.findViewById(R.id.lvUsers);
                ArrayAdapter adapter = new UsuarioAdapter(getActivity(), users);

                lista.setAdapter(adapter);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent novaIntent = new Intent(getActivity(), PerfilActivity.class);
                        novaIntent.putExtra("usuario", users.get(position).getUsuario());
                        startActivity(novaIntent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
