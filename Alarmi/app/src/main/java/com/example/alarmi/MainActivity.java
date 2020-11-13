package com.example.alarmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.math.MathContext;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    static Vector<Alarm> alarmi = new Vector<Alarm>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SlidingUpPanelLayout layout = findViewById(R.id.layout);


        TextView cas = (TextView) findViewById(R.id.cas);
        cas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        cas.setText(hourOfDay + ":" + minute);
                    }
                }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, android.text.format.DateFormat.is24HourFormat(MainActivity.this));
                timePickerDialog.show();
            }
        });

        layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {

                }
            }
        });

    }


    public void odpriPanel(View v) {
        SlidingUpPanelLayout layout = findViewById(R.id.layout);
        layout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
//        //View alOkno = findViewById(v.getId());
//
//
        //Okno dodajanje urejanje
        TextView tvCas = findViewById(R.id.cas);
        EditText editTextIme = findViewById(R.id.EditTextIme);
        Switch snooze = findViewById(R.id.switchDremez);

        //Okno alarm relative
        TextView tvCasR = v.findViewById(R.id.tvCas);
        TextView tvImeR = v.findViewById(R.id.tvIme);
        Switch switchR = v.findViewById(R.id.switchCompatStanje);


        tvCas.setText(tvCasR.getText().toString());
        editTextIme.setText(tvImeR.getText().toString());
        snooze.setChecked(switchR.isChecked());


    }




    public void dodajAlarm(View v) {


        TextView castv = (TextView) findViewById(R.id.cas);
        EditText editTextIme = (EditText) findViewById(R.id.EditTextIme);
        Switch snooze = (Switch) findViewById(R.id.switchDremez);

        Alarm alarm = new Alarm(castv.getText().toString(), null, editTextIme.getText().toString(), "Kr Neki", snooze.isChecked());
        alarmi.add(alarm);
        ustvariAlarmOknce(alarmi.lastElement());
        SlidingUpPanelLayout layout = findViewById(R.id.layout);
        layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

    }



    private void ustvariAlarmOknce(Alarm alarm) {
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = null;
        v = layoutInflater.inflate(R.layout.okno_alarm_relative, null);
        v.setId(View.generateViewId());

        LinearLayout layout = (LinearLayout) findViewById(R.id.containerZaAlarme);
        layout.addView(v);

        View parent = findViewById(v.getId());
        TextView tvCas = parent.findViewById(R.id.tvCas);

        String neki = tvCas.getText().toString();
        String cas = alarm.getCas();
        tvCas.setText(cas);
        TextView tvIme = (TextView) parent.findViewById(R.id.tvIme);
        tvIme.setText(alarm.getNaziv());
        Switch switchCompat = (Switch) parent.findViewById(R.id.switchCompatStanje);
        if (alarm.getSnooze()) {
            switchCompat.setChecked(true);
        } else
            switchCompat.setChecked(false);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odpriPanel(v);
            }
        });


//        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.containerZaAlarme);
//        insertPoint.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


}