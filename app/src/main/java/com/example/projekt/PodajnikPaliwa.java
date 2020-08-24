package com.example.projekt;


import android.support.v4.app.INotificationSideChannel;

public class PodajnikPaliwa {
    public static final int UZUPELNIAJACA_PORCJA_PALIWA = 100;
    public static final int DAWKA_PALIWA = 10;

    private Boolean czyWPracyAutomatycznej;
    private Boolean czyPodawacPaliwo;

    private Integer zbiornikPaliwa;


    public PodajnikPaliwa() {
        czyWPracyAutomatycznej = true;
        czyPodawacPaliwo = false;
        zbiornikPaliwa = 0;
    }

    public void UzupelnijPaliwo(){
        zbiornikPaliwa += UZUPELNIAJACA_PORCJA_PALIWA;
    }
    public Integer GetPoziomPaliwa(){
        return zbiornikPaliwa;
    }
    public void SetPracaAutomatyczna(boolean czyWPracyAutomatycznej){
        this.czyWPracyAutomatycznej = czyWPracyAutomatycznej;
    }
    public void SetPodawacPaliwo(boolean czyPodawacPaliwo){
        this.czyPodawacPaliwo = czyPodawacPaliwo;
    }
    public Integer PodajPaliwo(Integer IloscPaliwa){
        if(czyPodawacPaliwo){
            if(czyWPracyAutomatycznej){
                while(IloscPaliwa > zbiornikPaliwa){
                    UzupelnijPaliwo();
                }
                zbiornikPaliwa -= IloscPaliwa;
            }else {
                if (IloscPaliwa > zbiornikPaliwa) {
                    IloscPaliwa = zbiornikPaliwa;
                    zbiornikPaliwa = 0;
                } else{
                    zbiornikPaliwa -= IloscPaliwa;
                }
            }
            return IloscPaliwa;
        }
        return 0;
    }
    public Integer PodajPaliwo(){
        if(zbiornikPaliwa<DAWKA_PALIWA){
            UzupelnijPaliwo();
        }
        zbiornikPaliwa -= DAWKA_PALIWA;
        return DAWKA_PALIWA;
    }

}
