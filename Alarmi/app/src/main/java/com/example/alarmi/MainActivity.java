package com.example.alarmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.FocusFinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
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




        SlidingUpPanelLayout layout = findViewById(R.id.layout);
        EditText ure = (EditText) findViewById(R.id.Ure);
        ure.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH")).toString());

        EditText minute = (EditText) findViewById(R.id.Minute);
        minute.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("mm")).toString());

        layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    Toast.makeText(MainActivity.this, "Expanded", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void odpriPanel(View v) {
        SlidingUpPanelLayout layout = findViewById(R.id.layout);
        layout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }


    public void dodajAlarm(View v) {
        TextView h = (TextView) findViewById(R.id.Ure);
        TextView min = (TextView) findViewById(R.id.Minute);

        int ura = Integer.parseInt(h.getText().toString());
        int minuta = Integer.parseInt(min.getText().toString());


        Alarm alarm = new Alarm(ura, minuta, null, "Prvi", "Kr Neki", true);
        ustvariAlarmOknce(alarm);

    }

    private void ustvariAlarmOknce(Alarm alarm) {
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = null;
        v = layoutInflater.inflate(R.layout.okno_alarm_relative, null);




        LinearLayout layout = (LinearLayout) findViewById(R.id.containerZaAlarme);
        layout.addView(v);

        TextView tvCas = (TextView) findViewById(R.id.tvCas);
        String cas = Integer.toString(alarm.getUra()) + " : " + Integer.toString(alarm.getMinuta());
        tvCas.setText(cas);

        TextView tvIme = (TextView) findViewById(R.id.tvIme);
        tvIme.setText(alarm.getNaziv());
        Switch switchCompat = (Switch) findViewById(R.id.switchCompatStanje);
        if (alarm.getSnooze()){
            switchCompat.setChecked(true);
        }
        else
            switchCompat.setChecked(false);


//        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.containerZaAlarme);
//        insertPoint.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


}