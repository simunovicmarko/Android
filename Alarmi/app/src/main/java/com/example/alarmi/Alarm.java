package com.example.alarmi;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Alarm {
    private int ura, minuta;
    private int[] dneviVTednu;
    private String naziv;
    private String zvok;
    private Boolean snooze;

    public int getUra() {
        return ura;
    }

    public void setUra(int ura) {
        this.ura = ura;
    }

    public int getMinuta() {
        return minuta;
    }

    public void setMinuta(int minuta) {
        this.minuta = minuta;
    }

    public int[] getDneviVTednu() {
        return dneviVTednu;
    }

    public void setDneviVTednu(int[] dneviVTednu) {
        this.dneviVTednu = dneviVTednu;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZvok() {
        return zvok;
    }

    public void setZvok(String zvok) {
        this.zvok = zvok;
    }

    public Boolean getSnooze() {
        return snooze;
    }

    public void setSnooze(Boolean snooze) {
        this.snooze = snooze;
    }


    public Alarm(int ura,int minuta, int[] dneviVTednu, String naziv, String zvok, Boolean snooze) {
        this.ura = ura;
        this.minuta = minuta;
        this.dneviVTednu = dneviVTednu;
        this.naziv = naziv;
        this.zvok = zvok;
        this.snooze = snooze;
    }

    public Alarm(){
        this.ura =  Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("HH")));
        this.minuta =  Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("mm")));
        dneviVTednu = null;
        naziv = "Alarm";
        zvok = null;
        snooze = true;
    }


}
