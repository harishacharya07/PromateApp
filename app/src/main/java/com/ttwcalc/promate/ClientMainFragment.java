package com.ttwcalc.promate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ClientMainFragment extends Fragment {

    private Button clientButton;
    private View view;
    private EditText enterLogin;
    private  static String LOGIN = "userRole";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_client, container, false);
        clientButton = view.findViewById(R.id.details_button);
        enterLogin = view.findViewById(R.id.credentials);

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = enterLogin.getText().toString().trim();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login",
                        Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(LOGIN, login);
                editor.apply();

                Intent intent = new Intent(getContext(), ClientsDetailsActivity.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        });
        return view;
    }
}
