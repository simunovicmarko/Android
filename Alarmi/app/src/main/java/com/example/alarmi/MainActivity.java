package com.example.alarmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SlidingUpPanelLayout layout  = findViewById(R.id.layout);
        TextView lableCasMain = (TextView)findViewById(R.id.casMainLabel);
        lableCasMain.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")).toString());



        layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED){
                    Toast.makeText(MainActivity.this, "Expanded", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void odpriPanel(View v){
        SlidingUpPanelLayout layout  = findViewById(R.id.layout);
        layout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }


}