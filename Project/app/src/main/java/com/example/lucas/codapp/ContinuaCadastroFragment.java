package com.example.lucas.codapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContinuaCadastroFragment extends Fragment {


    public ContinuaCadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_continua_cadastro, container, false);
        final Spinner tipoSanguineo = (Spinner) view.findViewById(R.id.spTipoSanguineo);
        Button confirma = (Button) view.findViewById(R.id.btnConfirma);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipoSanguineo,
                                        R.layout.support_simple_spinner_dropdown_item);
        tipoSanguineo.setAdapter(adapter);
        final String usuario = this.getArguments().getString("usuario");
        final EditText dataNasc = (EditText) view.findViewById(R.id.edtData);
        final EditText cep = (EditText) view.findViewById(R.id.edtCep);
        final RadioGroup sexo = (RadioGroup) view.findViewById(R.id.rdbSexo);


        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference(usuario);
                ref.child("sangue").setValue(tipoSanguineo.getSelectedItem().toString());
                ref.child("nascimento").setValue(dataNasc.getText().toString());
                ref.child("cep").setValue(cep.getText().toString());
                ref.child("sexo").setValue(((RadioButton)view.findViewById(sexo.getCheckedRadioButtonId())).getText());
                ((CadastroActivity) getActivity()).finish();
            }


        });
        return view;
    }
}
