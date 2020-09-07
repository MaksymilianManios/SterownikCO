package com.example.projekt;

import android.content.Context;
import android.widget.Toast;

public class Menu{

    private Context context;
    private Boolean trybManualny = true;

    public Menu(Context con) {
        this.context = con;
    }

    public Boolean getTrybManualny() { return trybManualny;}

    public void setTrybManualny() {
        if(trybManualny){
            trybManualny = false;
        }else trybManualny = true;

    }

    public Sterownik setTemperaturaZalaczeniaPompy(Sterownik ster, Integer temp)
    {
        ster.setTemperaturaZalaczeniaPompy(temp);
        return ster;
    }

    public Sterownik setHisterezaTemperaturyKotla(Sterownik ster, Integer temp)
    {
        ster.setHistereza(temp);
        return ster;
    }

    public Sterownik zatwierdz(Integer numerMenu, Sterownik sterownik, Integer liczba)
    {
        int duration;
        CharSequence text;
        Toast toast;

        switch(numerMenu){
            case 1: //histereza temperatury kotłą
                sterownik = setHisterezaTemperaturyKotla(sterownik, liczba);
                text = "Ustawiono Histereze temperatury kotłą";
                duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                return sterownik;
            case 2:
                setTemperaturaZalaczeniaPompy(sterownik, liczba);//temperatura załączenia pompy
                text = "ustawiono temperature zalaczenia pompy";
                duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                return sterownik;

            case 3: //tryb reczny
                setTrybManualny();
                text = "wlaczono tryb manualny";
                duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                return sterownik;

            case 4: //ustawinia fabryzcne
                Sterownik ster;
                ster = new Sterownik();
                sterownik = ster;
                text = "przywrucono ustawienia fabryczne!";
                duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                return sterownik;
        }
        return sterownik;
    }



}
