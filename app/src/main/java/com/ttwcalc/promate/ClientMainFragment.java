package com.ttwcalc.promate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ClientMainFragment extends Fragment {

    private Button clientButton;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_client, container, false);
        clientButton = view.findViewById(R.id.details_button);

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ClientsDetailsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
