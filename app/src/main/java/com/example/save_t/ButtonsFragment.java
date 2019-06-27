package com.example.save_t;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ButtonsFragment extends Fragment {
    private TextView room_view;
    private TextView Temperature;
    private String room;
    private double min = 19.0;
    private double max = 22.0;
    private double random = (Math.random() * ((max - min))+ min );
    private DecimalFormat df = new DecimalFormat("0.0");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buttons,container,false);
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            room = bundle.getString("ROOM");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        room_view = getView().findViewById(R.id.room);
        room_view.setText(room);

        Temperature = getView().findViewById(R.id.temp);
        df.setRoundingMode(RoundingMode.DOWN);
        Temperature.setText("Temp: " + df.format(random) + " C");
    }
}
