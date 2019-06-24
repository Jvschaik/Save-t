package com.example.save_t;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    private Button btnLiving;
    private Button btnSleep;
    private Button btnKitchen;
    private Button btnBath;
    private Button btnKids;
    private Button btnLoft;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLiving = getView().findViewById(R.id.woonkamer);
        btnSleep = getView().findViewById(R.id.slaapkamer);
        btnKitchen = getView().findViewById(R.id.keuken);
        btnBath = getView().findViewById(R.id.badkamer);
        btnKids = getView().findViewById(R.id.kinderkamer);
        btnLoft = getView().findViewById(R.id.zolder);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String buttonText = b.getText().toString();

                moveToRoom(buttonText);
            }
        };

        btnLiving.setOnClickListener(listener);
        btnSleep.setOnClickListener(listener);
        btnKitchen.setOnClickListener(listener);
        btnBath.setOnClickListener(listener);
        btnKids.setOnClickListener(listener);
        btnLoft.setOnClickListener(listener);
    }

    private void moveToRoom(String btnText) {

        Bundle arguments = new Bundle();
        arguments.putString("ROOM", btnText);
        Fragment ButtonsFragment = new ButtonsFragment();
        ButtonsFragment.setArguments(arguments);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, ButtonsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
