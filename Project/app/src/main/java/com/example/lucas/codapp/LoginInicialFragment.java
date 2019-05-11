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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginInicialFragment extends Fragment {

    private Boolean usuarioCadastrado = false;
    private String usuario;
    private int REQUEST_CODE_SEGUNDA = 1;
    private TextView informacao;

    public LoginInicialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_inicial, container, false);
        TextView cadastre = (TextView) view.findViewById(R.id.txtCadastre);
        Button login = (Button) view.findViewById(R.id.btnLogin);
        informacao = (TextView) view.findViewById(R.id.txtInfo);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create new fragment and transaction
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.frame_container, new LoginUserFragment());
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

        cadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastroActivity.class);
                intent.putExtra("REQUEST_CODE", 0);
                startActivityForResult(intent, REQUEST_CODE_SEGUNDA);
                //Toast.makeText(getContext(), "USER INVALIDO!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == REQUEST_CODE_SEGUNDA) {
            if (data.hasExtra("retorno")) {
                usuarioCadastrado = data.getExtras().getBoolean("retorno");
                //usuario = data.getExtras().getString("nickDoUsuario");
                if (usuarioCadastrado) {
                    //Toast.makeText(getContext(), "ENTREI AKI", Toast.LENGTH_SHORT).show();
                    ((MainActivity) getActivity()).setNickUser(usuario);
                    Fragment novoFragmento = new LoginUserFragment();
                    android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container, novoFragmento);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        }
    }
}
