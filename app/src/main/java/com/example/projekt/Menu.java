package com.example.projekt;

import android.content.Context;
import android.widget.Toast;

public class Menu{

    private Context context;

    public Menu(Context con) {
        this.context = con;
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

    public Sterownik setPredkoscNadmuchu(Sterownik ster, Integer predkosc){

        ster.dmuchawa.setPredkoscNadmuchu(predkosc);
        return ster;
    }

    public Sterownik setTemperaturaZadana(Sterownik ster, Integer temperatura){
        ster.setTempZadana(temperatura);
        return ster;
    }

    public Sterownik zatwierdz(Sterownik sterownik, Integer[] liczba)
    {
        int duration;
        CharSequence text;
        Toast toast;

        switch(liczba[0]){
            case 1: //histereza temperatury kotłą
                sterownik = setHisterezaTemperaturyKotla(sterownik, liczba[2]);
                text = "Ustawiono Histereze temperatury kotłą";
                duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                return sterownik;

            case 2://ustawienie temperatury zadanej
                sterownik = setTemperaturaZadana(sterownik, liczba[2]);
                text = "ustawiono temperature zadana";
                duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                return sterownik;

            case 3://ustawienie temperatury zalaczneia pompy
                sterownik = setTemperaturaZalaczeniaPompy(sterownik, liczba[2]);//temperatura załączenia pompy
                text = "ustawiono temperature zalaczenia pompy";
                duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                return sterownik;

            case 4: //ustaweinie prdekosci nawiewu
                sterownik = setPredkoscNadmuchu(sterownik, liczba[2]);//temperatura załączenia pompy
                text = "ustawiono predkosc obrotu dmuchawy";
                duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                return sterownik;
            case 5: //tryb reczny
                if(liczba[2] == 1) {
                    sterownik.setTrybManualny();
                    text = "wlaczono tryb manualny";
                    duration = Toast.LENGTH_SHORT;

                    toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
                return sterownik;


            case 6: //ustawinia fabryzcne
                if(liczba[2] == 1) {
                    Sterownik ster;
                    ster = new Sterownik();
                    ster.setTemperatura(sterownik.getTemperatura());
                    ster.pompa.setPracaPompy(sterownik.pompa.getPracaPompy());
                    ster.dmuchawa.setPracaNadmuchu(sterownik.dmuchawa.getPracaNadmuchu());
                    ster.setPracaPodajnika(sterownik.getPracaPodajnika());
                    sterownik = ster;
                    text = "przywrucono ustawienia fabryczne!";
                    duration = Toast.LENGTH_SHORT;

                    toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

                return sterownik;
        }
        return sterownik;
    }

    public Integer [] zakresUstawienia(Integer[] numerOpcji, Sterownik ster)
    {

        int duration;
        CharSequence text;
        Toast toast;

        switch(numerOpcji[0]){
            case 1: //histereza temperatury kotłą
                numerOpcji[1] = 10;
                numerOpcji[2] = ster.getHistereza();
                return numerOpcji;
            case 2: //ustwaeinie temperatury zadanej
                numerOpcji[1] = 90;
                numerOpcji[2] = ster.getTempZadana();
                return numerOpcji;

            case 3: //ustwaeinie temperatury zalaczenia ompy
                numerOpcji[1] = 60;
                numerOpcji[2] = ster.getTemperaturaZalaczeniaPompy();
                return numerOpcji;

            case 4: //ustawienie predkości nadmuchu
                numerOpcji[1] = 10;
                numerOpcji[2] = ster.dmuchawa.getPredkoscNadmuchu();
                return numerOpcji;

            case 5: //tryb reczny
                numerOpcji[1] = 1;
                if(ster.getTrybManualny()){
                    numerOpcji[2] = 1;
                }else numerOpcji[2] = 0;
                return numerOpcji;

            case 6: //ustawinia fabryzcne
                numerOpcji[1] = 1;
                numerOpcji[2] = 0;
                return numerOpcji;
        }
        return numerOpcji;
    }

}
