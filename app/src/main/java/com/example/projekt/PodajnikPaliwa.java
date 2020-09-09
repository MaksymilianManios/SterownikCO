package com.example.projekt;


import android.support.v4.app.INotificationSideChannel;

public class PodajnikPaliwa {
    public static final int UZUPELNIAJACA_PORCJA_PALIWA = 100;
    public static final int DAWKA_PALIWA = 30;
    private Boolean pracaPodajnika;

    public PodajnikPaliwa() {
        this.pracaPodajnika = false;
    }

    public Boolean getPracaPodajnika() {
        return pracaPodajnika;
    }

    public void setPracaPodajnika(Boolean pracaPodajnika) {
        this.pracaPodajnika = pracaPodajnika;
    }

    public Integer PodajPaliwo(){
        return DAWKA_PALIWA;
    }
}
