package com.example.projekt;

import android.widget.TextView;

public class Menu {

    private Boolean trybManualny = false;

    public Boolean getTrybManualny() { return trybManualny;}
    public void setTrybManualny()
    {
        if(trybManualny){
            trybManualny = false;
        }else trybManualny = true;

    }
    public void zatwierdz(Integer numerMenu)
    {
        switch(numerMenu){
            case 1: //histereza temperatury kotłą

                break;
            case 2: //temperatura załączenia pompy
                break;
            case 3: //tryb reczny
                setTrybManualny();
                break;
            case 4: //ustawinia fabryzcne
                break;
        }
    }

}
